package com.alexandre.lobby.gui;

import com.alexandre.core.api.inventory.FastInv;
import com.alexandre.core.api.inventory.ItemBuilder;
import com.alexandre.core.players.GamePlayer;
import com.alexandre.core.utils.BungeeCordUtils;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class GuiGames extends FastInv {

    GamePlayer player;

    public GuiGames(GamePlayer player) {
        super(9 * 3, "Game selector");
        this.player = player;

        this.setItem(13, new ItemBuilder(Material.BED).name("§fBedwars Solo").flags().build());

        this.init();
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);

        switch (event.getSlot()) {
            case 13:
                BungeeCordUtils.joinGame(BungeeCordUtils.GameType.BEDWARS_SOLO, this.player);
                this.player.open(null);
                break;
        }
    }

}
