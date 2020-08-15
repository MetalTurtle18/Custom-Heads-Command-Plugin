package com.metalturtle.customheadscommand;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomHeadsCommand extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Custom Heads Command Plugin Loaded!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Custom Heads Command Plugin Unloaded!");
    }
}
