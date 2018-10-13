package com.robertx22.onevent.Item;

import com.robertx22.gearitem.ITooltip;
import com.robertx22.saving.Saving;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnTooltip {

	ItemStack item;
	NBTTagCompound nbt;
	NBTTagCompound statsNBT;
	NBTTagCompound enchantsNBT;
	NBTTagCompound socketNBT;

	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event) {

		if (event.getEntityPlayer() != null && event.getEntityPlayer().world != null
				&& !event.getEntityPlayer().world.isRemote) {
			return;
		}

		item = event.getItemStack();

		if (item == null) {
			return;
		}
		if (!item.hasTagCompound()) {
			return;
		}

		ITooltip data = Saving.Load(item.getTagCompound(), ITooltip.class);

		if (data != null) {

			data.BuildTooltip(event);

		}

	}

}
