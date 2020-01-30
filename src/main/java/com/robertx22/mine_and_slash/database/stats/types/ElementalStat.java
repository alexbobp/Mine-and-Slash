package com.robertx22.mine_and_slash.database.stats.types;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IElementalGenerated;

import java.util.ArrayList;
import java.util.List;

public abstract class ElementalStat extends Stat implements IElementalGenerated<Stat> {

    public Elements element;

    public ElementalStat(Elements element) {
        this.element = element;

    }

    @Override
    public Elements getElement() {
        return this.element;
    }

    public abstract Stat newGeneratedInstance(Elements element);

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = new ArrayList<>();
        Elements.getAllExcludingPhysical().forEach(x -> list.add(newGeneratedInstance(x)));
        return list;

    }

}
