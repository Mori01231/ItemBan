package com.github.mori01231.itemban.Listener;


import com.github.mori01231.itemban.ItemBan;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getLogger;

public class InventoryEventListener implements Listener {

    private ItemBan plugin;
    public InventoryEventListener(ItemBan plugin){
        this.plugin = plugin;
    }


    @EventHandler(priority = EventPriority.NORMAL)
    public void onInventoryOpenEvent(InventoryOpenEvent event) {
        // Get player name
        String playerName = event.getPlayer().getName();

        ItemStack OffHandItem = event.getPlayer().getInventory().getItemInOffHand();

        // Delete any item from event inventory
        for (ItemStack item: event.getInventory().getContents()) {
            try{
                for (String line : ItemBan.getInstance().getConfig().getStringList("BannedItems.All")) {
                    if(item.getItemMeta().getDisplayName().equals(line)){
                        item.setAmount(0);
                        Log("&3" + playerName + "の所持する" + line + "&3が削除されました。");
                    }
                }
            }catch (Exception e){
            }
        }

        // Delete any item from player inventory
        for (ItemStack item: event.getPlayer().getInventory().getContents()) {
            try{
                for (String line : ItemBan.getInstance().getConfig().getStringList("BannedItems.All")) {
                    if(item.getItemMeta().getDisplayName().equals(line)){
                        item.setAmount(0);
                        Log("&3" + playerName + "の所持する" + line + "&3が削除されました。");
                    }
                }
            }catch (Exception e){
            }
        }

        // Delete OffHand Item
        try{
            for (String line : ItemBan.getInstance().getConfig().getStringList("BannedItems.OffHand")) {
                if(OffHandItem.getItemMeta().getDisplayName().equals(line)){
                    OffHandItem.setAmount(0);
                    Log("&3" + playerName + "の所持するオフハンドアイテム：" + line + "&3が削除されました。");
                }
            }
        }catch (Exception e){
        }

        // Delete Armor Items
        try{
            for (ItemStack item: event.getPlayer().getInventory().getArmorContents()) {
                try{
                    for (String line : ItemBan.getInstance().getConfig().getStringList("BannedItems.Armor")) {
                        if(item.getItemMeta().getDisplayName().equals(line)){
                            item.setAmount(0);
                            Log("&3" + playerName + "の所持する防具：" + line + "&3が削除されました。");
                        }
                    }
                }catch (Exception e){
                }
            }
        }catch (Exception e){
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onInventoryClickEvent(InventoryClickEvent event) {
        // Get player name
        String playerName = event.getWhoClicked().getName();

        // Delete any item from event inventory
        for (ItemStack item: event.getClickedInventory().getContents()) {
            try{
                for (String line : ItemBan.getInstance().getConfig().getStringList("BannedItems.All")) {
                    if(item.getItemMeta().getDisplayName().equals(line)){
                        item.setAmount(0);
                        Log("&3" + playerName + "の所持する" + line + "&3が削除されました。");
                    }
                }
            }catch (Exception e){
            }
        }

        // Delete any item from player inventory
        for (ItemStack item: event.getWhoClicked().getInventory().getContents()) {
            try{
                for (String line : ItemBan.getInstance().getConfig().getStringList("BannedItems.All")) {
                    if(item.getItemMeta().getDisplayName().equals(line)){
                        item.setAmount(0);
                        Log("&3" + playerName + "の所持する" + line + "&3が削除されました。");
                    }
                }
            }catch (Exception e){
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {
        String playerName = event.getPlayer().getName();
        ItemStack mainHandItem = event.getPlayer().getInventory().getItem(event.getNewSlot());

        // Delete MainHand item
        try{
            for (String line : ItemBan.getInstance().getConfig().getStringList("BannedItems.MainHand")) {
                if(mainHandItem.getItemMeta().getDisplayName().equals(line)){
                    mainHandItem.setAmount(0);
                    Log("&3" + playerName + "の所持する" + line + "&3が削除されました。");
                }
            }
        }catch (Exception e){ }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event) {
        String playerName = event.getPlayer().getName();
        ItemStack offHandItem = event.getOffHandItem();

        // Delete offHand item
        try{
            for (String line : ItemBan.getInstance().getConfig().getStringList("BannedItems.OffHand")) {
                if(offHandItem.getItemMeta().getDisplayName().equals(line)){
                    offHandItem.setAmount(0);
                    Log("&3" + playerName + "の所持する" + line + "&3が削除されました。");
                }
            }
        }catch (Exception e){ }
    }

    public void Log(String message){
        getLogger().info(ChatColor.translateAlternateColorCodes('&',message));
    }
}
