package com.github.cha0ticcoder.fullhardcorereset;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
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
import org.shanerx.mojang.Mojang;

import java.lang.Override;
import java.util.Map;
import java.util.UUID;


@SuppressWarnings("UnstableApiUsage")
public class Main extends JavaPlugin implements Listener {

    private Map<String, ?> config;

    private final Mojang mojangAPI = new Mojang().connect();

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
                .then(Commands.literal("advancements")
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
                .then(Commands.literal("world")
                        .executes(context -> {

                            if (!resetWorld()) {
                                return 0;
                            }

                            return Command.SINGLE_SUCCESS;
                        })
                )
                .then(Commands.argument("onlinePlayer", ArgumentTypes.player())
                        .executes(context -> {
                            final PlayerSelectorArgumentResolver targetResolver = context.getArgument("onlinePlayer", PlayerSelectorArgumentResolver.class);
                            final Player onlinePlayer = targetResolver.resolve(context.getSource()).getFirst();

                            if (!resetPlayer(onlinePlayer, null)) {
                                return 0;
                            }

                            return Command.SINGLE_SUCCESS;
                        })
                )
                .then(Commands.argument("offlinePlayer", StringArgumentType.word()))
                        .executes( context -> {
                            final String offlinePlayerUsername = context.getArgument("offlinePlayer", String.class);

                            Player offlinePlayer = getPlayerByUsername(offlinePlayerUsername);

                            if (!resetPlayer(offlinePlayer, null)) {
                                return 0;
                            }

                            return Command.SINGLE_SUCCESS;
                        })
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

        boolean targetIsOnline = target.isOnline();

        target.getWorld().getWorldFolder();

        if (targetIsOnline) {



        } else if (!targetIsOnline) {



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

    public Player getPlayerByUsername(String username) {

        Player target = null;

        try {
            target = Bukkit.getPlayer(UUID.fromString(mojangAPI.getUUIDOfUsername(username)));
        } catch (Exception e) {
            this.getLogger().severe("API Request to Mojang failed: \n" + e.getMessage());
        }

        return target;
    }

}