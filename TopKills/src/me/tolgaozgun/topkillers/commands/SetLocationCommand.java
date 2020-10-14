package me.tolgaozgun.topkillers.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tolgaozgun.topkillers.util.Config;
import net.md_5.bungee.api.ChatColor;

public class SetLocationCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!p.isOp() && !p.hasPermission("topkillers.admin.setlocation")) {
				p.sendMessage("No permission!");
				return true;
			}
			if(args.length > 0) {
				switch(args[0].toLowerCase()) {
				case "kills":
				case "kill":
				case "killloc":
					Config.setKillsLocation(p.getLocation());
					sender.sendMessage(ChatColor.DARK_RED + "Kill " + ChatColor.AQUA  + "scoreboard location is set!");
					break;
				case "deaths":
				case "death":
				case "deathloc":
					Config.setDeathsLocation(p.getLocation());
					sender.sendMessage(ChatColor.DARK_RED + "Death " + ChatColor.AQUA  + "scoreboard location is set!");
					break;
				case "exps":
				case "exp":
				case "exploc":
					Config.setExpsLocation(p.getLocation());
					sender.sendMessage(ChatColor.DARK_RED + "Experience " + ChatColor.AQUA  + "scoreboard location is set!");
					break;
				case "levels":
				case "level":
				case "lvl":
				case "levelloc":
					Config.setLevelsLocation(p.getLocation());
					sender.sendMessage(ChatColor.DARK_RED + "Level " + ChatColor.AQUA  + "scoreboard location is set!");
					break;
				case "ratios":
				case "ratio":
				case "ratioloc":
					Config.setRatiosLocation(p.getLocation());
					sender.sendMessage(ChatColor.DARK_RED + "Ratio " + ChatColor.AQUA  + "scoreboard location is set!");
					break;
				default:
					sender.sendMessage(ChatColor.DARK_RED + "Scoreboard not found!");
					break;
					
				}
			}
			
		}else {
			sender.sendMessage("Console cannot be used for this command.");
		}
		return true;
	}

}