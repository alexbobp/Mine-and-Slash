package com.robertx22.mine_and_slash.config.base_player_stat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ConfigRegister;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;

import java.util.HashSet;

public class BasePlayerStatSerial implements ISerializedConfig<BasePlayerStatContainer> {
    public static BasePlayerStatSerial INSTANCE = new BasePlayerStatSerial();

    @Override
    public String fileName() {
        return "BasePlayerStats.txt";
    }

    @Override
    public BasePlayerStatContainer getDefaultObject() {
        return BasePlayerStatContainer.defaultStats();
    }

    @Override
    public BasePlayerStatContainer loadFromString(String string) {
        return new Gson().fromJson(string, BasePlayerStatContainer.class);
    }

    @Override
    public void autoFixProblems() {

        BasePlayerStatContainer currentConfig = loadFromString(getJsonFromFile(getPath()));

        BasePlayerStatContainer defaultConfig = getDefaultObject();

        new HashSet<>(currentConfig.PLAYER_STATS_THAT_SCALE_TO_LEVEL.entrySet()).stream()
                .filter(x -> !SlashRegistry.Stats().isRegistered(x.getKey()))
                .forEach(p -> {
                    currentConfig.PLAYER_STATS_THAT_SCALE_TO_LEVEL.remove(p.getKey());
                });

        new HashSet<>(currentConfig.BASE_PLAYER_STATS.entrySet()).stream()
                .filter(x -> !SlashRegistry.Stats().isRegistered(x.getKey()))
                .forEach(p -> {
                    currentConfig.BASE_PLAYER_STATS.remove(p.getKey());
                });

        defaultConfig.BASE_PLAYER_STATS.entrySet().forEach(x -> {
            if (!currentConfig.BASE_PLAYER_STATS.containsKey(x.getKey())) {
                currentConfig.BASE_PLAYER_STATS.put(x.getKey(), x.getValue());
            }
        });

        defaultConfig.PLAYER_STATS_THAT_SCALE_TO_LEVEL.entrySet().forEach(x -> {
            if (!currentConfig.PLAYER_STATS_THAT_SCALE_TO_LEVEL.containsKey(x.getKey())) {
                currentConfig.PLAYER_STATS_THAT_SCALE_TO_LEVEL.put(x.getKey(), x.getValue());
            }
        });

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(currentConfig);
        SerializationUtils.makeFileAndDirAndWrite(folder(), fileName(), json, true);

    }

    @Override
    public ConfigRegister.Config getConfigType() {
        return ConfigRegister.Config.BASE_PLAYER_STATS;
    }
}
