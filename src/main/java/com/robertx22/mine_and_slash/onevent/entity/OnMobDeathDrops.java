package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.loot.LootUtils;
import com.robertx22.mine_and_slash.loot.MasterLootGen;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.registers.common.CriteriaRegisters;
import com.robertx22.mine_and_slash.network.DmgNumPacket;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.NumberUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OnMobDeathDrops {
    private static final int DROPRAD = 40;
    private static final int DROPRADSQ = DROPRAD*DROPRAD;

    private static List<ServerPlayerEntity> playersNearEntity(Entity entity) {
        BlockPos pos = entity.getPosition();

        return entity.world.getEntitiesWithinAABB(ServerPlayerEntity.class,
                new AxisAlignedBB(
                        entity.posX + DROPRAD,
                        entity.posY + DROPRAD,
                        entity.posZ + DROPRAD,
                        entity.posX - DROPRAD,
                        entity.posY - DROPRAD,
                        entity.posZ - DROPRAD
                ),
                serverPlayerEntity -> serverPlayerEntity.getPosition().distanceSq(pos) <= DROPRADSQ);
    }

    @SubscribeEvent
    public static void mobOnDeathDrop(LivingDeathEvent event) {

        try {
            LivingEntity entity = event.getEntityLiving();
            if (entity.world.isRemote || entity instanceof PlayerEntity || !Load.hasUnit(entity))
                return;

            final Iterable<ServerPlayerEntity> killers;
            final ServerPlayerEntity mainKiller;
            {
                List<ServerPlayerEntity> nearMob = playersNearEntity(entity);

                if (event.getSource().getTrueSource() instanceof ServerPlayerEntity) {
                    mainKiller = (ServerPlayerEntity)event.getSource().getTrueSource();
                    Set<ServerPlayerEntity> s = new HashSet<>(nearMob);
                    s.addAll(playersNearEntity(mainKiller));
                    killers = s;
                } else {
                    killers = nearMob;
                    mainKiller = nearMob.get(RandomUtils.RandomRange(0, nearMob.size()-1));
                }
            }

            UnitData victim = Load.Unit(entity);

            if (!victim.shouldDropLoot())
                return;

            ModEntityConfig config = SlashRegistry.getEntityConfig(entity, victim);
            final float loot_multi = (float) config.LOOT_MULTI;
            final float exp_multi = (float) config.EXP_MULTI;

            for (ServerPlayerEntity player : killers) {
                UnitData killer = Load.Unit(player);

                CriteriaRegisters.DROP_LVL_PENALTY_TRIGGER.trigger(player, killer, victim);

                if (exp_multi > 0) {
                    int exp = GiveExp(entity, player, killer, victim, exp_multi);

                    if (exp > 0) {
                        DmgNumPacket packet = new DmgNumPacket(entity, Elements.Nature, "+" + NumberUtils
                                .formatNumber(exp) + " Exp!");
                        packet.isExp = true;
                        MMORPG.sendToClient(packet, player);
                    }
                }
            }

            if (loot_multi > 0) {
                MasterLootGen.genAndDrop(victim, Load.Unit(mainKiller), entity, mainKiller);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int GiveExp(LivingEntity victim, PlayerEntity entity, UnitData player,
                               UnitData mob, float multi) {

        int exp = (int) (mob.getLevel() * Rarities.Mobs.get(mob.getRarity())
                .ExpOnKill() * multi);

        exp = (int) LootUtils.ApplyLevelDistancePunishment(mob, player, exp);

        if (victim instanceof SlimeEntity) {
            exp /= 10;
        }

        if (WorldUtils.isMapWorldClass(victim.world)) {
            exp *= Load.playerMapData(entity).getExpMultiplier();
        }

        exp = player.PostGiveExpEvent(victim, entity, exp);

        return exp;

    }

}
