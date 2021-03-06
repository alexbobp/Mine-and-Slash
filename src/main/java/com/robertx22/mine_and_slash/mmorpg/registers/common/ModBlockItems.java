package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockItems {

    static Item.Properties stationProp = new Item.Properties().group(CreativeTabs.MyModTab);
    static Item.Properties inviProp = new Item.Properties();

    public static DeferredRegister<Item> REG = new DeferredRegister<>(ForgeRegistries.ITEMS, Ref.MODID);

    public static RegistryObject<Item> GEAR_MODIFY = of(ModBlocks.GEAR_MODIFY);
    public static RegistryObject<Item> GEAR_REPAIR = of(ModBlocks.GEAR_REPAIR);
    public static RegistryObject<Item> GEAR_SALVAGE = of(ModBlocks.GEAR_SALVAGE);
    public static RegistryObject<Item> MAP_DEVICE = of(ModBlocks.MAP_DEVICE);

    public static RegistryObject<Item> MAGMA_FLOWER = invi(ModBlocks.MAGMA_FLOWER);
    public static RegistryObject<Item> THORN_BUSH = invi(ModBlocks.THORN_BUSH);

    public static RegistryObject<Item> DUNGEON_PORTAL = invi(ModBlocks.DUNGEON_PORTAL);
    public static RegistryObject<Item> MAP_CHEST = invi(ModBlocks.MAP_CHEST);

    public static RegistryObject<Item> SCRABBLE = of(ModBlocks.SCRABBLE);

    static <T extends Block> RegistryObject<Item> invi(RegistryObject<T> block) {
        return REG.register(block.getId()
            .getPath(), () -> new BlockItem(block.get(), inviProp));
    }

    static <T extends Block> RegistryObject<Item> of(RegistryObject<T> block) {
        return REG.register(block.getId()
            .getPath(), () -> new BlockItem(block.get(), stationProp));
    }

}
