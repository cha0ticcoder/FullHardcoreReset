package com.github.cha0ticcoder.fullhardcorereset;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.json.simple.parser.JSONParser;

import java.lang.Override;
import java.util.Map;
import java.util.UUID;


@SuppressWarnings("UnstableApiUsage")
public class Main extends JavaPlugin implements Listener {

    private Map<String, ?> config;

    public enum PlayerResetType {
        ACHIEVEMENTS,
        STATISTICS,
        BOTH
    }

    public void onLoad() {

        this.saveDefaultConfig();
        this.reloadConfig();
        config = this.getConfig().getValues(false);

    }

    @Override
    public void onEnable() {

        LiteralCommandNode<CommandSourceStack> resetCommand = Commands.literal("reset")
                .then(Commands.literal("achievements")
                        .executes( context -> {

                            if (!resetAdvancements(null)) {
                                return 0;
                            }

                            return Command.SINGLE_SUCCESS;
                        })
                )
                .then(Commands.literal("statistics")
                        .executes( context -> {

                            if (!resetStatistics(null)) {
                                return 0;
                            }

                            return Command.SINGLE_SUCCESS;
                        })
                )
                .then(Commands.argument("target", ArgumentTypes.player())
                        .executes(context -> {
                            final PlayerSelectorArgumentResolver targetResolver = context.getArgument("target", PlayerSelectorArgumentResolver.class);
                            final Player target = targetResolver.resolve(context.getSource()).getFirst();

                            if (!resetPlayer(target, null)) {
                                return 0;
                            }

                            return Command.SINGLE_SUCCESS;
                        })
                )
                .then(Commands.literal("world")
                        .executes(context -> {

                            if (!resetWorld()) {
                                return 0;
                            }

                            return Command.SINGLE_SUCCESS;
                        })
                )
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

    public boolean resetPlayer(Player target, PlayerResetType option ) {

        if (option == null) {
            option = PlayerResetType.BOTH;
        }

        return switch (option) {
            case BOTH -> resetAdvancements(target) && resetStatistics(target);
            case ACHIEVEMENTS -> resetAdvancements(target);
            case STATISTICS -> resetStatistics(target);
        };
    }

    public boolean resetAdvancements(Player target) {

        if (target == null) {
            return false;
        }

        UUID targetUUID = target.getUniqueId();

        target.getWorld().getWorldFolder();

        if (target.isOnline()) {



        } else if (!target.isOnline()) {





            return true;
        }


        return false;
    }

    public boolean resetStatistics(Player target) {
        return false;
    }

    public boolean resetWorld() {
        return false;
    }

}