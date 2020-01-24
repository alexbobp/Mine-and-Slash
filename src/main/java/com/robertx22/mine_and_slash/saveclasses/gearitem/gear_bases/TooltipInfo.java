package com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;

public class TooltipInfo implements Cloneable {

    public TooltipInfo(EntityCap.UnitData unitdata, MinMax minmax, int level) {
        this.minmax = minmax;
        this.level = level;
        this.unitdata = unitdata;

        if (Screen.hasShiftDown()) {
            verbose = true;
        }

    }

    public TooltipInfo(EntityCap.UnitData unitdata, int level) {
        this.minmax = new MinMax(100, 100);
        this.level = level;
        this.unitdata = unitdata;

        if (Screen.hasShiftDown()) {
            verbose = true;
        }

    }

    public TooltipInfo() {
    }

    public TooltipInfo(PlayerEntity player) {
        this.player = player;
        this.unitdata = Load.Unit(player);
        this.level = unitdata.getLevel();
        this.minmax = new MinMax(100, 100);
    }

    public TooltipInfo setIsSet() {
        this.isSet = true;
        return this;
    }

    public PlayerEntity player;
    public EntityCap.UnitData unitdata;
    public MinMax minmax;
    public int level;
    public boolean isSet = false;
    public boolean usePrettyStatSymbols = false;
    public boolean verbose = false;

    public TooltipInfo withLevel(int level) {

        TooltipInfo info = null;
        try {
            info = (TooltipInfo) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        info.level = level;

        return info;

    }

}
