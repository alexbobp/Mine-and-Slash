package com.robertx22.database.stats.types.elementals.resist;

import com.robertx22.uncommon.enumclasses.Elements;

public class WaterResist extends BaseEleResist {
	public static String GUID = "Water Resist";

	public WaterResist() {
	}

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public Elements Element() {
		return Elements.Water;
	}

}
