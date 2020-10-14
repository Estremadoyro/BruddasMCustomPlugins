package me.tolgaozgun.topkillers.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import me.tolgaozgun.topkillers.PluginMain;

public class Config {

	private static PluginMain plugin = PluginMain.getPlugin(PluginMain.class);
	private static HashMap<UUID,String> uuids = new HashMap<>();
	private static File playersFile, locsFile;
	private static FileConfiguration playersConfig, config, locsConfig;
	public static Location killsLoc, deathsLoc, expsLoc, levelsLoc, ratiosLoc;
	public static int updateSeconds, maxShown;
	public static String killsTitle, killsLine, deathsTitle, deathsLine, expsTitle, expsLine, levelsTitle, levelsLine, ratiosTitle, ratiosLine;
	
	public static void init() {
		uuids.clear();
		playersFile = plugin.getPlayersFile();
		playersConfig = plugin.getPlayersConfig();
		//configFile = plugin.getConfigFile();
		config = plugin.getConfig();
		locsFile = plugin.getLocationFile();
		locsConfig = plugin.getLocationConfig();
		try {
		    for (String key : playersConfig.getConfigurationSection("players").getKeys(false)) {
		    	UUID uuid = UUID.fromString(key);
		        uuids.put(uuid, playersConfig.getString("players."+key));
		    }	
		}catch(NullPointerException ex) {
			// No problem, players are empty! Just continue...
		}
		try {
			killsLoc = (Location) locsConfig.get("kills-loc",null);	
		}catch(NullPointerException ex) {
			
		}
		try {
			deathsLoc = (Location) locsConfig.get("deaths-loc",null);
		}catch(NullPointerException ex) {
			
		}
		try {
			expsLoc = (Location) locsConfig.get("exps-loc",null);
		}catch(NullPointerException ex) {
			
		}
		try {
			levelsLoc = (Location) locsConfig.get("levels-loc",null);
		}catch(NullPointerException ex) {
			
		}
		try {
			ratiosLoc = (Location) locsConfig.get("ratios-loc",null);
		}catch(NullPointerException ex) {
			
		}

		killsTitle = getColored("kills-title","&4Kill Rankings:");
		killsLine = getColored("kills-line","%placement%: &6%player%&r: %count%");

		deathsTitle = getColored("deaths-title","&4Death Rankings:");
		deathsLine = getColored("deaths-line","%placement%: &6%player%&r: %count%");

		expsTitle = getColored("exps-title","&4Experience Rankings:");
		expsLine = getColored("exps-line","%placement%: &6%player%&r: %count%");

		levelsTitle = getColored("levels-title","&4Level Rankings:");
		levelsLine = getColored("levels-line","%placement%: &6%player%&r: %count%");

		ratiosTitle = getColored("ratios-title","&4K/D Ratio Rankings:");
		ratiosLine = getColored("ratios-line","%placement%: &6%player%&r: %count%");
		

		updateSeconds = config.getInt("scoreboard-update",600);

		maxShown = config.getInt("max-players-on-scoreboard",10);
		plugin.getServer().getConsoleSender().sendMessage("Update: " + updateSeconds + " maxShown: " + maxShown);
	}	
	
	public static void add(UUID uuid,String name) {
		uuids.put(uuid,name);
		for (UUID id: uuids.keySet()) {
			playersConfig.set("players."+id, uuids.get(id));
		}
		savePlayersConfig();
	}
	
	
	private static String getColored(String string, String string2) {
		return ChatColor.translateAlternateColorCodes('&', config.getString(string,string2));
		
	}
	
	private static void savePlayersConfig() {
		try {
			playersConfig.save(playersFile);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private static void saveLocationConfig() {
		try {
			locsConfig.save(locsFile);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static HashMap<UUID,String> getPlayerList(){
		return uuids;
	}
	
	public static String getPlayerName(UUID uuid) {
		return uuids.get(uuid);
	}
	
	public static void setKillsLocation(Location loc) {
		killsLoc = loc;
		locsConfig.set("kills-loc", killsLoc);
		saveLocationConfig();
	}

	public static void setDeathsLocation(Location loc) {
		deathsLoc = loc;
		locsConfig.set("deaths-loc", deathsLoc);
		saveLocationConfig();
	}
	
	public static void setExpsLocation(Location loc) {
		expsLoc = loc;
		locsConfig.set("exps-loc", expsLoc);
		saveLocationConfig();
		
	}
	
	public static void setLevelsLocation(Location loc) {
		levelsLoc = loc;
		locsConfig.set("levels-loc", levelsLoc);
		saveLocationConfig();
		
	}
	
	public static void setRatiosLocation(Location loc) {
		ratiosLoc = loc;
		locsConfig.set("ratios-loc", ratiosLoc);
		saveLocationConfig();
		
	}
	

}
