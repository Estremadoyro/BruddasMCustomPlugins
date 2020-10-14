package me.tolgaozgun.topkillers.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tolgaozgun.topkillers.util.SortScoreboard;

public class SortCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!p.isOp() && !p.hasPermission("topkillers.admin.sort")) {
				p.sendMessage("No permission!");
				return true;
			}
			p.sendMessage("Sorting...");
			SortScoreboard.load();
			
		}else {
			sender.sendMessage("Sorting...");
			SortScoreboard.load();
		}
		return true;
	}

}
