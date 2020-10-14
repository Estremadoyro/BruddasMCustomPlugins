package org.bruddasmc.deathmessages;

import org.bruddasmc.deathmessages.listeners.PlayerDeathListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() { 
		new PlayerDeathListener(this); 
	}
}
