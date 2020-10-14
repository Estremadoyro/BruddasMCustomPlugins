package org.bruddasmc.deathmessages.listeners;

import org.bruddasmc.deathmessages.Main;
import org.bruddasmc.deathmessages.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

	@SuppressWarnings("unused")
	private static Main plugin ; 
	
	@SuppressWarnings("static-access")
	public PlayerDeathListener (Main plugin) { 
		this.plugin = plugin ; 
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) { 
		if (e.getEntity().getKiller() instanceof Player) {
			Player killer = e.getEntity().getKiller() ; 
			Player player = e.getEntity() ; 
			
			killer.sendMessage(Utils.chat(
					"&bYou have killed &d" + player.getDisplayName()));
			player.sendMessage(Utils.chat(
					"&cYou have been killed by &d" + killer.getDisplayName() + " using&3 " + killer.getItemInHand().getType().toString().toLowerCase().substring(0, 1).toUpperCase() + killer.getItemInHand().getType().toString().toLowerCase().substring(1).replace("_", " ")));

			return ; 
		}
	}
}
