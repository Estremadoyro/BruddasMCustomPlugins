package org.bruddasmc.kitpvpplaceholders;

import org.bruddasmc.kitpvpplaceholders.ph.Placeholders;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
		new Placeholders(this) ;	
	}
	
}
