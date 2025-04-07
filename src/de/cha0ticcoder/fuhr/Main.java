package de.cha0ticcoder.fuhr;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.Override;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        // Need Code here

        Bukkit.getLogger().info(String.format("SUCCESS! %s LOADED!", this.getName()));

    }

    @Override
    public void onDisable() {

    }

}