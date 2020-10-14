package org.bruddasmc.switchservers.commands;

import org.bruddasmc.switchservers.Main;
import org.bruddasmc.switchservers.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class SwitchServersCommand implements CommandExecutor{

	@SuppressWarnings("unused")
	private Main plugin ;
	
	public SwitchServersCommand (Main plugin) { 
		this.plugin = plugin ; 
		plugin.getCommand("Lobby").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if (!(sender instanceof ProxiedPlayer)) { 
			sender.sendMessage("&cThis command can only be executed in game");
			return true; 
		}
		
		ProxiedPlayer prox_player = (ProxiedPlayer) sender ;
		Player player = (Player) sender ;
		
		if (prox_player.hasPermission("bungeecord.command.ss")) {
		
			if (prox_player.getServer().getInfo().getName().equalsIgnoreCase("Lobby")) {
				player.sendMessage(Utils.chat("&3Already connected to the Lobby"));
			} else { 
				ServerInfo target = ProxyServer.getInstance().getServerInfo("Lobby") ; 
				prox_player.connect(target);
			}
		} else { 
			player.sendMessage(Utils.chat("&cYou don't have permission to execute this command :(")) ; 
		}
		return true ; 
	}
}
