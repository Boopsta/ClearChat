package io.github.boop.clearchat.listeners

import io.github.boop.clearchat.ClearChatPlugin
import io.github.boop.clearchat.utilities.CC
import io.github.boop.clearchat.utilities.Random
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender


/**
 * @author Boop
 * @since 4/14/2022
 */
class ChatListener(private val plugin: ClearChatPlugin) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val prefix = "Prefix"
        if (args.isEmpty()) {
            for (i in 0..999) {
                Bukkit.broadcastMessage(Random.nextSpace(30))
            }
            Bukkit.broadcastMessage(
                CC.translate(
                    plugin.config.getString(prefix) + plugin.config.getString("Global")
                        ?.replace("%sender%", sender.name)
                )
            )
        } else if (args.size == 1) {
            if (Bukkit.getPlayer(args[0])?.isOnline == true) {
                for (i in 0..999) {
                    Bukkit.getPlayer(args[0])?.sendMessage(Random.nextSpace(30))
                }
                Bukkit.getPlayer(args[0])?.sendMessage(
                    CC.translate(
                        plugin.config.getString(prefix) + plugin.config.getString("Player")
                            ?.replace("%sender%", sender.name)
                    )
                )
                sender.sendMessage(
                    CC.translate(
                        plugin.config.getString(prefix) + plugin.config.getString("Success")
                            ?.replace("%person%", args[0])
                    )
                )
            } else {
                sender.sendMessage("${ChatColor.RED}Could not find specified player${ChatColor.RESET}")
            }
        } else if (args.size > 1) {
            sender.sendMessage("${ChatColor.RED}Please refrain from using 2 or more args${ChatColor.RESET}")
        }
        return true
    }
}
