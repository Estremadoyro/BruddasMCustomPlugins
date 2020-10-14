package org.bruddasmc.doublejump;

import org.bruddasmc.doublejump.listeners.PlayerDoubleJumpListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() { 
		new PlayerDoubleJumpListener(this) ; 
	}
}
