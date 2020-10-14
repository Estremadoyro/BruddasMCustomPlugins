package me.tolgaozgun.topkillers.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tolgaozgun.topkillers.util.Config;
import net.md_5.bungee.api.ChatColor;

public class DisableCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!p.isOp() && !p.hasPermission("topkillers.admin.disable")) {
				p.sendMessage("No permission!");
				return true;
			}
		}
			if(args.length > 0) {
				switch(args[0].toLowerCase()) {
				case "kills":
				case "kill":
				case "killloc":
					Config.setKillsLocation(null);
					sender.sendMessage(ChatColor.DARK_RED + "Kill " + ChatColor.AQUA  + "scoreboard disabled!");
					break;
				case "deaths":
				case "death":
				case "deathloc":
					Config.setDeathsLocation(null);
					sender.sendMessage(ChatColor.DARK_RED + "Death " + ChatColor.AQUA  + "scoreboard disabled!");
					break;
				case "exps":
				case "exp":
				case "exploc":
					Config.setExpsLocation(null);
					sender.sendMessage(ChatColor.DARK_RED + "Experience " + ChatColor.AQUA  + "scoreboard disabled!");
					break;
				case "levels":
				case "level":
				case "lvl":
				case "levelloc":
					Config.setLevelsLocation(null);
					sender.sendMessage(ChatColor.DARK_RED + "Level " + ChatColor.AQUA  + "scoreboard disabled!");
					break;
				case "ratios":
				case "ratio":
				case "ratioloc":
					Config.setRatiosLocation(null);
					sender.sendMessage(ChatColor.DARK_RED + "Ratio " + ChatColor.AQUA  + "scoreboard disabled!");
					break;
				default:
					sender.sendMessage(ChatColor.DARK_RED + "Scoreboard not found!");
					break;
				}
			}
			

		return true;
	}

}