package com.robertx22.mine_and_slash.config.base_player_stat;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.*;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;

import java.util.HashMap;

public class BasePlayerStatContainer implements ISlashRegistryInit, IApplyableStats {

    public static BasePlayerStatContainer INSTANCE = new BasePlayerStatContainer();

    public static BasePlayerStatContainer defaultStats() {

        BasePlayerStatContainer c = new BasePlayerStatContainer();

        //base ones

        c.base(Health.getInstance(), 100);
        c.base(HealthRegen.getInstance(), 1.5F);
        c.base(MagicShieldRegen.getInstance(), 1.5F);
        c.base(PhysicalDamage.getInstance(), 3);
        c.base(CriticalHit.getInstance(), 1);
        c.base(CriticalDamage.getInstance(), 0);

        //scaling ones

        c.scale(Energy.getInstance(), 125);
        c.scale(Mana.getInstance(), 50);

        c.scale(EnergyRegen.getInstance(), 7);
        c.scale(ManaRegen.getInstance(), 3);

        c.scale(Health.getInstance(), 5);
        c.scale(HealthRegen.getInstance(), 1);
        c.scale(MagicShieldRegen.getInstance(), 0.4F);
        c.scale(PhysicalDamage.getInstance(), 0.25D);

        return c;

    }

    public HashMap<String, Double> BASE_PLAYER_STATS = new HashMap<>();
    public HashMap<String, Double> PLAYER_STATS_THAT_SCALE_TO_LEVEL = new HashMap<>();

    public void scale(Stat stat, double val) {
        PLAYER_STATS_THAT_SCALE_TO_LEVEL.put(stat.GUID(), val);
    }

    public void base(Stat stat, double val) {
        BASE_PLAYER_STATS.put(stat.GUID(), val);
    }

    @Override
    public void registerAll() {
        INSTANCE = this;
    }

    @Override
    public void applyStats(EntityCap.UnitData data, int level) {

        this.BASE_PLAYER_STATS.entrySet()
            .forEach(x -> {
                data.getUnit()
                    .getCreateStat(x.getKey())
                    .addFlat(x.getValue()
                        .floatValue());
            });
        this.PLAYER_STATS_THAT_SCALE_TO_LEVEL.entrySet()
            .forEach(x -> {
                data.getUnit()
                    .getCreateStat(x.getKey())
                    .addFlat(x.getValue()
                        .floatValue(), level);
            });

    }

}
