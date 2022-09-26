package com.alexandre.lobby.event;

import com.alexandre.lobby.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
		Main.getInstance().onJoin(event.getPlayer());
    }

}
