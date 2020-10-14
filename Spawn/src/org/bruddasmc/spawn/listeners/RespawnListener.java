package org.bruddasmc.spawn.listeners;

import org.bruddasmc.spawn.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener{

	private Main plugin ;
	
	public RespawnListener (Main plugin) { 
		this.plugin = plugin ; 
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) { 
		Location l = Bukkit.getServer().getWorld(plugin.getConfig().getString("defaultRespawn")).getSpawnLocation() ;
		e.setRespawnLocation(l); 
	}
}
 