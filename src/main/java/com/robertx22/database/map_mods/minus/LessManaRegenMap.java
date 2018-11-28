package com.robertx22.database.map_mods.minus;

import com.robertx22.database.stat_types.resources.ManaRegen;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class LessManaRegenMap extends StatMod {

	public LessManaRegenMap() {
	}

	@Override
	public String GUID() {
		return "LessManaRegenMap";
	}

	@Override
	public int Min() {
		return -30;
	}

	@Override
	public int Max() {
		return -80;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Multi;
	}

	@Override
	public Stat GetBaseStat() {
		return new ManaRegen();
	}

}