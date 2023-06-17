package me.mangorage.tiabremastered.common.entities;

import me.mangorage.tiabremastered.common.core.Util;
import me.mangorage.tiabremastered.common.core.registries.TiabRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;

public class AccelerationEntity extends Entity {
    private static final EntityDataAccessor<Integer> timeRate = SynchedEntityData.defineId(AccelerationEntity.class, EntityDataSerializers.INT);
    private int ticksLeft = 0;
    private BlockPos pos = BlockPos.ZERO;
    private int ticksAlive = 0;
    private int ticksAccelerated = 0;

    public AccelerationEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public AccelerationEntity(Level level, BlockPos pos) {
        super(TiabRegistries.ACCELERATION_ENTITY.get(), level);
        this.pos = pos;
        setPos(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5);
    }

    public BlockPos getAcceleratedBlockPos() {
        return pos;
    }

    @Override
    public void tick() {
        if (getLevel().isClientSide) return;
        ticksAlive++;
        // Kill it if we are at 0 ticks left/pos is null/block changed?
        if (ticksLeft <= 0 || this.pos == null || this.pos == BlockPos.ZERO || !Util.doesBlockTick(level, pos))
            kill();

        super.tick();
        // TODO: Do something in relation to ticksLeft/rate
        for (int i = 0; i < getTimeRate(); i++) {
            Util.tickBlock((ServerLevel) level, pos);
            ticksLeft--;
            ticksAccelerated++;
        }
    }

    @Override
    public void kill() {
        // Dev Debug for now
        // LogManager.getLogger().info("Killed Acceleration Entity : Ticks alive-> " + ticksAlive + " Accelerated: " + ticksAccelerated);
        super.kill();
    }

    public int getTimeRate() {
        return entityData.get(timeRate);
    }

    public void setTimeRate(int pTimeRate) {
        entityData.set(timeRate, pTimeRate);
    }

    public int getTicksLeft() {
        return ticksLeft;
    }

    public void setTicksLeft(int ticksLeft) {
        this.ticksLeft = ticksLeft;
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(timeRate, 1);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("timeRate"))
            setTimeRate(tag.getInt("timeRate"));
        if (tag.contains("ticksLeft"))
            setTicksLeft(tag.getInt("ticksLeft"));
        if (tag.contains("blockPos"))
            this.pos = NbtUtils.readBlockPos(tag.getCompound("blockPos"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("timeRate", entityData.get(timeRate));
        tag.putInt("ticksLeft", ticksLeft);
        tag.put("blockPos", NbtUtils.writeBlockPos(pos));
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
