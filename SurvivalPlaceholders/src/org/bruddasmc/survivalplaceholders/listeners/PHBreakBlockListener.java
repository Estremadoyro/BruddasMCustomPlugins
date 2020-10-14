package org.bruddasmc.survivalplaceholders.listeners;

import org.bruddasmc.survivalplaceholders.Main;
import org.bruddasmc.survivalplaceholders.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;

public class PHBreakBlockListener implements Listener{

	@SuppressWarnings("unused")
	private Main plugin ;
	
	public PHBreakBlockListener (Main plugin) {
		this.plugin = plugin  ;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
	}
	
	private static double round(double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}
	
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e) {
		@SuppressWarnings("unused")
		Player p = e.getPlayer() ;
		
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			PlaceholderAPI.registerPlaceholderHook("teams", new PlaceholderHook() {
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
					String p_teamname = PlaceholderAPI.setPlaceholders(p, "%team_colorname%");
					if (params.equalsIgnoreCase("name_scoreboard")) {
						if (p_teamname.equalsIgnoreCase("Not in a team")) {
							return "Not in a team" ;
						} else {
							return p_teamname ; 
						}
					}
					return null;
				}
			}) ; 

			PlaceholderAPI.registerPlaceholderHook("bclaim", new PlaceholderHook() {
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
					
					if (params.equalsIgnoreCase("owner_name")) {
						String claim_owner = PlaceholderAPI.setPlaceholders(p, "%griefprevention_currentclaim_ownername%") ;
						if (claim_owner.equalsIgnoreCase("an administrator")) {
							return Utils.chat("&cSpawn") ;
						}
						
						if (claim_owner.equalsIgnoreCase("Unclaimed")) {
							return Utils.chat("Wilderness") ; 
						} else {
							return Utils.chat("&b"+claim_owner) ;	
						}
					}
					
					return null ; 
				}
			});
			
			
			PlaceholderAPI.registerPlaceholderHook("bjobs", new PlaceholderHook() {
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
					String p_job = PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jobs%");
					if (params.equalsIgnoreCase("survival_job_name")) {
						//String p_job = PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jobs%");
						if (p_job.equalsIgnoreCase("none")) {
							return "Unemployed" ;
						} else {
							return p_job ;	
						}
						//JobsPlayer p_job = Jobs.getPlayerManager().getJobsPlayer(p) ;
						//JobsPlayer p_job = Jobs.getJobsDAO().job ;
					} else if (params.equalsIgnoreCase("survival_job_name_tab")) {
						String p_job2 = PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jobs%");
						if (p_job2.equalsIgnoreCase("none")) {
							return "" ;
						} else {
							return Utils.chat("&7[&3"+p_job2+"&7]") ;	
						}					
						
					} else if (params.equalsIgnoreCase("survival_job_level")) {
						String p_job_level = PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jlevel_"+p_job+"%");
						int p_job_level_int = Integer.parseInt(p_job_level);
						if (p_job_level_int >= 1 && p_job_level_int <= 19) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&a✪"+p_job_level_s+"&7]") ;	 
						}
						else if (p_job_level_int >= 20 && p_job_level_int <= 39) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&e✪"+p_job_level_s+"&7]") ;	 
						}
						else if (p_job_level_int >= 40 && p_job_level_int <= 59) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&b✪"+p_job_level_s+"&7]") ;	 
						}
						else if (p_job_level_int >= 60 && p_job_level_int <= 79) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&9✪"+p_job_level_s+"&7]") ;	 
						}
						else if (p_job_level_int >= 80 && p_job_level_int <= 99) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&5✪"+p_job_level_s+"&7]") ;	 
						}
						//100+ 
						else if (p_job_level_int >= 100 && p_job_level_int <= 124) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&6&l✪"+p_job_level_s+"✪"+"&7]") ;	 
						}
						else if (p_job_level_int >= 125 && p_job_level_int <= 149) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&d&l✪"+p_job_level_s+"✪"+"&7]") ;	 
						}
						else if (p_job_level_int >= 150 && p_job_level_int <= 169) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&c&l✪"+p_job_level_s+"✪"+"&7]") ;	 
						}
						else if (p_job_level_int >= 150 && p_job_level_int <= 169) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&4&l✪"+p_job_level_s+"✪"+"&7]") ;	 
						} else { 
							return Utils.chat("&7[&f✪0&7]") ;
						}
					} else if (params.equalsIgnoreCase("survival_job_level_sign")) {
						String p_job_level_sign = PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jlevel_"+p_job+"%");
						return p_job_level_sign ;
						
					} else if (params.equalsIgnoreCase("survival_job_level_belowname")) {
						String p_job_level = PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jlevel_"+p_job+"%");
						int p_job_level_int = Integer.parseInt(p_job_level);
						if (p_job_level_int >= 1 && p_job_level_int <= 19) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&a✪"+p_job_level_s+"&7]") ;	 
						}
						else if (p_job_level_int >= 20 && p_job_level_int <= 39) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&e✪"+p_job_level_s+"&7]") ;	 
						}
						else if (p_job_level_int >= 40 && p_job_level_int <= 59) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&b✪"+p_job_level_s+"&7]") ;	 
						}
						else if (p_job_level_int >= 60 && p_job_level_int <= 79) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&9✪"+p_job_level_s+"&7]") ;	 
						}
						else if (p_job_level_int >= 80 && p_job_level_int <= 99) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&5✪"+p_job_level_s+"&7]") ;	 
						}
						//100+ 
						else if (p_job_level_int >= 100 && p_job_level_int <= 124) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&6&l✪"+p_job_level_s+"✪"+"&7]") ;	 
						}
						else if (p_job_level_int >= 125 && p_job_level_int <= 149) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&d&l✪"+p_job_level_s+"✪"+"&7]") ;	 
						}
						else if (p_job_level_int >= 150 && p_job_level_int <= 169) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&c&l✪"+p_job_level_s+"✪"+"&7]") ;	 
						}
						else if (p_job_level_int >= 150 && p_job_level_int <= 169) {
							String p_job_level_s = String.valueOf(p_job_level_int) ; 
							return Utils.chat("&7[&4&l✪"+p_job_level_s+"✪"+"&7]") ;	 
						} else { 
							return "" ; 
						}
					} else if (params.equalsIgnoreCase("survival_job_level_arrow")) {
						String p_job_level = PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jlevel_"+p_job+"%");
						if (p_job_level.equalsIgnoreCase("0")) {
							return "" ;
						} else { 
							return "»" ; 
						}
						
					} else if (params.equalsIgnoreCase("survival_job_exp")) {
						//String p_job = PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jobs%");
						String p_job_exp = PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jexp_"+p_job+"%");
						return p_job_exp ;
					} else if (params.equalsIgnoreCase("survival_job_maxexp")) {
						String p_job_maxexp = PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jmaxexp_"+p_job+"%");
						return p_job_maxexp ;
					} else if (params.equalsIgnoreCase("survival_job_progress")) {
						String p_job_exp = PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jexp_"+p_job+"%");
						String p_job_maxexp = PlaceholderAPI.setPlaceholders(p, "%jobsr_user_jmaxexp_"+p_job+"%");
						//System.out.print(p_job_maxexp);
						double p_job_exp_n = Double.parseDouble(p_job_exp.replace(",",""));
						double p_job_maxexp_n = Double.parseDouble(p_job_maxexp.replace(",","")) ;
						//System.out.print(p_job_maxexp_n);
						double result = ((p_job_exp_n / p_job_maxexp_n) * 100.0);
						double result_rounded = round(result, 2) ; 
						//double result_rounded = Math.round(result) ; 
						//int result_final = (int) result_rounded ; 
						String progress = String.valueOf(result_rounded) ; 
						return progress ; 
					}
					
					
					return null ; 
				}
				
			}) ;
			
			PlaceholderAPI.registerPlaceholderHook("bruddasmc", new PlaceholderHook() {
				
				@Override
				public String onRequest(OfflinePlayer p, String params) {
					if (p != null && p.isOnline()) {
						return onPlaceholderRequest(p.getPlayer(), params) ; 
					}
					return null;
				}
				
				//bruddasmc_survival_ores
				@Override 
				public String onPlaceholderRequest(Player p, String params) {
					if (p == null) {
						
						return null ;	
					}
					if (params.equalsIgnoreCase("survival_ores")) {
						String coal_ore = PlaceholderAPI.setPlaceholders(p, "%statistic_mine_block_COAL_ORE%");
						int coalore = Integer.parseInt(coal_ore) ;
						String iron_ore = PlaceholderAPI.setPlaceholders(p, "%statistic_mine_block_IRON_ORE%");
						int ironore = Integer.parseInt(iron_ore) ;
						String gold_ore = PlaceholderAPI.setPlaceholders(p, "%statistic_mine_block_GOLD_ORE%");
						int goldore = Integer.parseInt(gold_ore) ;
						String redstone_ore = PlaceholderAPI.setPlaceholders(p, "%statistic_mine_block_REDSTONE_ORE%");
						int redstoneore = Integer.parseInt(redstone_ore) ;
						String lapis_ore = PlaceholderAPI.setPlaceholders(p, "%statistic_mine_block_LAPIS_ORE%");
						int lapisore = Integer.parseInt(lapis_ore) ;
						String diamond_ore = PlaceholderAPI.setPlaceholders(p, "%statistic_mine_block_DIAMOND_ORE%");
						int diamondore = Integer.parseInt(diamond_ore) ;
						String emerald_ore = PlaceholderAPI.setPlaceholders(p, "%statistic_mine_block_EMERALD_ORE%");
						int emeraldore = Integer.parseInt(emerald_ore) ;
						String quartz_ore = PlaceholderAPI.setPlaceholders(p, "%statistic_mine_block_QUARTZ_ORE%");
						int quartzore = Integer.parseInt(quartz_ore) ;
						
						int oresMined = quartzore + emeraldore + diamondore + lapisore + redstoneore + goldore + ironore + coalore ;
						
						String s_oredMined = String.valueOf(oresMined) ;
					
						return s_oredMined ; 
					}
					return null ; 
				}
			}) ;
		}
	}
}

