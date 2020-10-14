package org.bruddasmc.kitpvpplaceholders.ph;

import org.bruddasmc.kitpvpplaceholders.Main;
import org.bruddasmc.kitpvpplaceholders.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;

public class Placeholders implements Listener{

	@SuppressWarnings("unused")
	private Main plugin ;
	
	public Placeholders (Main plugin) {
		this.plugin = plugin  ;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
	}
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e) {
		@SuppressWarnings("unused")
		Player p = e.getPlayer() ;
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			PlaceholderAPI.registerPlaceholderHook("bpvp", new PlaceholderHook() {
				@Override
				public String onRequest(OfflinePlayer p, String params) {
					if (p != null && p.isOnline()) {
						return onPlaceholderRequest(p.getPlayer(), params) ; 
					}
					return null;
				}
				@Override 
				public String onPlaceholderRequest(Player p, String params) {
					if (p == null) {
						
						return null ;	
					}
					String p_level = PlaceholderAPI.setPlaceholders(p, "%kitpvp_stats_level%");
					if (params.equalsIgnoreCase("level")) {
						int p_level_int = Integer.parseInt(p_level);
						if (p_level_int >= 1 && p_level_int <= 2) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&e"+p_level_s); 
						}else if (p_level_int >= 3 && p_level_int <= 4) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&a"+p_level_s); 
						}else if (p_level_int >= 5 && p_level_int <= 9) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&b"+p_level_s);
						}else if (p_level_int >= 10 && p_level_int <= 14) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&9"+p_level_s);
						}else if (p_level_int >= 15 && p_level_int <= 19) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&5"+p_level_s);
						}else if (p_level_int >= 20 && p_level_int <= 24) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&d"+p_level_s);
						}else if (p_level_int >= 25 && p_level_int <= 29) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&c"+p_level_s);
						}else if (p_level_int >= 30 && p_level_int <= 34) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&6"+p_level_s);
						}else if (p_level_int >= 35 && p_level_int <= 39) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&e&l"+p_level_s);
						}else if (p_level_int >= 40 && p_level_int <= 44) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&a&l"+p_level_s);
						}else if (p_level_int >= 45 && p_level_int <= 49) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&b&l"+p_level_s);
						}else if (p_level_int >= 50 && p_level_int <= 54) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&9&l"+p_level_s);
						}else if (p_level_int >= 55 && p_level_int <= 59) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&5&l"+p_level_s);
						}else if (p_level_int >= 60 && p_level_int <= 64) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&d&l"+p_level_s);
						}else if (p_level_int >= 65 && p_level_int <= 69) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&c&l"+p_level_s);
						}else if (p_level_int >= 70 && p_level_int <= 74) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&6&l"+p_level_s);
						}else if (p_level_int >= 75 && p_level_int <= 79) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&e&l&o"+p_level_s);
						}else if (p_level_int >= 80 && p_level_int <= 84) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&a&l&o"+p_level_s);
						}else if (p_level_int >= 85 && p_level_int <= 89) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&b&l&o"+p_level_s);
						}else if (p_level_int >= 90 && p_level_int <= 94) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&9&l&o"+p_level_s);
						}else if (p_level_int >= 95 && p_level_int <= 98) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&5&l&o"+p_level_s);
						}else if (p_level_int == 99) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&d&l&o"+p_level_s);
						}else if (p_level_int == 100) {
							String p_level_s = String.valueOf(p_level_int) ;
							return Utils.chat("&c&l&o"+p_level_s);
						} else { 
							return p_level ; 
						}
					}
					return null;
					
				}
			});
		}
	}
}
