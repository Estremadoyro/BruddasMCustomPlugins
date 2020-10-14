package org.bruddasmc.switchservers;

import org.bruddasmc.switchservers.commands.SwitchServersCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override 
	public void onEnable() { 
		saveDefaultConfig() ; 
		new SwitchServersCommand(this) ;
	}
}
