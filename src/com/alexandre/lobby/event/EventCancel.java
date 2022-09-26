package com.alexandre.lobby.event;

import com.alexandre.lobby.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

public class EventCancel implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if (event.getPlayer().getLocation().getY() < Main.getMinHeight()) Main.getInstance().onJoin(event.getPlayer());
	}

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
    	event.setCancelled(true);
    }

    @EventHandler
    public void onBlockDispense(BlockDispenseEvent event) {
    	event.setCancelled(true);
    }
    
	@EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
    	event.setCancelled(true);
    }
    
    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
    	event.setCancelled(true);
    }
    
    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
    	event.setCancelled(true);
    }
    
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
		event.setCancelled(event.getPlayer().getGameMode() != GameMode.CREATIVE);
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {    	
    	if (event.getItem() != null && (event.getItem().getType().toString().replace("_", "").equalsIgnoreCase("snowball") || event.getItem().getType() == Material.BOW)) {
			return;
		}
    	
    	this.repearItem(event.getPlayer());
    	if (event.getAction() == Action.PHYSICAL) {
    		event.setCancelled(true);
    		return;
    	}
    	if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;

		event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
    	event.setCancelled(event.getPlayer().getGameMode() != GameMode.CREATIVE);
    }
    
	@EventHandler
    public void onExplode(EntityExplodeEvent event) {
		event.setCancelled(true);
    }

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerItemDamage(PlayerItemDamageEvent event) {
		event.setDamage(0);
		event.setCancelled(true);
		this.repearItem(event.getPlayer());
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.getPlayer() == null) {
			event.setCancelled(true);
			return;
		}
		
		this.repearItem(event.getPlayer());
		event.setCancelled(event.getPlayer().getGameMode() != GameMode.CREATIVE);
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onHitPlayer(EntityDamageByEntityEvent event) {
		event.setCancelled(true);
	}
	
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
    	if (event.getCurrentItem() != null) event.getCurrentItem().setDurability((short) 0);
    	event.setCancelled(event.getWhoClicked().getGameMode() != GameMode.CREATIVE);
    }
    
    public void repearItem(Player player) {
    	ItemStack item = player.getInventory().getItemInHand();
    	if (item != null) item.setDurability((short) 0);
    	player.updateInventory();
    }

    @EventHandler
    public void onPlayerArmorStandManipulation(PlayerArmorStandManipulateEvent event) {
    	event.setCancelled(event.getPlayer().getGameMode() != GameMode.CREATIVE);
    }

}
