package me.tolgaozgun.topkillers.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.tolgaozgun.topkillers.util.Config;

public class JoinListener implements Listener {
	
	// private PluginMain plugin = PluginMain.getPlugin(PluginMain.class);

	@EventHandler
	public void onPlayerDeath(PlayerJoinEvent e) {
		Config.add(e.getPlayer().getUniqueId(),e.getPlayer().getName());
	}
	
}
