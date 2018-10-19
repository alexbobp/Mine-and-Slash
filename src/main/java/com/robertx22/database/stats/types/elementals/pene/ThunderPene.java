package com.robertx22.database.stats.types.elementals.pene;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class ThunderPene extends Stat {
	public ThunderPene() {
	}

	@Override
	public String Name() {
		return "Thunder Penetration";
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return Elements.Thunder;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}