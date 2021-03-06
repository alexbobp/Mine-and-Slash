package com.robertx22.mine_and_slash.config.whole_mod_entity_configs;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

public class ModEntityConfig implements ISlashRegistryEntry<ModEntityConfig> {

    public ModEntityConfig() {

    }

    public ModEntityConfig(float loot, float exp) {
        this.EXP_MULTI = exp;
        this.LOOT_MULTI = loot;
    }

    public transient String GUID = "";

    public double LOOT_MULTI = 1F;
    public double EXP_MULTI = 1F;
    public int MIN_RARITY = 0;
    public int MAX_RARITY = 4;
    public int MIN_LEVEL = 0;
    public int MAX_LEVEL = Integer.MAX_VALUE;
    public boolean LEVEL_TO_NEAREST_PLAYER = false;
    public int LEVEL_MODIFIER = 0;
    public double DMG_MULTI = 1;
    public double HP_MULTI = 1;
    public double STAT_MULTI = 1;

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.MOD_ENTITY_CONFIGS;
    }

    @Override
    public boolean unregisterBeforeConfigsLoad() {
        return true;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public int Weight() {
        return 100;
    }

    @Override
    public int getRarityRank() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());
    }

}