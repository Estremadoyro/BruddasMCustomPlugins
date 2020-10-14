package org.bruddasmc.spongelauncher.listeners;

import java.util.ArrayList;
import java.util.List;
import org.bruddasmc.spongelauncher.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
//import org.bukkit.event.player.PlayerToggleFlightEvent;

public class StandOnSpongeListener implements Listener{

	@SuppressWarnings("unused")
	private static Main plugin ; 
	
	@SuppressWarnings("static-access")
	public StandOnSpongeListener (Main plugin) { 
		this.plugin = plugin ; 
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	private List<String> players = new ArrayList<String>() ;
	
	@EventHandler
	public void setFly(PlayerJoinEvent e) { 
		
		e.getPlayer().setAllowFlight(true);
		e.getPlayer().setFlying(false);
	}
	
	@EventHandler
	public void setVelocity(PlayerMoveEvent e) { 
		Player player = (Player) e.getPlayer() ;
		Location loc = e.getPlayer().getLocation() ; 
		//loc.setY(loc.getY() -2);
		Block block = loc.getBlock().getRelative(BlockFace.DOWN);
		
		if(block.getType() == Material.SPONGE && !(players.contains(player.getName()))) {
			e.setCancelled(true) ;
			//e.setCancelled(true);
			//player.setAllowFlight(false);
			//player.setFlying(false);
			//player.setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.5).setY(1));
			//player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 1.0f, -5.0f);  
			//player.setFallDistance(100);
			player.sendMessage(player.getName() + "is standing on " + block.getType());
			players.add(player.getName()) ;
		} else {
			if (players.contains(player.getName())){
				e.setCancelled(true) ;
				players.remove(player.getName()) ;  
			}
		}
		
	}
}
