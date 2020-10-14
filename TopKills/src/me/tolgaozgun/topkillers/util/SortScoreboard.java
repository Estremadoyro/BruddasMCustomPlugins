package me.tolgaozgun.topkillers.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.google.common.collect.Iterables;

import me.tolgaozgun.topkillers.PluginMain;

public class SortScoreboard {

	private static PluginMain plugin = PluginMain.getPlugin(PluginMain.class);
	private static HashMap<UUID,Integer> deaths = new HashMap<>();
	private static HashMap<UUID,Integer> exps = new HashMap<>();
	private static HashMap<UUID,Integer> kills = new HashMap<>();
	private static HashMap<UUID,Integer> levels = new HashMap<>();
	private static HashMap<UUID,Double> ratios = new HashMap<>();
	private static ArrayList<Hologram> holograms = new ArrayList<>();
	
	public static void load() {
		try {
			for(Hologram hologram: holograms) {
				hologram.delete();
			}
		}catch(NullPointerException ex) {
			
		}
		deaths.clear();
		exps.clear();
		kills.clear();
		levels.clear();
		ratios.clear();
		for(UUID uuid: Config.getPlayerList().keySet()) {
			int death = PluginMain.kitpvp.getArena().getStats().getDeaths(uuid);
			int exp = PluginMain.kitpvp.getArena().getStats().getExperience(uuid);
			int kill = PluginMain.kitpvp.getArena().getStats().getKills(uuid);
			int level = PluginMain.kitpvp.getArena().getStats().getLevel(uuid);
			double ratio = PluginMain.kitpvp.getArena().getStats().getKDRatio(uuid);
			deaths.put(uuid, death);
			exps.put(uuid, exp);
			kills.put(uuid, kill);
			levels.put(uuid, level);
			ratios.put(uuid, ratio);
		}
		sortAll();
	}
	
	private static void sortAll() {
		deaths = sortByValue(deaths);
		exps = sortByValue(exps);
		kills = sortByValue(kills);
		levels = sortByValue(levels);
		ratios = sortByValueDouble(ratios);

		
		if(Config.deathsLoc != null) {
			Hologram hologram = HologramsAPI.createHologram(plugin, Config.deathsLoc);
			hologram.appendTextLine(Config.deathsTitle);
			int val = 0;
		      for(Map.Entry<UUID, Integer> me : deaths.entrySet()) 
		      { 
		    	  if(val == Config.maxShown) {
		    		  break;
		    	  }
		    	  UUID uuid = me.getKey();
		    	  String pName = Config.getPlayerName(uuid);
		    	  int nth = val+1;
		    	  String line = Config.deathsLine;
		    	  line = line.replace("%player%", pName).replace("%ranking%",nth+"").replaceAll("%count%",me.getValue()+"");
		    	  hologram.appendTextLine(line);
		         val++;
		      }
		      holograms.add(hologram);
		}
		
		if(Config.killsLoc != null) {
			Hologram hologram = HologramsAPI.createHologram(plugin, Config.killsLoc);
			hologram.appendTextLine(Config.killsTitle);
			int val = 0;
		      for(Map.Entry<UUID, Integer> me : kills.entrySet()) 
		      { 
		    	  if(val == Config.maxShown) {
		    		  break;
		    	  }
		    	  UUID uuid = me.getKey();
		    	  String pName = Config.getPlayerName(uuid);
		    	  int nth = val+1;
		    	  String line = Config.killsLine;
		    	  line = line.replace("%player%", pName).replace("%ranking%",nth+"").replaceAll("%count%",me.getValue()+"");
		    	  hologram.appendTextLine(line);
		         val++;
		      }
		      holograms.add(hologram);
		}
		

		if(Config.expsLoc != null) {
			Hologram hologram = HologramsAPI.createHologram(plugin, Config.expsLoc);
			hologram.appendTextLine(Config.expsTitle);
			int val = 0;
		      for(Map.Entry<UUID, Integer> me : exps.entrySet()) 
		      { 
		    	  if(val == Config.maxShown) {
		    		  break;
		    	  }
		    	  UUID uuid = me.getKey();
		    	  String pName = Config.getPlayerName(uuid);
		    	  int nth = val+1;
		    	  String line = Config.expsLine;
		    	  line = line.replace("%player%", pName).replace("%ranking%",nth+"").replaceAll("%count%",me.getValue()+"");
		    	  hologram.appendTextLine(line);
		         val++;
		      }
		      holograms.add(hologram);
		}
		

		if(Config.levelsLoc != null) {
			Hologram hologram = HologramsAPI.createHologram(plugin, Config.levelsLoc);
			hologram.appendTextLine(Config.levelsTitle);
			int val = 0;
		      for(Map.Entry<UUID, Integer> me : levels.entrySet()) 
		      { 
		    	  if(val == Config.maxShown) {
		    		  break;
		    	  }
		    	  UUID uuid = me.getKey();
		    	  String pName = Config.getPlayerName(uuid);
		    	  int nth = val+1;
		    	  String line = Config.levelsLine;
		    	  line = line.replace("%player%", pName).replace("%ranking%",nth+"").replaceAll("%count%",me.getValue()+"");
		    	  hologram.appendTextLine(line);
		         val++;
		      }
		      holograms.add(hologram);
		}
		

		if(Config.ratiosLoc != null) {
			Hologram hologram = HologramsAPI.createHologram(plugin, Config.ratiosLoc);
			hologram.appendTextLine(Config.ratiosTitle);
			int val = 0;
		      for(Map.Entry<UUID, Double> me : ratios.entrySet()) 
		      { 
		    	  if(val == Config.maxShown) {
		    		  break;
		    	  }
		    	  UUID uuid = me.getKey();
		    	  String pName = Config.getPlayerName(uuid);
		    	  int nth = val+1;
		    	  String line = Config.ratiosLine;
		    	  line = line.replace("%player%", pName).replace("%ranking%",nth+"").replaceAll("%count%",me.getValue()+"");
		    	  hologram.appendTextLine(line);
		         val++;
		      }
		      holograms.add(hologram);
		}
	}
	
