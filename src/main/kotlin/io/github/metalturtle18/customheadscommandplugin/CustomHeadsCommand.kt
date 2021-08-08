/*
 * Copyright 2021 MetalTurtle18
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.metalturtle18.customheadscommandplugin

import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.chat.sendMessage
import net.axay.kspigot.extensions.console
import net.axay.kspigot.main.KSpigot
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import kotlin.properties.Delegates

class CustomHeadsCommand : KSpigot() {

    private lateinit var paymentItem: Material
    private var paymentAmount by Delegates.notNull<Int>()

    override fun startup() {
        // Handle config file stuff
        config.addDefault("payment", "diamond")
        config.addDefault("amount", 2)
        config.options().copyDefaults(true)
        saveConfig()
        paymentItem = try {
            Material.valueOf(config.getString("payment", "diamond")!!.uppercase())
        } catch (e: IllegalArgumentException) {
            Material.DIAMOND
        }
        paymentAmount = config.getInt("amount")

        // Create the head command
        getCommand("customhead")?.setExecutor { sender, _, _, args ->
            if (sender is Player) {
                if (args.isEmpty())
                    sender.sendMessage(
                        literalText(
                            if (paymentAmount > 0)
                                "The set payment for this command is $paymentAmount x ${
                                    paymentItem.name.replace(
                                        "_",
                                        " "
                                    ).lowercase()
                                }."
                            else
                                "This command is free."
                        ) {
                            color = KColors.AQUA
                        }
                    )
                else if (sender.gameMode == GameMode.CREATIVE || paymentAmount <= 0 || sender.takePayment()) {
                    sender.giveHead(args[0])
                    sender.sendMessage(
                        literalText("You were given ${args[0]}'s head.") {
                            color = KColors.GREEN
                        }
                    )
                } else
                    sender.sendMessage(
                        literalText(
                            "Insufficient funds. The set payment for this command is $paymentAmount x ${
                                paymentItem.name.replace(
                                    "_",
                                    " "
                                ).lowercase()
                            }."
                        ) {
                            color = KColors.RED
                        }
                    )
            }
            true
        }

        // Inform the console that the plugin is finished setting up
        console.sendMessage(
            literalText("Custom Heads Command Plugin loaded.") {
                color = KColors.GREEN
            }
        )
    }

    /**
     * Returns whether the player has the necessary payment for a head and takes the payment if they do
     * @receiver the player to check for the payment
     * @return whether the player has the configured payment
     */
    private fun Player.takePayment(): Boolean =
        if (inventory.containsAtLeast(ItemStack(paymentItem), paymentAmount)) {
            inventory.removeItem(ItemStack(paymentItem, paymentAmount))
            true
        } else {
            false
        }

    /**
     * Gives the player the head with the specified name
     * @receiver the player to give the head to
     * @param name the name of the head to give the player
     */
    private fun Player.giveHead(name: String) =
        inventory.addItem(
            ItemStack(Material.PLAYER_HEAD).apply {
                itemMeta = (Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD) as SkullMeta)
                    .apply {
                        owningPlayer =
                            server.getOfflinePlayer(name) // This is deprecated, but I don't have a better solution
                    }
            }
        )
}