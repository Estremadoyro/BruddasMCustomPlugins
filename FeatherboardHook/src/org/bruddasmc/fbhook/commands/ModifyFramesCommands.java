package org.bruddasmc.fbhook.commands;

import org.bruddasmc.fbhook.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.featherboard.api.scoreboard.* ; 

public class ModifyFramesCommands implements CommandExecutor{

	private Main plugin ; 
	
	public ModifyFramesCommands(Main plugin) { 
		this.plugin = plugin ; 
		plugin.getCommand("fbremove").setExecutor(this) ; 
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		
		Player player = (Player) sender ; 
		
		if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
			
		}
			
		
		return false;
	}

}
