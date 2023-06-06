package me.mangorage.tiabremastered.common.core.tiab;

import me.mangorage.tiabremastered.common.core.registries.TiabRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class BasicTiab implements ITIAB {
    private int timeLeft = 0;
    private int totalAccumulatedTime = 0;
    @Override
    public int getTimeLeft() {
        return timeLeft;
    }

    @Override
    public int getTotalAcumulatedTime() {
        return totalAccumulatedTime;
    }

    @Override
    public void setTotalAcumulatedTime(int value) {
        this.totalAccumulatedTime = value;
    }

    @Override
    public void setTimeLeft(int value) {
        this.timeLeft = value;
    }

    @Override
    public void addTime(int amount) {
        timeLeft += amount;
        totalAccumulatedTime += amount;
    }

    // returns true if we didn't go below 0
    @Override
    public int removeTime(int amount, boolean simulate) {
        if (simulate)
            return timeLeft - amount;

        timeLeft -= amount;
        return timeLeft;
    }

    @Override
    public void tick(Player player) {
        if (player.getInventory().countItem(TiabRegistries.TIME_IN_A_BOTTLE_ITEM.get()) > 0)
            addTime(1);
    }


    @Override
    public CompoundTag serialize(CompoundTag tag) {
        tag.putInt("time", timeLeft);
        tag.putInt("totaltime", totalAccumulatedTime);
        return tag;
    }

    @Override
    public void deserialize(CompoundTag tag) {
        if (tag.contains("time"))
            timeLeft = tag.getInt("time");
        if (tag.contains("totaltime"))
            totalAccumulatedTime = tag.getInt("totaltime");
    }
}
