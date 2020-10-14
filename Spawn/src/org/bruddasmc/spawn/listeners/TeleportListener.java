package org.bruddasmc.spawn.listeners;

import org.bruddasmc.spawn.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class TeleportListener implements Listener{
	
	@SuppressWarnings("unused")
	private Main plugin ; 
	
	public TeleportListener (Main plugin) { 
		this.plugin = plugin ;
		Bukkit.getPluginManager().registerEvents(this, plugin) ; 
	}
	
}
