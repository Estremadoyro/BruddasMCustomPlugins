package org.bruddasmc.listeners;

import org.bruddasmc.listeners.join.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() { 
		saveDefaultConfig() ; 
		new JoinListener(this) ; 
	}
}
