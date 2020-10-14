package org.bruddasmc.helloworld;

import org.bruddasmc.helloworld.commands.HelloCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	//When plugin runs 
	@Override
	public void onEnable() { 
		 new HelloCommand(this) ; 
	}
	
}
