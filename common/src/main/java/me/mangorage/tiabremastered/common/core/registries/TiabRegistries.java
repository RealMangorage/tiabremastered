package me.mangorage.tiabremastered.common.core.registries;

import me.mangorage.tiabremastered.common.blockentities.TimeCollectorMachineBlockEntity;
import me.mangorage.tiabremastered.common.blocks.TimeCollectorMachineBlock;
import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.entities.AccelerationEntity;
import me.mangorage.tiabremastered.common.items.TimeCapsuleItem;
import me.mangorage.tiabremastered.common.items.TimeInABottleItem;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class TiabRegistries {
    public static final ResourceKey<Registry<CreativeModeTab>> TABS_KEY = ResourceKey.createRegistryKey(new ResourceLocation("tabs"));
    public static final LazyReferenceRegistry<CreativeModeTab> TABS = LazyReferenceRegistry.create(Constants.MODID, TABS_KEY);
    public static final LazyReferenceRegistry<Block> BLOCKS = LazyReferenceRegistry.create(Constants.MODID, Registry.BLOCK_REGISTRY);
    public static final LazyReferenceRegistry<BlockEntityType<?>> BLOCK_ENTITIES = LazyReferenceRegistry.create(Constants.MODID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);
    public static final LazyReferenceRegistry<Item> ITEMS = LazyReferenceRegistry.create(Constants.MODID, Registry.ITEM_REGISTRY);
    public static final LazyReferenceRegistry<EntityType<?>> ENTITIES = LazyReferenceRegistry.create(Constants.MODID, Registry.ENTITY_TYPE_REGISTRY);


    public static final LazyReferenceObject<CreativeModeTab> TIAB_TAB =
            TABS.prepareRegister("main", null);

    public static final LazyReferenceObject<TimeInABottleItem> TIME_IN_A_BOTTLE_ITEM =
            ITEMS.prepareRegister("timeinabottle", null);

    public static final LazyReferenceObject<TimeCapsuleItem> TIME_CAPSULE_ITEM =
            ITEMS.prepareRegister(
                   "capsule",
                    () -> new TimeCapsuleItem(new Item.Properties().stacksTo(64).tab(TIAB_TAB.get()),  1000)
            );
    public static final LazyReferenceObject<EntityType<AccelerationEntity>> ACCELERATION_ENTITY =
            ENTITIES.prepareRegister(
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
            BLOCK_ENTITIES.prepareRegister(
                    "timecollectorentity",
                    () -> BlockEntityType.Builder.of(
                            TimeCollectorMachineBlockEntity::new,
                            TIME_COLLECTOR_MACHINE_BLOCK.get()
                            ).build(null)
            );
}
