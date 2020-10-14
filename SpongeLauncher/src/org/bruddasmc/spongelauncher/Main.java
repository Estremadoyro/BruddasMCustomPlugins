package org.bruddasmc.spongelauncher;

import org.bruddasmc.spongelauncher.listeners.StandOnSpongeListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() { 
		
		new StandOnSpongeListener(this) ; 
	}
}
