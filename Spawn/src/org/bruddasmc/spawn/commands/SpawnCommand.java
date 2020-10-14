package org.bruddasmc.spawn.commands;

import java.util.ArrayList;

import org.bruddasmc.spawn.Main;
import org.bruddasmc.spawn.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SpawnCommand implements CommandExecutor, Listener{
	
	public static ArrayList<Player> name = new ArrayList<Player>() ; 
	
	private Main plugin ;
	
	public SpawnCommand (Main plugin) { 
		this.plugin = plugin ; 
		plugin.getCommand("spawn").setExecutor(this);
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		
		Player p = (Player) sender ;
		
		if (sender instanceof Player) {
			if (p.hasPermission("spawn.use")) {
				if (cmd.getName().equalsIgnoreCase("spawn")) {
					if (args.length >= 0) {
						Boolean playerNearby = false ;
						name.add(p) ; 
						for (Entity nearby : p.getNearbyEntities(20, 255, 20)) {
							if(nearby instanceof Player) {
								playerNearby = true ; 
							}
						}
						Location l = Bukkit.getServer().getWorld(plugin.getConfig().getString("defaultSpawn")).getSpawnLocation();
						
						if (playerNearby) {
							int delay = plugin.getConfig().getInt("teleport_delay") ;
							p.sendMessage(Utils.chat(Utils.chat(plugin.getConfig().getString("delayedWarp"))));
							p.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									p.teleport(l) ;
									name.remove(p) ; 
								}
							}, delay*20L) ; 
						} else { 
							p.teleport(l) ;
						}
						return true ; 
					}
				} 		
			} else { 
				p.sendMessage(Utils.chat(plugin.getConfig().getString("noUsePermission")));
			}
		} else { 
			sender.sendMessage(Utils.chat("&cThis command can only be executed in-game."));
		}
		return false;
	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) { 
		Player p = e.getPlayer() ;
		name.remove(p) ; 
		Bukkit.getScheduler().cancelAllTasks();
		p.sendMessage(Utils.chat(plugin.getConfig().getString("warpCancelled")));
		return ;
	}
}
