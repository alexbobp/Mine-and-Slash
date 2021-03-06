package com.robertx22.mine_and_slash.database.stats.types.elementals.all_damage;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.AllEleSpellDmgEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class AllEleSpellDmg extends Stat implements IStatEffects {

    public static String GUID = "all_ele_spell_dmg";

    @Override
    public String locDescForLangFile() {
        return "Increases All Elemental Spell DMG";
    }

    public AllEleSpellDmg() {

    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public IStatEffect getEffect() {
        return new AllEleSpellDmgEffect();
    }

    @Override
    public String locNameForLangFile() {
        return "All Ele Spell Damage";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements getElement() {
        return Elements.Physical;
    }
}
