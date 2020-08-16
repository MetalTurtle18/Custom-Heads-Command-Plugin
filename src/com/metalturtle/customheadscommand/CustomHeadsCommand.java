package com.metalturtle.customheadscommand;

import com.metalturtle.customheadscommand.commands.Commands;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomHeadsCommand extends JavaPlugin {

    FileConfiguration config = this.getConfig();
    public static Material paymentItem;
    public static int paymentAmount;
    public Server server = getServer();

    @Override
    public void onEnable() {
        config.addDefault("payment", "diamond");
        config.addDefault("amount", 2);
        config.options().copyDefaults(true);
        saveConfig();
        paymentItem = Material.valueOf(config.getString("payment").toUpperCase());
        paymentAmount = config.getInt("amount");
        getCommand("customhead").setExecutor(new Commands());
        server.getConsoleSender().sendMessage(ChatColor.GREEN + "Custom Heads Command Plugin Loaded!");
        server.getConsoleSender().sendMessage(ChatColor.BLUE + "The set payment amount is " + paymentAmount);
    }

    @Override
    public void onDisable() {
        server.getConsoleSender().sendMessage(ChatColor.RED + "Custom Heads Command Plugin Unloaded!");
    }
}
