package org.bruddasmc.help.commands;

import org.bruddasmc.help.Main;
import org.bruddasmc.help.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class HelpCommand implements CommandExecutor{

	private Main plugin ; 
	
	public HelpCommand(Main plugin) { 
		this.plugin = plugin ; 
		plugin.getCommand("sh").setExecutor(this) ;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { 
		
		Player player = (Player) sender ;
		
		if (player.hasPermission("simpleHelp.help")) {
			if (label.equalsIgnoreCase("sh")) { 
				if(args.length == 0) { 
					for (String messages : plugin.getConfig().getStringList("sh_help")) { 
						player.sendMessage(Utils.chat(messages)) ;
					}
				} else { 
					switch(args[0]) {
					
					case "reload": 
						plugin.reloadConfig(); 
						player.sendMessage(Utils.chat("&a&lConfiguration reloaded!"));
						break ;
					default:
						for (String messages : plugin.getConfig().getStringList("sh_help")) { 
							player.sendMessage(Utils.chat(messages)) ;
						}
						break ; 
					}
				}
			}
		}
		return false ; 
	}	
}
