package org.bruddasmc.helloworld.commands;

import org.bruddasmc.helloworld.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelloCommand implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private Main plugin ; 
	
	public HelloCommand(Main plugin) { 
		this.plugin = plugin ; 
		plugin.getCommand("hello").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// Check if sender is player
		//Permissions go here 
		
		//sender can be either player or console 
		//Player --> Online player 
		if (!(sender instanceof Player)) { 
			sender.sendMessage("Only players can execute this command");
			return true ; 
		}
		// Additional IF's statements if there are multiple commands in the plugin
		//Player variable
		Player player = (Player) sender ; 
		
		if(player.hasPermission("hello.use")) {
			player.sendMessage("Hello my brudda");
			return true ; 
		} else { 
			player.sendMessage("You don't have permission to execute this command");
		}
		
		return false;
	}
}
