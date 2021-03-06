package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.loot.blueprints.ItemBlueprint;

public class GearItemSlotPart extends BlueprintPart<GearItemSlot> {

    public GearItemSlotPart(ItemBlueprint blueprint) {
        super(blueprint);
    }

    @Override
    protected GearItemSlot generateIfNull() {
        return SlashRegistry.GearTypes().random();
    }

    public void set(String id) {
        super.set(SlashRegistry.GearTypes().get(id));
    }

}


