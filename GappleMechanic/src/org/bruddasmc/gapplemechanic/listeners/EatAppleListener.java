package org.bruddasmc.gapplemechanic.listeners;

import org.bruddasmc.gapplemechanic.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class EatAppleListener implements Listener{

	private Main plugin; 
	
	public EatAppleListener (Main plugin) { 
		this.plugin = plugin ; 
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerApppleConsumeEvent (PlayerItemConsumeEvent e) { 
		
		Player p = e.getPlayer() ;
		Integer food = p.getFoodLevel() ; 
		ItemStack item = e.getItem() ; 
		
		if (item.getType() == Material.APPLE) { 
			p.setFoodLevel((food - 4) + plugin.getConfig().getInt("appleFoodRegenLevel"));
		}
	}
}
