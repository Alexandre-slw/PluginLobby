package com.alexandre.lobby.manager;

import com.alexandre.lobby.Main;
import com.alexandre.lobby.event.EventCancel;
import com.alexandre.lobby.event.EventJoin;
import com.alexandre.lobby.event.InventoryEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventManager {

    public void registers() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EventCancel(), Main.getInstance());
        pm.registerEvents(new EventJoin(), Main.getInstance());
        pm.registerEvents(new InventoryEvent(), Main.getInstance());
    }

}
