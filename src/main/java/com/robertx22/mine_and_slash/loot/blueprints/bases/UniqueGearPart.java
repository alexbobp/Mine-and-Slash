package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.registry.FilterListWrap;
import com.robertx22.mine_and_slash.registry.SlashRegistry;

public class UniqueGearPart extends BlueprintPart<IUnique> {

    public UniqueGearPart(GearBlueprint blueprint) {
        super(blueprint);
    }

    @Override
    protected IUnique generateIfNull() {

        if (blueprint.tier.isRandom == false) {
            return SlashRegistry.UniqueGears()
                .getWrapped()
                .ofExactTier(blueprint.tier.get())
                .random();
        } else {
            return randomUnique();
        }

    }

    private IUnique randomUnique() {

        GearBlueprint gearBlueprint = (GearBlueprint) blueprint;

        FilterListWrap<IUnique> gen = SlashRegistry.UniqueGears()
            .getWrapped()
            .ofTierOrLess(blueprint.tier.get());

        if (gearBlueprint.gearItemSlot.isGenerated()) {
            gen.ofSpecificGearType(gearBlueprint.gearItemSlot.get()
                .GUID()); // if not specified, dont need to set exact
        }

        return gen.random();

    }

}
