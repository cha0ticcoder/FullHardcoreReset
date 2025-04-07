package io.github.cha0ticcoder.fullhardcorereset;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import java.lang.Override;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        // Need Code here

        Bukkit.getLogger().info(String.format("SUCCESS! %s LOADED!", this.getName()));

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(String.format("SUCCESS! %s UNLOADED!", this.getName()));
    }

}