package com.metalturtle.customheadscommand.commands;

import com.metalturtle.customheadscommand.CustomHeadsCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
        // Check for command
        if (command.getName().equalsIgnoreCase("customhead")) {
            // If there was no player name specified the command will not try to run (and fail)
            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "You need to specify a player name to get their head!");
                return true;
            }
            // Get the item set in the config file
            ItemStack payment = new ItemStack(CustomHeadsCommand.paymentItem);
            // Check player's inventory for the payment
            if (player.getInventory().containsAtLeast(payment, CustomHeadsCommand.paymentAmount)) {
                player.sendMessage(ChatColor.GREEN + "You paid " + CustomHeadsCommand.paymentAmount + " x " + CustomHeadsCommand.paymentItemString + " for the head named " + args[0]);
                giveHead(player, args[0]);
                return true;
            }
            player.sendMessage(ChatColor.RED + "You do not have sufficient payment. The set payment for this command is " + CustomHeadsCommand.paymentAmount + " x " + CustomHeadsCommand.paymentItemString + ".");
            return true;
        }
        return true;
    }
    // Method to give the player head to the player
    private void giveHead(Player player, String headName) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        //head.setData();
        player.getInventory().addItem(head);

    }
}
