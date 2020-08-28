package com.github.mori01231.itemban.CommandExecutor;

import com.github.mori01231.itemban.ItemBan;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import static org.bukkit.Bukkit.getLogger;

public class ReloadCommand implements CommandExecutor {

    private Player player;
    private Boolean isConsole;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if the sender is the console or a player
        if (sender instanceof Player) {
            isConsole = false; // Command was executed by a player
            player = (Player) sender; // Get the player
        }else{
            isConsole = true; // Command was executed by console
        }

        // Reload the config
        ItemBan.getInstance().reloadConfig();

        // Give feedback indicating the command was properly executed
        FeedBack("&3正常にリロードされました。");

        return true;
    }

    public void FeedBack(String message){
        // Send the feedback to different places depending on who the sender is
        if(isConsole){
            // send feedback to the console
            getLogger().info(ChatColor.translateAlternateColorCodes('&',message));
        }else{
            // send feedback to the player
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',message));
        }
    }
}
