package com.github.cha0ticcoder.fullhardcorereset;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import java.lang.Override;
import java.lang.Exception;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        LiteralCommandNode<CommandSourceStack> resetCommand = Commands.literal("reset")
                .then(Commands.literal("achievements")
                        .executes( ctx -> {

                        })
                )
                .then(Commands.literal("statistics")
                        .executes( ctx -> {

                        }))
                .then(Commands.literal("player")
                        .then(Commands.argument("target", ArgumentTypes.player())
                                .executes( ctx -> {
                                    ctx.getArgument("target", Player.class);

                                    if (!resetPlayer()) {
                                        return 0;
                                    }

                                    return Command.SINGLE_SUCCESS;
                                })
                .then(Commands.literal("world")
                        .executes( ctx -> {

                        }))
                .build();

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(resetCommand);
        });

        this.getLogger().info("SUCCESSFULLY LOADED!");

    }

    @Override
    public void onDisable() {

        this.getLogger().info("SUCCESSFULLY UNLOADED!");

    }

    public boolean resetPlayer() {

    }

}