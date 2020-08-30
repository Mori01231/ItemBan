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


        // Delete items from event inventory
        for (ItemStack item: event.getInventory().getContents()) {
            try{
                if(ItemBan.getInstance().getConfig().getStringList("BannedItems").contains(item.getItemMeta().getDisplayName())) {
                    item.setAmount(0);
                    Log("&3" + playerName + "の所持する" + item.getItemMeta().getDisplayName() + "&3が削除されました。");
                }
            }catch (Exception e){
            }
        }

        // Delete items from player inventory
        for (ItemStack item: event.getPlayer().getInventory().getContents()) {
            try{
                if(ItemBan.getInstance().getConfig().getStringList("BannedItems").contains(item.getItemMeta().getDisplayName())) {
                    item.setAmount(0);
                    Log("&3" + playerName + "の所持する" + item.getItemMeta().getDisplayName() + "&3が削除されました。");
                }
            }catch (Exception e){
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onInventoryClickEvent(InventoryClickEvent event) {

        // define playerName
        String playerName = "";

        try {
            // Get player name
            playerName = event.getWhoClicked().getName();
        }catch(Exception e){
            Log("&3ItemBanプラグインでクリック時にプレイヤー名の取得に失敗");
        }


        // Delete items from event inventory
        for (ItemStack item: event.getClickedInventory().getContents()) {
            try{
                if(ItemBan.getInstance().getConfig().getStringList("BannedItems").contains(item.getItemMeta().getDisplayName())) {
                    item.setAmount(0);
                    Log("&3" + playerName + "の所持する" + item.getItemMeta().getDisplayName() + "&3が削除されました。");
                }
            }catch (Exception e){
            }
        }

        // Delete items from player inventory
        for (ItemStack item: event.getWhoClicked().getInventory().getContents()) {
            try{
                if(ItemBan.getInstance().getConfig().getStringList("BannedItems").contains(item.getItemMeta().getDisplayName())) {
                    item.setAmount(0);
                    Log("&3" + playerName + "の所持する" + item.getItemMeta().getDisplayName() + "&3が削除されました。");
                }
            }catch (Exception e){
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {
        String playerName = event.getPlayer().getName(); // get player name
        ItemStack mainHandItem = event.getPlayer().getInventory().getItem(event.getNewSlot()); // get main hand item
        ItemStack offHandItem = event.getPlayer().getInventory().getItem(event.getPreviousSlot()); // get off hand item

        try{
            // Delete MainHand item
            for (String line : ItemBan.getInstance().getConfig().getStringList("BannedItems")) {
                if(mainHandItem.getItemMeta().getDisplayName().equals(line)){
                    mainHandItem.setAmount(0);
                    Log("&3" + playerName + "の所持する" + line + "&3が削除されました。");
                }
            }
            // Delete OffHand item
            for (String line : ItemBan.getInstance().getConfig().getStringList("BannedItems")) {
                if(offHandItem.getItemMeta().getDisplayName().equals(line)){
                    offHandItem.setAmount(0);
                    Log("&3" + playerName + "の所持する" + line + "&3が削除されました。");
                }
            }
        }catch (Exception e){ }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event) {
        String playerName = event.getPlayer().getName(); // get player name
        ItemStack offHandItem = event.getOffHandItem(); // get off hand item

        try{
            // Delete offHand item
            for (String line : ItemBan.getInstance().getConfig().getStringList("BannedItems")) {
                if(offHandItem.getItemMeta().getDisplayName().equals(line)){
                    offHandItem.setAmount(0);
                    Log("&3" + playerName + "の所持する" + line + "&3が削除されました。");
                }
            }
        }catch (Exception e){ }
    }

    // log message in console
    public void Log(String message){
        getLogger().info(ChatColor.translateAlternateColorCodes('&',message));
    }
}
