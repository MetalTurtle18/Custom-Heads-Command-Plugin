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

        if (command.getName().equalsIgnoreCase("customhead")) {
            ItemStack payment = new ItemStack(CustomHeadsCommand.paymentItem);
            if (player.getInventory().containsAtLeast(payment, CustomHeadsCommand.paymentAmount)) {
                player.sendMessage(ChatColor.GREEN + "You paid " + CustomHeadsCommand.paymentAmount + " X " + payment + " for the head named" + args[0]);
                giveHead(player, args[0]);
                return true;
            }
            player.sendMessage(ChatColor.RED + "You do not have sufficient payment. The set payment for this command is " + CustomHeadsCommand.paymentAmount + " X " + payment + ".");
            return true;
        }
        return true;
    }
    private void giveHead(Player player, String headName) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        //head.setData();
        player.getInventory().addItem(head);

    }
}
