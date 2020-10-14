package org.bruddasmc.gapplemechanic.commands;

import org.bruddasmc.gapplemechanic.Main;
import org.bruddasmc.gapplemechanic.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GappleMechanicCommands implements CommandExecutor{

	private Main plugin ;
	
	public GappleMechanicCommands (Main plugin) { 
		this.plugin = plugin ; 
		plugin.getCommand("gpm").setExecutor(this); ; 
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {

		Player p = (Player) sender ; 
					
		if (!(sender instanceof Player)) { 
			sender.sendMessage("Can only be exucuted in game");
			return false ; 
		}
		
		if (alias.equalsIgnoreCase("gpm")) { 
			if (args.length == 0) { 
				p.sendMessage(Utils.chat("&cMissing arguments"));
			} else { 
				//gpm reload
				if (args[0].equalsIgnoreCase("reload")) {
					plugin.reloadConfig();
					p.sendMessage(Utils.chat("&aConfig reloaded"));
					
				}
			}
		}
				
		return false;
	}
	
}
