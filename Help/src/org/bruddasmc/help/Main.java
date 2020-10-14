package org.bruddasmc.help;

import org.bruddasmc.help.commands.HelpCommand;
import org.bruddasmc.help.messages.HelpMessage;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() { 
		saveDefaultConfig() ; 
		new HelpCommand(this) ; 
		new HelpMessage(this) ;
	}
}
