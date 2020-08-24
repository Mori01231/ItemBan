package com.github.mori01231.itemban.CommandExecutor;

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

        if (sender instanceof Player) {
            isConsole = false;
            player = (Player) sender; // Get the player
        }else{
            isConsole = true;
        }

        FeedBack("&3正常にリロードされました。");

        return true;
    }

    public void FeedBack(String message){
        if(isConsole){
            getLogger().info(ChatColor.translateAlternateColorCodes('&',message));
        }else{
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',message));
        }
    }
}
