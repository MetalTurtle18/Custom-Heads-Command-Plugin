package com.metalturtle.customheadscommand.commands;

import com.metalturtle.customheadscommand.CustomHeadsCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            return true;
        }
        Player player = (Player) commandSender;
            // If there was no player name specified the command will not try to run (and fail)
            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "You need to specify a player name to get their head!");
                return true;
            }
            // Get the item set in the config file
            ItemStack payment = new ItemStack(CustomHeadsCommand.paymentItem);
            int paymentAmount = CustomHeadsCommand.paymentAmount;
            ItemStack fullPayment = new ItemStack(CustomHeadsCommand.paymentItem, paymentAmount);
            // If the player is in creative mode they do not need to pay
            if (player.getGameMode() == GameMode.CREATIVE || paymentAmount <= 0) {
                giveHead(player, args[0]);
                player.sendMessage(ChatColor.GREEN + "You got the head named " + args[0]);
                return true;
            }
            // Check player's inventory for the payment
            if (player.getInventory().containsAtLeast(payment, paymentAmount)) {
                player.sendMessage(ChatColor.GREEN + "You paid " + paymentAmount + " x " + CustomHeadsCommand.paymentItemString + " for the head named " + args[0]);
                giveHead(player, args[0]);
                player.getInventory().removeItem(fullPayment);
                return true;
            }
            player.sendMessage(ChatColor.RED + "Insufficient funds. The set payment for this command is " + CustomHeadsCommand.paymentAmount + " x " + CustomHeadsCommand.paymentItemString + ".");
            return true;
    }
    // Method to give the player head to the player
    private void giveHead(Player player, String headName) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
        OfflinePlayer playerName = player.getServer().getOfflinePlayer(headName);
        meta.setOwningPlayer(playerName);
        head.setItemMeta(meta);
        player.getInventory().addItem(head);

    }
}
