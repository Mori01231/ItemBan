package com.github.mori01231.itemban.CommandExecutor;

import com.github.mori01231.itemban.ItemBan;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;


public class ItemBanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        List<String> BannedItems = ItemBan.getInstance().getConfig().getStringList("BannedItems.All");
        BannedItems.add(mainHandItem.getItemMeta().getDisplayName());

        ItemBan.getInstance().getConfig().set("BannedItems.All", BannedItems);

        return true;
    }
}
