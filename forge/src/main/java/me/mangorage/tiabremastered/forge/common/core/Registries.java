package me.mangorage.tiabremastered.forge.common.core;

import me.mangorage.tiabremastered.common.blockentities.TimeCollectorMachineBlockEntity;
import me.mangorage.tiabremastered.common.blocks.TimeCollectorMachineBlock;
import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.core.registries.TiabRegistries;
import me.mangorage.tiabremastered.common.entities.AccelerationEntity;
import me.mangorage.tiabremastered.common.items.TimeCapsuleItem;
import me.mangorage.tiabremastered.forge.common.items.ForgeTimeInABottleItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registries {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegisteryUtils.register(
            DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MODID)
    );

    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegisteryUtils.register(
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Constants.MODID)
    );

    private static final DeferredRegister<Item> ITEMS = DeferredRegisteryUtils.register(
            DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MODID)
    );

    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegisteryUtils.register(
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Constants.MODID)
    );

    public static final CreativeModeTab TAB = TiabRegistries.TIAB_TAB.initiate(
            () -> new CreativeModeTab(Constants.MODID + ".main") {
                @Override
                public ItemStack makeIcon() {
                    return TiabRegistries.TIME_IN_A_BOTTLE_ITEM.get().getDefaultInstance();
                }
            });

    public static final RegistryObject<ForgeTimeInABottleItem> TIME_IN_A_BOTTLE_ITEM =
            ITEMS.register(
                    TiabRegistries.TIME_IN_A_BOTTLE_ITEM.getNameID(),
                    () -> TiabRegistries.ITEMS.initiate(
                            TiabRegistries.TIME_IN_A_BOTTLE_ITEM,
                            () -> new ForgeTimeInABottleItem(
                                    new Item.Properties()
                                            .stacksTo(1)
                                            .tab(TiabRegistries.TIAB_TAB.get())
                            )
                    )
            );

    public static final RegistryObject<TimeCapsuleItem> TIME_CAPSULE_ITEM =
            ITEMS.register(
                    TiabRegistries.TIME_CAPSULE_ITEM.getNameID(),
                    () -> TiabRegistries.ITEMS.initiate(TiabRegistries.TIME_CAPSULE_ITEM)
            );

    public static final RegistryObject<TimeCollectorMachineBlock> TIME_COLLECTOR_MACHINE_BLOCK =
            BLOCKS.register(
                    TiabRegistries.TIME_COLLECTOR_MACHINE_BLOCK.getNameID(),
                    () -> TiabRegistries.BLOCKS.initiate(
                            TiabRegistries.TIME_COLLECTOR_MACHINE_BLOCK
                    )
            );

    public static final RegistryObject<BlockEntityType<TimeCollectorMachineBlockEntity>> TIME_COLLECTOR_MACHINE_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register(
                    TiabRegistries.TIME_COLLECTOR_MACHINE_BLOCK_ENTITY.getNameID(),
                    () -> TiabRegistries.BLOCK_ENTITY_TYPES.initiate(
                            TiabRegistries.TIME_COLLECTOR_MACHINE_BLOCK_ENTITY
                    )
            );

    public static final RegistryObject<EntityType<AccelerationEntity>> ACCELERATION_ENTITY =
            ENTITY_TYPES.register(TiabRegistries.ACCELERATION_ENTITY.getNameID(), () ->
                TiabRegistries.ENTITY_TYPES.initiate(TiabRegistries.ACCELERATION_ENTITY)
            );

    public static void init() {
    }
}
