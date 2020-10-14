package org.bruddasmc.doublejump.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bruddasmc.doublejump.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class PlayerDoubleJumpListener implements Listener{
	
	@SuppressWarnings("unused")
	private static Main plugin ; 
	
	@SuppressWarnings("static-access")
	public PlayerDoubleJumpListener (Main plugin) { 
		this.plugin = plugin ; 
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	//List of players who are are double jumping
	//So a player can't triple jump 
	private List<String> players = new ArrayList<String>() ;
	
	//Event to set fly true or false for player 
	@EventHandler
	public void setFly(PlayerJoinEvent e) { 
		
		e.getPlayer().setAllowFlight(true);
		e.getPlayer().setFlying(false);
	}
	
	//Event triggers when player toggles fly aka when he double jumps
	@EventHandler
	public void setVelocity(PlayerToggleFlightEvent e) { 
		
		Player player = e.getPlayer() ;
		
		if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR || player.isFlying()) { 
			return ; 
		} else { 
			players.add(player.getName()) ;
			e.setCancelled(true);
			//Player double jumped so he can't fly anymore, as soon as flying event is triggered
			player.setAllowFlight(false);
			player.setFlying(false);
			//Get player position and and multiply his velocity and setY 1 
			player.setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.5).setY(1));
			//Sound produced 
			//Source of sound, type of sound, start volume, decrease volume over time 
			player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 1.0f, -5.0f);
			//Not necessary if the lobby has no fall damage or invincible mode on  
			player.setFallDistance(100);
		}
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		Player player = (Player) e.getEntity() ;
		//Get player current position XYZ of player's head
		Location loc = player.getPlayer().getLocation() ;
		//Get player position of his feet -2
		loc.setY(loc.getY() -2);
		//Get the block he is standing on 
		//Block block = loc.getWorld().getBlockAt(loc) ;
		Block block = loc.getBlock() ;
		//Get player block standing at 
		player.sendMessage(player.getDisplayName() + " is standing on " + block.getType());
		//block.getType() == Material.WATER || block.getType() == Material.LAVA
		if (e.getEntity() instanceof Player && e.getCause() == DamageCause.FALL) { 
			//If player has jumped, if he is in the players array still 
			if (players.contains(player.getName())) { 
				e.setCancelled(true) ;
				//Remove player from the list, he is ready to double jump again 
				players.remove(player.getName()) ;
				player.setAllowFlight(true);
				
			}
		}
		
	}
	
}
