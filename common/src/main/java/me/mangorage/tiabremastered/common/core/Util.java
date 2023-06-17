package me.mangorage.tiabremastered.common.core;

import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import me.mangorage.tiabremastered.common.entities.AccelerationEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Optional;

public class Util {
    private static final String[] NOTES = {"C", "D", "E", "F", "G2", "A2", "B2", "C2", "D2", "E2", "F2"};

    /**
     * @param totalTicks ticks
     * @return
     */
    public static String getFormattedTimeString(int totalTicks) {
        String returned = "";

        int ticks = totalTicks % 20;
        int totalSeconds = totalTicks / 20;

        int seconds = totalSeconds % 60;
        int totalminutes = totalSeconds / 60;

        int minutes = totalminutes % 60;
        int totalhours = totalminutes / 60;

        int hours = totalhours % 24;
        int totaldays = totalhours / 24;

        if (ticks > 0)
            returned = ticks + "T";
        if (seconds > 0)
            returned = seconds + "S " + returned;
        if (minutes > 0)
            returned = minutes + "M " + returned;
        if (hours > 0)
            returned = hours + "H " + returned;
        if (totaldays > 0)
            returned = totaldays + "D " + returned;

        return returned.strip();
    }

    // minBaseTicks * rate
    // minBaseTicks = 20 * 5 = 5 seconds
    public static int getTicksForRate(int minBaseTicks, int rate) {
        return minBaseTicks * rate;
    }

    public static void setTicksForEntity(Player player, AccelerationEntity e, int rate, ITIAB itiab) {
        BlockPos pos = e.getAcceleratedBlockPos();
        Level level = e.getLevel();
        int ticksNeeded = Util.getTicksForRate(100, rate);

        if (itiab.getTimeLeft() >= ticksNeeded || player.getAbilities().instabuild) {
            if (!player.getAbilities().instabuild)
                itiab.setTimeLeft(itiab.getTimeLeft() - ticksNeeded);

            e.setTimeRate(rate);
            e.setTicksLeft(e.getTicksLeft() + ticksNeeded);
            Util.playSound(level, pos, rate);
        }
    }

    public static boolean doesBlockTick(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        Optional<BlockEntity> BE = Optional.ofNullable(level.getBlockEntity(pos));
        return (BE.isPresent() && state.getTicker(level, BE.get().getType()) != null) || state.isRandomlyTicking();
    }

    public static <T extends BlockEntity> void tickBlock(ServerLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        Optional<BlockEntity> BE = Optional.ofNullable(level.getBlockEntity(pos));
        BE.ifPresentOrElse(RBE -> {
            BlockEntityTicker<BlockEntity> ticker = (BlockEntityTicker<BlockEntity>) RBE.getBlockState().getTicker(level, RBE.getType());
            ticker.tick(level, pos, state, RBE);
        }, () -> {
            state.randomTick(level, pos, level.random);
        });
    }

    public static void updateNewTiab(ITIAB Old, ITIAB New) {
        if (Old == New) return; // Don't bother!, as it's the same instance!
        New.setTimeLeft(Old.getTimeLeft());
        New.setTotalAcumulatedTime(Old.getTotalAcumulatedTime());
    }


    public static void playSound(Level level, BlockPos pos, int nextRate) {
        switch (nextRate) {
            case 1:
                playNoteBlockHarpSound(level, pos, NOTES[0]);
                break;
            case 2:
                playNoteBlockHarpSound(level, pos, NOTES[1]);
                break;
            case 4:
                playNoteBlockHarpSound(level, pos, NOTES[2]);
                break;
            case 8:
                playNoteBlockHarpSound(level, pos, NOTES[3]);
                break;
            case 16:
                playNoteBlockHarpSound(level, pos, NOTES[4]);
                break;
            case 32:
                playNoteBlockHarpSound(level, pos, NOTES[5]);
                break;
            case 64:
                playNoteBlockHarpSound(level, pos, NOTES[6]);
                break;
            case 128:
                playNoteBlockHarpSound(level, pos, NOTES[7]);
                break;
            case 256:
                playNoteBlockHarpSound(level, pos, NOTES[8]);
                break;
            case 512:
                playNoteBlockHarpSound(level, pos, NOTES[9]);
                break;
            default:
                playNoteBlockHarpSound(level, pos, NOTES[10]);
        }
    }

    public static void playNoteBlockHarpSound(Level level, BlockPos pos, String note) {
        // https://minecraft.gamepedia.com/Note_Block
        switch (note) {
            // Octave 1
            case "F#" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 0.5F);
            case "G" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 0.529732F);
            case "G#" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 0.561231F);
            case "A" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 0.594604F);
            case "A#" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 0.629961F);
            case "B" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 0.667420F);
            case "C" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 0.707107F);
            case "C#" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 0.749154F);
            case "D" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 0.793701F);
            case "D#" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 0.840896F);
            case "E" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 0.890899F);
            case "F" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 0.943874F);


            // Octave 2
            case "F#2" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 1F);
            case "G2" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 1.059463F);
            case "G#2" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 1.122462F);
            case "A2" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 1.189207F);
            case "A#2" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 1.259921F);
            case "B2" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 1.334840F);
            case "C2" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 1.414214F);
            case "C#2" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 1.498307F);
            case "D2" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 1.587401F);
            case "D#2" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 1.681793F);
            case "E2" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 1.781797F);
            case "F2" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 1.887749F);
            case "F#3" -> level.playSound(null, pos, SoundEvents.NOTE_BLOCK_HARP, SoundSource.BLOCKS, 0.5F, 2F);
        }
    }
}
