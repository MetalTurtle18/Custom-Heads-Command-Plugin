package com.metalturtle.customheadscommand.commands;

import com.metalturtle.customheadscommand.CustomHeadsCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
            ItemStack payment = new ItemStack(CustomHeadsCommand.paymentItem);
            if (player.getInventory().containsAtLeast(payment, CustomHeadsCommand.paymentAmount)) {
                player.sendMessage("You have the payment");
            }
            return true;
        }
        player.sendMessage("You do not have the payment");
        return true;
    }
}
