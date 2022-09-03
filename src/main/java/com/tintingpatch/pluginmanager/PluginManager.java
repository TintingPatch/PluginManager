package com.tintingpatch.pluginmanager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginManager extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginCommand("pluginmanager").setExecutor(new PluginManagerCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
