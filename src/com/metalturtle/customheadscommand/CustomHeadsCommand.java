package com.metalturtle.customheadscommand;

import com.metalturtle.customheadscommand.commands.Commands;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomHeadsCommand extends JavaPlugin {

    FileConfiguration config = this.getConfig();
    // Config items
    public static Material paymentItem;
    public static int paymentAmount;
    public static String paymentItemString;
    public static String noNameSpecified;
    public static String headGivenFree;
    public static String headGivenPaid;
    public static String noFunds;

    // Server object
    public Server server = getServer();

    @Override
    public void onEnable() {
        makeConfig();
        paymentItemString = config.getString("payment_type");
        paymentItem = Material.valueOf(paymentItemString.toUpperCase());
        paymentAmount = config.getInt("amount");
        initializeMessages();
        getCommand("customhead").setExecutor(new Commands());
        server.getConsoleSender().sendMessage(ChatColor.GREEN + "Custom Heads Command Plugin Loaded!");
    }

    @Override
    public void onDisable() {
        server.getConsoleSender().sendMessage(ChatColor.RED + "Custom Heads Command Plugin Unloaded!");
    }

    private void makeConfig() {
        config.addDefault("payment_type", "diamond");
        config.addDefault("amount", 2);
        config.addDefault("messages", "");
        config.addDefault("messages.no_name_specified", "You need to specify a player name to get their head!");
        config.addDefault("messages.head_given_free", "You got the head named %head_name%");
        config.addDefault("messages.head_given_paid", "You paid %payment_amount% x %payment_item% for the head named %head_name%");
        config.addDefault("messages.no_funds", "Insufficient funds. The set payment for this command is %payment_amount% x %payment_item%");
        config.options().copyDefaults(true);
        saveConfig();
    }

    public static String replacePlaceholders(String string, String placeholder, String replacement) {
        if (string != null)
            if (string.contains(placeholder)) {
                return string.replaceAll(placeholder, replacement);
            }
        return string;
    }

    private void initializeMessages() {
        noNameSpecified = replacePlaceholders(replacePlaceholders(config.getString("messages.no_name_specified"), "%payment_item%", paymentItemString), "%payment_amount%", "" + paymentAmount);
        headGivenFree = replacePlaceholders(replacePlaceholders(config.getString("messages.head_given_free"), "%payment_item%", paymentItemString), "%payment_amount%", "" + paymentAmount);
        headGivenPaid = replacePlaceholders(replacePlaceholders(config.getString("messages.head_given_paid"), "%payment_item%", paymentItemString), "%payment_amount%", "" + paymentAmount);
        noFunds = replacePlaceholders(replacePlaceholders(config.getString("messages.no_funds"), "%payment_item%", paymentItemString), "%payment_amount%", "" + paymentAmount);
    }
}
