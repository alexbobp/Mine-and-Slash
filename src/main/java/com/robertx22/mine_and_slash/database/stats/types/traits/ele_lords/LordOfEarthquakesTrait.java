package com.robertx22.mine_and_slash.database.stats.types.traits.ele_lords;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.generated.AllElementalDamageMulti;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class LordOfEarthquakesTrait extends Trait implements IAffectsOtherStats {

    @Override
    public List<StatMod> getStats() {

        return Arrays.asList(new AllElementalDamageMulti(Elements.Nature));

    }

    @Override
    public String GUID() {
        return "lord_of_earthquakes";
    }

    @Override
    public String locNameForLangFile() {
        return "Lord of Earthquakes";
    }
}
