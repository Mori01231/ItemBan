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
            ReplaceItem("All"); // Adding banned item in All category
            return true;
        }

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("All")){
                ReplaceItem("All"); // Adding banned item in All category
                return true;
            }
            if(args[0].equalsIgnoreCase("OffHand")){
                ReplaceItem("OffHand"); // Adding banned item in OffHand category
                return true;
            }
            if(args[0].equalsIgnoreCase("Armor")){
                ReplaceItem("Armor"); // Adding banned item in Armor category
                return true;
            }
        }

        FeedBack("&c引数が多すぎます。");
        return true;
    }

    public void ReplaceItem(String Category){

        // Save replacement item.
        //getInstance().getConfig().set(Category.);
    }

    public void FeedBack(String message){
        if(isConsole){
            getLogger().info(ChatColor.translateAlternateColorCodes('&',message));
        }else{
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',message));
        }
    }
}
