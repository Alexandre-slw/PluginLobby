package com.alexandre.lobby;

import com.alexandre.core.api.inventory.ItemBuilder;
import com.alexandre.core.players.GamePlayer;
import com.alexandre.core.players.GamePlayerModifier;
import com.alexandre.core.players.GamePlayersRegistry;
import com.alexandre.core.utils.InventoryManager;
import com.alexandre.lobby.manager.EventManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    private static int spawnX = 0;
    private static int spawnY = 64;
    private static int spawnZ = 0;
    private static int minHeight = 0;

    private static final InventoryManager INVENTORY = new InventoryManager();

    static {
        INVENTORY.add(4, new ItemBuilder(Material.COMPASS).build(), "§fGame selector §7(Right click)");
    }

    @Override
    public void onEnable() {
        Main.instance = this;

        this.saveDefaultConfig();
        Main.spawnX = this.getConfig().getInt("spawn.x", 0);
        Main.spawnY = this.getConfig().getInt("spawn.y", 64);
        Main.spawnZ = this.getConfig().getInt("spawn.z", 0);
        Main.minHeight = this.getConfig().getInt("spawn.min_height", 0);

        new EventManager().registers();

        for (Player player : Bukkit.getOnlinePlayers()) {
            this.onJoin(player);
        }

        GamePlayersRegistry.setModifier(new GamePlayerModifier() {
            @Override
            public GamePlayer onAddPlayer(GamePlayer player) {
                for (GamePlayer gamePlayer : GamePlayersRegistry.getPlayers()) {
                    Main.getInstance().sendScoreboard(gamePlayer);
                }

                Main.getInstance().sendScoreboard(player);
                return super.onAddPlayer(player);
            }
        });
    }

    public void onJoin(Player player) {
        player.getWorld().setAutoSave(false);
        player.teleport(new Location(player.getWorld(), Main.spawnX + 0.5F, Main.spawnY + 1, Main.spawnZ + 0.5F));

        player.setGameMode(GameMode.ADVENTURE);

        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);

        player.setMaxHealth(20);
        player.setHealth(20);

        InventoryManager.setInventory(player, Main.INVENTORY);
    }

    public void sendScoreboard(GamePlayer player) {
        if (player == null) return;
        player.setScoreboard("ExampleMC",
                "",
                "§fLobby §7#" + (Bukkit.getPort() - 25500 + 1),
                "§e" + Bukkit.getOnlinePlayers().size() + " player" + (Bukkit.getOnlinePlayers().size() > 1 ? "s" : "") + " §7on this lobby",
                "",
                "§eplay.example.com"
        );
    }

    public static Main getInstance() {
        return Main.instance;
    }

    public static InventoryManager getInventory() {
        return Main.INVENTORY;
    }

    public static int getMinHeight() {
        return minHeight;
    }
}
