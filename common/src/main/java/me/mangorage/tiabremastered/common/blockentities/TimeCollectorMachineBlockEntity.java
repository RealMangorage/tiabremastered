package me.mangorage.tiabremastered.common.blockentities;

import me.mangorage.tiabremastered.common.core.registries.TiabRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TimeCollectorMachineBlockEntity extends BlockEntity {
    public TimeCollectorMachineBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(TiabRegistries.TIME_COLLECTOR_MACHINE_BLOCK_ENTITY.get(), blockPos, blockState);
    }
}
