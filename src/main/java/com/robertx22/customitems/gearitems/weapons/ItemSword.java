package com.robertx22.customitems.gearitems.weapons;

import java.util.HashMap;

import com.robertx22.customitems.gearitems.bases.BaseSwordItem;
import com.robertx22.customitems.gearitems.bases.IWeapon;
import com.robertx22.database.lists.Rarities;
import com.robertx22.database.stats.types.offense.PhysicalDamage;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ItemSword extends BaseSwordItem implements IWeapon {
	public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

	public ItemSword(int rarity, HashMap<Integer, Item> map) {
		super(rarity, map);

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		Rarities.Items.forEach((x) -> Items.put(x.Rank(), new ItemSword(x.Rank(), Items)));
		Items.values().forEach((x) -> event.getRegistry().register(x));
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		Items.values().forEach((x) -> RegisterUtils.registerRender(x));
	}

	@Override
	public String Name() {
		return "Sword";
	}

	@Override
	public int GetEnergyCost() {
		return 4;
	}

	@Override
	public boolean Attack(EntityLivingBase source, EntityLivingBase target, Unit unitsource, Unit targetUnit) {

		int num = (int) unitsource.MyStats.get(PhysicalDamage.GUID).Value;
		DamageEffect dmg = new DamageEffect(source, target, num, unitsource, targetUnit);
		dmg.Type = EffectTypes.BASIC_ATTACK;
		dmg.Activate();

		return true;
	}

}