package com.robertx22.mine_and_slash.database.rarities.runes;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseRare;

public class RareRune extends BaseRare implements RuneRarity {

    private RareRune() {
    }

    public static RareRune getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNES.RARE_WEIGHT.get();
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(30, 75);
    }

    @Override
    public float salvageLotteryWinChance() {
        return 20;
    }

    private static class SingletonHolder {
        private static final RareRune INSTANCE = new RareRune();
    }
}
