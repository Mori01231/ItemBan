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

        if (sender instanceof Player) {
            isConsole = false;
            player = (Player) sender; // Get the player

            // Get the display name of item in player's main hand
            mainHandItemDisplayName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        }else{
            isConsole = true;
            FeedBack("&cコンソールからの実行はできません。"); // Send error message
            return true;
        }

        if(args.length == 0){
            AddItem("All"); // Adding banned item in All category
            return true;
        }

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("All")){
                AddItem("All"); // Adding banned item in All category
                return true;
            }
            if(args[0].equalsIgnoreCase("OffHand")){
                AddItem("OffHand"); // Adding banned item in OffHand category
                return true;
            }
            if(args[0].equalsIgnoreCase("Armor")){
                AddItem("Armor"); // Adding banned item in Armor category
                return true;
            }
            if(args[0].equalsIgnoreCase("MainHand")){
                AddItem("MainHand"); // Adding banned item in Armor category
                return true;
            }
        }

        FeedBack("&c引数が多すぎます。");
        return true;
    }

    public void AddItem(String path){
        // Get current list from config
        List<String> BannedItems = ItemBan.getInstance().getConfig().getStringList(path);

        // Append new item to old list
        BannedItems.add(mainHandItemDisplayName);

        // Apply the edited list to config
        ItemBan.getInstance().getConfig().set(path, BannedItems);
    }

    public void FeedBack(String message){
        if(isConsole){
            getLogger().info(ChatColor.translateAlternateColorCodes('&',message));
        }else{
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',message));
        }
    }
}
