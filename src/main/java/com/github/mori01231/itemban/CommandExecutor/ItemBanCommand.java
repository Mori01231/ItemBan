package com.github.mori01231.itemban.CommandExecutor;

import com.github.mori01231.itemban.ItemBan;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

import java.util.List;


public class ItemBanCommand implements CommandExecutor {

    private Player player;
    private Boolean isConsole;
    private String mainHandItemDisplayName;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if sender is a player
        if (sender instanceof Player) {
            isConsole = false; // sender is player
            player = (Player) sender; // Get the player

            // Get the display name of item in player's main hand
            mainHandItemDisplayName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        }else{
            isConsole = true; // sender is console
            FeedBack("&cコンソールからの実行はできません。"); // Send error message
            return true; // Finish command execution
        }

        if(args.length == 0){
            AddItem("BannedItems"); // Adding banned item in All category
            FeedBack("&3正常に" + mainHandItemDisplayName + "&3が禁止アイテムとして登録されました。"); // Send confirmation message
            return true; // Finish command execution
        }

        // Too many arguments
        FeedBack("&c引数が多すぎます。"); // Send error message
        return true; // Finish command execution
    }

    public void AddItem(String path){
        // Get current list from config
        List<String> BannedItems = ItemBan.getInstance().getConfig().getStringList(path);

        // Append new item to old list
        BannedItems.add(mainHandItemDisplayName);

        // Apply the edited list to config
        ItemBan.getInstance().getConfig().set(path, BannedItems);

        // Save the config
        ItemBan.getInstance().saveConfig();
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
