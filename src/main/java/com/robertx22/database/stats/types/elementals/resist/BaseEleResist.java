package com.robertx22.database.stats.types.elementals.resist;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.UsableStat;
import com.robertx22.stats.StatEffects.ElementalResistEffect;

public abstract class BaseEleResist extends UsableStat implements IStatEffects {

	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new ElementalResistEffect());
	}

	@Override
	public float MaximumPercent() {
		return 0.75F;
	}

	@Override
	public int AverageStat() {
		return 5;
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}
