package com.github.mori01231.itemban.Listener;


import com.github.mori01231.itemban.ItemBan;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getLogger;

public class InventoryEventListener implements Listener {

    private ItemBan plugin;
    public InventoryEventListener(ItemBan plugin){
        this.plugin = plugin;
    }


    @EventHandler(priority = EventPriority.NORMAL)
    public void onInventoryOpenEvent(InventoryOpenEvent event) {
        Inventory inv = event.getInventory();
        String playerName = event.getPlayer().getName();

        for (ItemStack item: inv.getContents()) {
            try{
                for (String line : ItemBan.getInstance().getConfig().getStringList("BannedItems.All")) {
                    if(item.getItemMeta().getDisplayName().equals(line)){
                        item.setAmount(0);
                        Log("&3" + playerName + "の所持する" + line + "が削除されました。");
                    }
                }
            }catch (Exception e){
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        Inventory inv = event.getInventory();
        String playerName = event.getPlayer().getName();

        ItemStack OffHandItem = event.getPlayer().getInventory().getItemInOffHand();


        // Delete OffHand Item
        try{
            for (String line : ItemBan.getInstance().getConfig().getStringList("BannedItems.OffHand")) {
                if(OffHandItem.getItemMeta().getDisplayName().equals(line)){
                    OffHandItem.setAmount(0);
                    Log("&3" + playerName + "の所持するオフハンドアイテム：" + line + "が削除されました。");
                }
            }
        }catch (Exception e){
        }

        // Delete Armor Item
        try{
            for (ItemStack item: event.getPlayer().getInventory().getArmorContents()) {
                try{
                    for (String line : ItemBan.getInstance().getConfig().getStringList("BannedItems.Armor")) {
                        if(item.getItemMeta().getDisplayName().equals(line)){
                            item.setAmount(0);
                            Log("&3" + playerName + "の所持する防具：" + line + "が削除されました。");
                        }
                    }
                }catch (Exception e){
                }
            }
        }catch (Exception e){
        }

    }

    public void Log(String message){
        getLogger().info(ChatColor.translateAlternateColorCodes('&',message));
    }
}
