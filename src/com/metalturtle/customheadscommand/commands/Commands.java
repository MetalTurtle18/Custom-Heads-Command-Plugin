package com.metalturtle.customheadscommand.commands;

import com.metalturtle.customheadscommand.CustomHeadsCommand;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            return true;
        }
        Player player = (Player) commandSender;

        if (command.getName().equalsIgnoreCase("customhead")) {
            CustomHeadsCommand plugin = new CustomHeadsCommand();
            FileConfiguration config = plugin.getConfigurationFile();
            String paymentItem = config.getString("payment");
            int paymentAmount = config.getInt("amount");
            ItemStack payment = new ItemStack(Material.valueOf(paymentItem), paymentAmount);
            if (player.getInventory().contains(payment)) {

            }
        }

        return true;
    }
}
