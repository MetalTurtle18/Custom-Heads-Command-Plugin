package com.metalturtle.customheadscommand;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomHeadsCommand extends JavaPlugin {

    FileConfiguration config = this.getConfig();

    public FileConfiguration getConfigurationFile() {
        return this.getConfig();
    }

    @Override
    public void onEnable() {
        config.addDefault("payment", "diamond");
        config.addDefault("amount", "2");
        config.options().copyDefaults(true);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Custom Heads Command Plugin Loaded!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Custom Heads Command Plugin Unloaded!");
    }
}
