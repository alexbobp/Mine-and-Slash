package com.robertx22.database.stats.types.elementals.pene;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class FirePene extends Stat {
	public FirePene() {
	}

	@Override
	public String Name() {
		return "Fire Penetration";
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return Elements.Fire;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}