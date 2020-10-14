package me.tolgaozgun.topkillers;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.planetgallium.kitpvp.Game;

import me.tolgaozgun.topkillers.commands.DisableCommand;
import me.tolgaozgun.topkillers.commands.SetLocationCommand;
import me.tolgaozgun.topkillers.commands.SortCommand;
import me.tolgaozgun.topkillers.listeners.JoinListener;
import me.tolgaozgun.topkillers.util.Config;
import me.tolgaozgun.topkillers.util.PlaceholderRegistration;
import me.tolgaozgun.topkillers.util.ScoreboardUpdater;


public class PluginMain extends JavaPlugin{

    public static Game kitpvp = null;
    
    private File playersFile, configFile,locationFile;
    private FileConfiguration playersConfig, config, locationsConfig;
	
    // eliminate Variables
    

	public void onEnable() {
		kitpvp = (Game) Bukkit.getPluginManager().getPlugin("KitPvP");
		createFiles();
		Config.init();
		ScoreboardUpdater.init();
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getServer().getPluginCommand("sort").setExecutor(new SortCommand());
		getServer().getPluginCommand("setscoreboard").setExecutor(new SetLocationCommand());
		getServer().getPluginCommand("disablescoreboard").setExecutor(new DisableCommand());
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PlaceholderRegistration(this).register();
      }
		
	}

	private void createFiles() {
		playersFile = new File(getDataFolder(), "players.yml");
		configFile = new File(getDataFolder(), "config.yml");
		locationFile = new File(getDataFolder(), "locations.yml");
		
		
		if (!playersFile.exists()) {
			playersFile.getParentFile().mkdirs();
			saveResource("players.yml", true);
		}
		
		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			saveResource("config.yml", true);
		}

		if (!locationFile.exists()) {
			locationFile.getParentFile().mkdirs();
			saveResource("locations.yml", true);
		}

		playersConfig = new YamlConfiguration();
		config = new YamlConfiguration();
		locationsConfig = new YamlConfiguration();
		
		
		try {
			playersConfig.load(playersFile);
			config.load(configFile);
			locationsConfig.load(locationFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public File getLocationFile() {
		return locationFile;
	}

	public FileConfiguration getLocationConfig() {
		return locationsConfig;
	}
	
	public File getPlayersFile() {
		return playersFile;
	}

	public FileConfiguration getPlayersConfig() {
		return playersConfig;
	}

	public File getConfigFile() {
		return configFile;
	}

	@Override
	public FileConfiguration getConfig() {
		return config;
	}
}
