package org.bruddasmc.survivalplaceholders.listeners;

import org.bruddasmc.survivalplaceholders.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;

public class MVdWPlaceholders implements Listener{

	private Main plugin ;
	
	public MVdWPlaceholders (Main plugin) {
		this.plugin = plugin  ;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer() ; 
		if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
			
			PlaceholderAPI.registerPlaceholder(plugin, "mvdw_job", new PlaceholderReplacer() {
				@Override
				public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
					String p_job = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jobs%") ;
					if (p_job.equalsIgnoreCase("none")) {
						return "Unemployed" ;
					}
					return p_job ; 
				}
			});
			PlaceholderAPI.registerPlaceholder(plugin, "mvdw_job_exp", new PlaceholderReplacer() {
				@Override
				public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
					String p_job = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jobs%") ;
					Player p = e.getPlayer() ; 
					String p_job_exp = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(p,"%jobsr_user_jexp_"+p_job+"%") ;
					return p_job_exp ; 
				}
			});
			PlaceholderAPI.registerPlaceholder(plugin, "mvdw_job_maxexp", new PlaceholderReplacer() {
				@Override
				public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
					String p_job = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jobs%") ;
					Player p = e.getPlayer() ; 
					String p_job_maxexp = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(p,"%jobsr_user_jmaxexp_"+p_job+"%") ;
					return p_job_maxexp ; 
				}
			});
		}
		
	}
}
