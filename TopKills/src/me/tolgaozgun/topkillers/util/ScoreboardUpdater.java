package me.tolgaozgun.topkillers.util;

import me.tolgaozgun.topkillers.PluginMain;

public class ScoreboardUpdater {

	private static PluginMain plugin = PluginMain.getPlugin(PluginMain.class);
	
	public static void init() {
		plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {

			@Override
			public void run() {
				SortScoreboard.load();			
		}
			
			
		}, 0L, Config.updateSeconds*20L);
	}

}
