package org.bruddasmc.gapplemechanic.listeners;

import java.util.ArrayList;
import java.util.Random;

import org.bruddasmc.gapplemechanic.Main;
import org.bruddasmc.gapplemechanic.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BreakBlockListener implements Listener{

	public int getRandom(int lower, int upper) {
	    return new Random().nextInt((upper - lower) + 1) + lower;
	}
	
	private Main plugin ;
	
	public BreakBlockListener (Main plugin) { 
		this.plugin = plugin ; 
		
		Bukkit.getPluginManager().registerEvents(this, plugin) ;
	}
	
	@EventHandler
	public void onPlayerBreakBlock(BlockBreakEvent e) { 
		Block block = e.getBlock() ;
		
		ItemStack apple = new ItemStack(Material.APPLE) ;
		ItemMeta m_apple = apple.getItemMeta() ;
		ArrayList<String> lore = new ArrayList<String>() ;
		
		for (String lore_line : plugin.getConfig().getStringList("apple_lore")) {
			lore.add(Utils.chat(lore_line)) ; 
		}
		//lore.add(Utils.chat("&7» &aCombine x9 to get a")) ; 
		//lore.add(Utils.chat("&7» &bGolden Apple!")) ;
		
		m_apple.setLore(lore);
		apple.setItemMeta(m_apple) ; 
		
		if (block.getType() == Material.LONG_GRASS) {
			Integer randomInt = getRandom(1, 100) ;
			if (randomInt <= plugin.getConfig().getInt("appleDropRate")) {
				e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), apple) ;
				//p.getInventory().addItem(apple) ; 
			}
		}
		
	}
}
