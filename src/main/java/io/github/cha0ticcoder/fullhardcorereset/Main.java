package io.github.cha0ticcoder.fullhardcorereset;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import java.lang.Override;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register();
        });

        this.getLogger().info(String.format("SUCCESS! %s LOADED!", this.getName()));

    }

    @Override
    public void onDisable() {

        this.getLogger().info(String.format("SUCCESS! %s UNLOADED!", this.getName()));

    }

}