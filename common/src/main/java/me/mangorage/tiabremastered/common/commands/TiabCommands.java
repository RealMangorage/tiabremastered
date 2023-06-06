package me.mangorage.tiabremastered.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.player.Player;

public class TiabCommands {
    /**
     * /tiab setTime/removeTime/addTime PLAYER amount true/false
     * /tiab setTime/removeTime/addTime amount true/false
     * @param commandDispatcher
     */


    public static void register(CommandDispatcher<CommandSourceStack> commandDispatcher) {
        commandDispatcher.register(((Commands.literal("tiab")
                .then(Commands.literal("setTime")
                        .requires((stack) -> {
                            return stack.hasPermission(2);
                        })
                        .then(Commands.argument("player", EntityArgument.players())
                                .then(Commands.argument("amount", IntegerArgumentType.integer(0, Integer.MAX_VALUE))
                                        .then(Commands.argument("affecttotaltime", BoolArgumentType.bool())
                                                .executes((st) -> {
                                                    return 1;
                                                }))
                                        .executes((st) -> {
                                            return 1;
                                        }))
                        )))));
    }

    private static void handleTime(CommandSourceStack stack, Player player, int amount, boolean remove, boolean affectTotaltime) {

    }
}
