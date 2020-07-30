package com.github.mori01231.itemban.CommandExecutor;

import com.github.mori01231.itemban.ItemBan;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;


public class ItemBanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){

            // Get the player
            Player player = (Player) sender;

            // Get the display name of item in player's main hand
            String mainHandItemDisplayName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();

            // Get current list from config
            List<String> BannedItems = ItemBan.getInstance().getConfig().getStringList("BannedItems.All");

            // Append new item to old list
            BannedItems.add(mainHandItemDisplayName);

            // Apply the edited list to config
            ItemBan.getInstance().getConfig().set("BannedItems.All", BannedItems);
        }else{
            FeedBack("コンソールからの実行はできません。");
        }

        return true;
    }

    public void FeedBack(String message){
        // print message to player
    }
}
