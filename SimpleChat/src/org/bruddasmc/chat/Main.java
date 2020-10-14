package org.bruddasmc.chat;

import org.bruddasmc.chat.commands.ChatCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() { 
		saveDefaultConfig() ; 
		new ChatCommand(this) ;
	}
}
