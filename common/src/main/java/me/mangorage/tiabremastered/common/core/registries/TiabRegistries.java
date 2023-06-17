package me.mangorage.tiabremastered.common.core.registries;

import me.mangorage.tiabremastered.common.blockentities.TimeCollectorMachineBlockEntity;
import me.mangorage.tiabremastered.common.blocks.TimeCollectorMachineBlock;
import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.entities.AccelerationEntity;
import me.mangorage.tiabremastered.common.items.TimeCapsuleItem;
import me.mangorage.tiabremastered.common.items.TimeInABottleItem;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class TiabRegistries {
    public static final LazyReferenceRegistry<Block> BLOCKS = LazyReferenceRegistry.create(Constants.MODID, Registry.BLOCK);
    public static final LazyReferenceRegistry<BlockEntityType<?>> BLOCK_ENTITY_TYPES = LazyReferenceRegistry.create(Constants.MODID, Registry.BLOCK_ENTITY_TYPE);
    public static final LazyReferenceRegistry<Item> ITEMS = LazyReferenceRegistry.create(Constants.MODID, Registry.ITEM);
    public static final LazyReferenceRegistry<EntityType<?>> ENTITY_TYPES = LazyReferenceRegistry.create(Constants.MODID, Registry.ENTITY_TYPE);


    public static final LazyReferenceCreativeModeTab<CreativeModeTab> TIAB_TAB =
            LazyReferenceCreativeModeTab.prepareDefaultRegister(null);

    public static final LazyReferenceObject<TimeInABottleItem> TIME_IN_A_BOTTLE_ITEM =
            ITEMS.prepareRegister("timeinabottle", null);

    public static final LazyReferenceObject<TimeCapsuleItem> TIME_CAPSULE_ITEM =
            ITEMS.prepareRegister(
                   "capsule",
                    () -> new TimeCapsuleItem(new Item.Properties().stacksTo(64).tab(TIAB_TAB.get()),  1000)
            );
    public static final LazyReferenceObject<EntityType<AccelerationEntity>> ACCELERATION_ENTITY =
            ENTITY_TYPES.prepareRegister(
                    "acceleration",
                    () -> EntityType.Builder.<AccelerationEntity>of(AccelerationEntity::new, MobCategory.MISC)
                            .clientTrackingRange(10)
                            .noSummon()
                            .fireImmune()
                            .sized(0.1F, 0.1F)
                            .build("tiabacceleration")
            );

    public static final LazyReferenceObject<TimeCollectorMachineBlock> TIME_COLLECTOR_MACHINE_BLOCK =
            BLOCKS.prepareRegister(
                    "timecollector",
                    () -> new TimeCollectorMachineBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL))
            );

    public static final LazyReferenceObject<BlockEntityType<TimeCollectorMachineBlockEntity>> TIME_COLLECTOR_MACHINE_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.prepareRegister(
                    "timecollectorentity",
                    () -> BlockEntityType.Builder.of(
                            TimeCollectorMachineBlockEntity::new,
                            TIME_COLLECTOR_MACHINE_BLOCK.get()
                            ).build(null)
            );
}
