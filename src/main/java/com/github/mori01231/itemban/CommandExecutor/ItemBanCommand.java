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
        }

        if(args.length == 0){

            // Get current list from config
            List<String> BannedItems = ItemBan.getInstance().getConfig().getStringList("BannedItems.All");

            // Append new item to old list
            BannedItems.add(mainHandItemDisplayName);

            // Apply the edited list to config
            ItemBan.getInstance().getConfig().set("BannedItems.All", BannedItems);

            return true;
        }

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("All")){

                // Get current list from config
                List<String> BannedItems = ItemBan.getInstance().getConfig().getStringList("BannedItems.All");

                // Append new item to old list
                BannedItems.add(mainHandItemDisplayName);

                // Apply the edited list to config
                ItemBan.getInstance().getConfig().set("BannedItems.All", BannedItems);
            }
            if(args[0].equalsIgnoreCase("OffHand")){
                // Get current list from config
                List<String> BannedItems = ItemBan.getInstance().getConfig().getStringList("BannedItems.OffHand");

                // Append new item to old list
                BannedItems.add(mainHandItemDisplayName);

                // Apply the edited list to config
                ItemBan.getInstance().getConfig().set("BannedItems.OffHand", BannedItems);
            }
            if(args[0].equalsIgnoreCase("Armor")){
                // Get current list from config
                List<String> BannedItems = ItemBan.getInstance().getConfig().getStringList("BannedItems.Armor");

                // Append new item to old list
                BannedItems.add(mainHandItemDisplayName);

                // Apply the edited list to config
                ItemBan.getInstance().getConfig().set("BannedItems.Armor", BannedItems);
            }
        }

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
