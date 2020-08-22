package com.github.mori01231.itemban;

import com.github.mori01231.itemban.CommandExecutor.ItemBanCommand;
import com.github.mori01231.itemban.CommandExecutor.ItemReplaceCommand;
import com.github.mori01231.itemban.Listener.InventoryEventListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemBan extends JavaPlugin {

    private static ItemBan instance;

    public ItemBan (){
        instance = this;
    }

    public static ItemBan getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("ItemBan has been enabled.");
        this.getCommand("itembanadd").setExecutor(new ItemBanCommand());

        // Currently disabled.
        // this.getCommand("itemreplaceadd").setExecutor(new ItemReplaceCommand());

        this.saveDefaultConfig();

        registerEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("ItemBan has been disabled.");
    }

    public void registerEvents(){

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new InventoryEventListener(this),  this);
    }
}