	public static String getKillRanking(int nth) {
		if(kills.entrySet().size() < nth) {
			return null;
		}
		return Config.getPlayerName(Iterables.get(kills.keySet(), nth));
	}
	

	public static int getKillCount(int nth) {
		if(kills.entrySet().size() < nth) {
			return -1;
		}
		return Iterables.get(kills.values(), nth);
	}
	
	public static String getDeathRanking(int nth) {
		if(deaths.entrySet().size() < nth) {
			return null;
		}
		return Config.getPlayerName(Iterables.get(deaths.keySet(), nth));
	}
	

	public static int getDeathCount(int nth) {
		if(deaths.entrySet().size() < nth) {
			return -1;
		}
		return Iterables.get(deaths.values(), nth);
	}
	
	public static String getRatioRanking(int nth) {
		if(ratios.entrySet().size() < nth) {
			return null;
		}
		return Config.getPlayerName(Iterables.get(ratios.keySet(), nth));
	}
	

	public static double getRatioCount(int nth) {
		if(ratios.entrySet().size() < nth) {
			return -1;
		}
		return Iterables.get(ratios.values(), nth);
	}
	
	public static String getExpRanking(int nth) {
		if(exps.entrySet().size() < nth) {
			return null;
		}
		return Config.getPlayerName(Iterables.get(exps.keySet(), nth));
	}
	

	public static int getExpCount(int nth) {
		if(exps.entrySet().size() < nth) {
			return -1;
		}
		return Iterables.get(exps.values(), nth);
	}
	
	public static String getLevelRanking(int nth) {
		if(levels.entrySet().size() < nth) {
			return null;
		}
		return Config.getPlayerName(Iterables.get(levels.keySet(), nth));
	}
	

	public static int getLevelCount(int nth) {
		if(levels.entrySet().size() < nth) {
			return -1;
		}
		return Iterables.get(levels.values(), nth);
	}
	
	
	private static HashMap<UUID,Integer> sortByValue(HashMap<UUID,Integer> unSortedMap){
		//LinkedHashMap preserve the ordering of elements in which they are inserted
		LinkedHashMap<UUID, Integer> reverseSortedMap = new LinkedHashMap<>();
		 
		//Use Comparator.reverseOrder() for reverse ordering
		unSortedMap.entrySet()
		    .stream()
		    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
		    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
		return reverseSortedMap;
	}
	
	private static HashMap<UUID,Double> sortByValueDouble(HashMap<UUID,Double> unSortedMap){
		//LinkedHashMap preserve the ordering of elements in which they are inserted
		LinkedHashMap<UUID, Double> reverseSortedMap = new LinkedHashMap<>();
		 
		//Use Comparator.reverseOrder() for reverse ordering
		unSortedMap.entrySet()
		    .stream()
		    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
		    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
		return reverseSortedMap;
	}
	/* private static HashMap<UUID, Integer> sortByValue(HashMap<UUID, Integer> hm) 
	   { 
	      // creating list from elements of HashMap
	      List<Map.Entry<UUID, Integer> > list = new LinkedList<Map.Entry<UUID, Integer> >(hm.entrySet());

	      // sorting list 
	      Collections.sort(list, new Comparator<Map.Entry<UUID, Integer> >()
	      { 
	    	 public int compare(Map.Entry<UUID, Integer> o1, Map.Entry<UUID, Integer> o2) 
	         { 
	            return (o1.getValue()).compareTo(o2.getValue()); 
	         } 
	      }); 

	      HashMap<UUID, Integer> ha = new LinkedHashMap<UUID, Integer>(); 
	      for(Map.Entry<UUID, Integer> me : list) 
	      { 
	         ha.put(me.getKey(), me.getValue()); 
	      } 
	      return ha;
	   }
	 

	 private static HashMap<UUID, Double> sortByValueDouble(HashMap<UUID, Double> hm) 
	   { 
	      // creating list from elements of HashMap
	      List<Map.Entry<UUID, Double> > list = new LinkedList<Map.Entry<UUID, Double> >(hm.entrySet());

	      // sorting list 
	      Collections.sort(list, new Comparator<Map.Entry<UUID, Double> >()
	      { 
	    	 public int compare(Map.Entry<UUID, Double> o1, Map.Entry<UUID, Double> o2) 
	         { 
	            return (o1.getValue()).compareTo(o2.getValue()); 
	         } 
	      }); 

	      HashMap<UUID, Double> ha = new LinkedHashMap<UUID, Double>(); 
	      for(Map.Entry<UUID, Double> me : list) 
	      { 
	         ha.put(me.getKey(), me.getValue()); 
	      } 
	      return ha;
	   }*/

}
