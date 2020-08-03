package com.github.mori01231.itemban.CommandExecutor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.mori01231.itemban.ItemBan.getInstance;
import static org.bukkit.Bukkit.getLogger;


public class ItemReplaceCommand implements CommandExecutor {

    private Player player;
    private Boolean isConsole;
    private String mainHandItemDisplayName;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

    public void ReplaceItem(String ReplaceItem, String ReplacedItem){

        // Save replacement item.
        getInstance().getConfig().set(ReplaceItem, ReplacedItem);
    }

    public void FeedBack(String message){
        if(isConsole){
            getLogger().info(ChatColor.translateAlternateColorCodes('&',message));
        }else{
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',message));
        }
    }
}
