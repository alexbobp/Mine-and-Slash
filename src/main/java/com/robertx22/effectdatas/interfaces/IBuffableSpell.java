package com.robertx22.effectdatas.interfaces;

import com.robertx22.spells.bases.BaseSpell.SpellType;

public interface IBuffableSpell {

    enum SpellBuffType {
	None, Homing_Projectile, Ghost_Projectile, Triple_Projectile_Cone, Aoe_On_Proj_Kill_Target, Zephyr_Speed_Boost
    }

    void setBuff(SpellBuffType buff);

    SpellBuffType getBuff();

    void setType(SpellType type);

    SpellType getType();
}
