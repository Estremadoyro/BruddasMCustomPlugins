package org.bruddasmc.listeners.join;

import org.bruddasmc.listeners.Main;
import org.bruddasmc.listeners.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener{

	@SuppressWarnings("unused")
	private Main plugin ; 
	
	public JoinListener(Main plugin) { 
		this.plugin = plugin ;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) { 
		Player player = e.getPlayer() ; 
		
		if (!player.hasPlayedBefore()) { 
			//DisplayName name with prefix, suffix, nick
			Bukkit.broadcastMessage(
					Utils.chat(plugin.getConfig().getString("firstjoin_message").replace("<player>", player.getName()))) ; 
		} else { 
			Bukkit.broadcastMessage(
					Utils.chat(plugin.getConfig().getString("join_message").replace("<player>", player.getName()))) ;
			
		}
	}
}
