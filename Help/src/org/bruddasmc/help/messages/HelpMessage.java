package org.bruddasmc.help.messages;

import org.bruddasmc.help.Main;
import org.bruddasmc.help.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpMessage implements CommandExecutor{

	private Main plugin ; 
	
	public HelpMessage(Main plugin) {
		this.plugin = plugin ; 
		plugin.getCommand("help").setExecutor(this);
		plugin.getCommand("socials").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) { 
			sender.sendMessage("Can only be exucuted in game");
			return false ; 
		}
		
		Player player = (Player) sender ; 
		
		if (player.hasPermission("simpleHelp.use")) {
			
			if (cmd.getName().equalsIgnoreCase("help")) {
				for (String messages : plugin.getConfig().getStringList("help_message")) { 
					player.sendMessage(Utils.chat(messages)) ;
				}	
			}
			else if (cmd.getName().equalsIgnoreCase("socials")) {
				for (String messages : plugin.getConfig().getStringList("socials")) { 
					player.sendMessage(Utils.chat(messages)) ;
				}
			}
		}
		
		return true;
	}
	
}
