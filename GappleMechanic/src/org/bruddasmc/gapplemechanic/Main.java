package org.bruddasmc.gapplemechanic;

import org.bruddasmc.gapplemechanic.commands.GappleMechanicCommands;
import org.bruddasmc.gapplemechanic.listeners.BreakBlockListener;
import org.bruddasmc.gapplemechanic.listeners.EatAppleListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() { 
		
		ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE) ;
		
		ItemMeta m_goldenApple = goldenApple.getItemMeta() ;
		//m_goldenApple.setDisplayName(Utils.chat("&6Crapple"));
		m_goldenApple.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		goldenApple.setItemMeta(m_goldenApple);
		
		ShapedRecipe r_goldenApple = new ShapedRecipe(goldenApple) ;
		
		r_goldenApple.shape("AAA", "AAA", "AAA") ; 
		r_goldenApple.setIngredient('A', Material.APPLE) ; 
		Bukkit.addRecipe(r_goldenApple) ; 
		
		saveDefaultConfig() ;
		new BreakBlockListener(this) ; 
		new EatAppleListener(this) ;
		new GappleMechanicCommands(this) ;
	}
}
