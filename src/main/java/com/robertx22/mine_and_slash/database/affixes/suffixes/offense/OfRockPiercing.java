package com.robertx22.mine_and_slash.database.affixes.suffixes.offense;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.ArmorPenetrationFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfRockPiercing extends Suffix {

    public OfRockPiercing() {
        super(new Requirements(SlotRequirement.weaponsOnly(), LevelRequirement.fromHighLevel()));
    }

    @Override
    public String GUID() {
        return "of_rock_piercing";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ArmorPenetrationFlat(), new PhysicalDamagePercent(),
            new ArmorPenetrationFlat()
        );
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String locNameForLangFile() {
        return "Of Rock Piercing";
    }
}