package com.robertx22.mine_and_slash.database.requirements;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.data_generation.JsonUtils;
import com.robertx22.mine_and_slash.database.requirements.bases.GearRequestedFor;
import com.robertx22.mine_and_slash.database.requirements.bases.UniqueItemRequirement;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class ExactUniquesRequierement extends UniqueItemRequirement<ExactUniquesRequierement> {

    List<String> uniquesGUIDS = new ArrayList<>();

    public ExactUniquesRequierement() {

    }

    public ExactUniquesRequierement(IUnique unique) {
        uniquesGUIDS.add(unique.GUID());
    }

    public ExactUniquesRequierement(List<IUnique> uniques) {
        for (IUnique uniq : uniques) {
            uniquesGUIDS.add(uniq.GUID());
        }
    }

    @Override
    public boolean meetsRequierment(GearRequestedFor requested) {

        Boolean prev = super.meetsRequierment(requested);

        if (prev == false) {
            return false;
        }

        if (uniquesGUIDS.contains(requested.gearData.uniqueStats.getUnique()
            .GUID()) == false) {
            return false;
        }

        return true;

    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.add("possible_uniques", JsonUtils.stringListToJsonArray(this.uniquesGUIDS));
        return json;
    }

    @Override
    public ExactUniquesRequierement fromJson(JsonObject json) {

        try {
            ExactUniquesRequierement newobj = new ExactUniquesRequierement();
            newobj.uniquesGUIDS = JsonUtils.jsonArrayToStringList(json.getAsJsonArray("possible_uniques"));
            return newobj;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public String getJsonID() {
        return "exact_unique";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        list.add(new SText("Needs Exact Unique Gear: "));

        ITextComponent comp = new SText(TextFormatting.YELLOW + "");
        this.uniquesGUIDS.forEach(x -> {
            comp.appendText(" ")
                .appendSibling(SlashRegistry.UniqueGears()
                    .get(x)
                    .locName());

        });
        list.add(comp);

        return list;

    }
}


