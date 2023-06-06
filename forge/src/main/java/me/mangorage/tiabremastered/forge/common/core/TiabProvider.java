package me.mangorage.tiabremastered.forge.common.core;

import me.mangorage.tiabremastered.common.core.tiab.BasicTiab;
import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TiabProvider implements ICapabilitySerializable<CompoundTag> {
    private final BasicTiab CAP = new BasicTiab();
    private final LazyOptional<ITIAB> lazy = LazyOptional.of(() -> CAP);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        if (capability == ModCapabilities.TIAB)
            return lazy.cast();

        return LazyOptional.empty();
    }


    @Override
    public CompoundTag serializeNBT() {
        return CAP.serialize(new CompoundTag());
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        CAP.deserialize(tag);
    }
}
