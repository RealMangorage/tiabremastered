package me.mangorage.tiabremastered.common.core.tiab;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public interface ITIAB {
    int getTimeLeft();
    int getTotalAcumulatedTime();
    void setTotalAcumulatedTime(int value);
    void setTimeLeft(int value);
    void addTime(int amount);
    int removeTime(int amount, boolean simulate);
    void tick(Player player);

    CompoundTag serialize();
    void deserialize(CompoundTag tag);

}
