package me.mangorage.tiabremastered.fabric.core;

import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import me.mangorage.tiabremastered.common.core.tiab.BasicTiab;
import net.minecraft.nbt.CompoundTag;

public class FabricTiab extends BasicTiab implements PlayerComponent {
    @Override
    public void readFromNbt(CompoundTag compoundTag) {
        this.deserialize(compoundTag);
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag) {
        this.serialize(compoundTag);
    }
}
