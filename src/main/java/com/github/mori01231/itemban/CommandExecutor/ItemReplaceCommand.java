package com.github.mori01231.itemban.CommandExecutor;

import com.github.mori01231.itemban.ItemBan;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class ItemReplaceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

    public void ReplaceItem(String ReplaceItem, String ReplacedItem){

        // Save replacement item.
        ItemBan.getInstance().getConfig().set(ReplaceItem, ReplacedItem);
    }
}
