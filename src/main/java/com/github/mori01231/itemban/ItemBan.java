package com.github.mori01231.itemban;

import com.github.mori01231.itemban.CommandExecutor.ItemBanCommand;
import com.github.mori01231.itemban.Listener.InventoryClickEvent;
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
        getLogger().info("LifeCore has been enabled.");
        this.getCommand("itemban").setExecutor(new ItemBanCommand());



        this.saveDefaultConfig();

        registerEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerEvents(){

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new InventoryClickEvent(this),  this);
    }
}
