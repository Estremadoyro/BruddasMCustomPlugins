package org.bruddasmc.spawn;

import org.bruddasmc.spawn.commands.SpawnCommand;
import org.bruddasmc.spawn.listeners.RespawnListener;
import org.bruddasmc.spawn.listeners.TeleportListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() { 
		saveDefaultConfig() ; 
		new SpawnCommand(this) ;
		new RespawnListener(this) ; 
		new TeleportListener(this) ; 
	}

}
