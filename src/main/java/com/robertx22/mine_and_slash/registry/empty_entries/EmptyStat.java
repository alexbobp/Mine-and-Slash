package com.robertx22.mine_and_slash.registry.empty_entries;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class EmptyStat extends Stat {

    private EmptyStat() {
    }

    public static EmptyStat getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public Elements getElement() {
        return Elements.Physical;
    }

    @Override
    public String locDescForLangFile() {
        return "This stat was probably removed or renamed.";
    }

    @Override
    public String locNameForLangFile() {
        return "Unknown Stat";
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.EMPTY;
    }

    @Override
    public String GUID() {
        return "unknown_stat";
    }

    private static class SingletonHolder {
        private static final EmptyStat INSTANCE = new EmptyStat();
    }
}
