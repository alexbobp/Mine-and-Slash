package com.robertx22.mine_and_slash.database.unique_items.pants;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlatePants;
import com.robertx22.mine_and_slash.database.spells.spell_classes.fire.MagmaFlowerSpell;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.PlusAbiliyLevelFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.ArmorMulti;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class PantsFire implements IUnique {

    public PantsFire() {

    }

    static StatReq req = new StatReq(
        LvlPointStat.STAMINA, StatReq.Size.NORMAL, LvlPointStat.INTELLIGENCE, StatReq.Size.SMALL);

    @Override
    public GearItemSlot getGearSlot() {
        return PlatePants.INSTANCE;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public String GUID() {
        return "pantsfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new ArmorFlat().size(StatMod.Size.HALF_MORE),
            new ArmorMulti().size(StatMod.Size.DOUBLE),
            new ElementalResistFlat(Elements.Fire),
            new PlusAbiliyLevelFlat(MagmaFlowerSpell.getInstance())
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Flaming Leggings";
    }

    @Override
    public String locDescForLangFile() {
        return "Embrace my flames.";
    }
}
