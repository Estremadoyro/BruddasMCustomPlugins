package org.bruddasmc.survivalplaceholders;

import org.bruddasmc.survivalplaceholders.listeners.PHBreakBlockListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
		//new MVdWPlaceholders(this) ; 
		new PHBreakBlockListener(this) ; 
	}
}
