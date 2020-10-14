package org.bruddasmc.chat.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bruddasmc.chat.Main;
import org.bruddasmc.chat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ChatCommand implements CommandExecutor, Listener{

	private Main plugin ;
	
	public ChatCommand (Main plugin) { 
		this.plugin = plugin ;
		plugin.getCommand("mutechat").setExecutor(this) ;
		plugin.getCommand("clearchat").setExecutor(this) ;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	boolean chatMuted = false ;
	List<String> talkedInChat = new ArrayList<>() ; 
	Integer counter, pMessages, countMessages;
	
	Map<String, String> lastMessage = new HashMap<String, String>() ;
	Map<String, Integer> repeatedMessages = new HashMap<String, Integer>() ;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		Player p = (Player) sender ;
		
		if (sender instanceof Player) {
			if (p.hasPermission("mutechat.admin")) {
				if (cmd.getName().equalsIgnoreCase("mutechat")) {
					if(args.length == 0) {
						chatMuted = !chatMuted ; 
						counter = 0 ;
						if (!chatMuted) {
							for (Player players : Bukkit.getOnlinePlayers()) {
								counter = talkedInChat.size() ; 
								players.sendMessage(Utils.chat(plugin.getConfig().getString("unmutechat").replace("<prefix>", Utils.chat(
										plugin.getConfig().getString("prefix"))))) ;
								players.sendMessage(Utils.chat(plugin.getConfig().getString("typeattempts")).replace("<prefix>", Utils.chat(
										plugin.getConfig().getString("prefix"))).replace("<count>", counter.toString()).replace("<msgs>", pMessages.toString()));
							}
							System.out.println(ChatColor.GREEN + "Chat has been unmuted") ;
						} else {
							pMessages = 0 ;
							talkedInChat.clear() ;
							for (Player players : Bukkit.getOnlinePlayers()) {
								if (!(players.hasPermission("mutechat.admin"))) {
									players.sendMessage(Utils.chat(plugin.getConfig().getString("mutechat_players").replace("<prefix>", Utils.chat(
											plugin.getConfig().getString("prefix"))))) ;
								} else { 
									players.sendMessage(Utils.chat(plugin.getConfig().getString("mutechat_staff").replace("<prefix>", Utils.chat(
											plugin.getConfig().getString("prefix"))).replace("<staff>", p.getName()))) ;
								}
							}
							System.out.println(ChatColor.WHITE + p.getName() + ChatColor.RED + " muted the chat");
						}
								
					} else if (args[0].equalsIgnoreCase("reload")) {
						plugin.reloadConfig();
						p.sendMessage(Utils.chat(plugin.getConfig().getString("configreloaded")));
					}
				} else if (cmd.getName().equalsIgnoreCase("clearchat")) {
					for (int i = 0; i < 100; i++) { 
						Bukkit.broadcastMessage("") ; 
					}
					for (Player players : Bukkit.getOnlinePlayers()) {
						if (!(players.hasPermission("mutechat.admin"))) {
							players.sendMessage(Utils.chat(plugin.getConfig().getString("clearchat_players").replace("<prefix>", Utils.chat(
									plugin.getConfig().getString("prefix"))))) ;
						} else { 
							players.sendMessage(Utils.chat(plugin.getConfig().getString("clearchat_staff").replace("<prefix>", Utils.chat(
									plugin.getConfig().getString("prefix"))).replace("<staff>", p.getName()))) ;
						}
					}
				}
			} else { 
				p.sendMessage(Utils.chat(plugin.getConfig().getString("sorrynopermission")));
			}
		} else { 
			System.out.println(ChatColor.RED + "You can only execute this command in game.");
			return false ;
		}
		return false;
	}
	
	@EventHandler 
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		
		String playerName = e.getPlayer().getName() ;
		Player p = (Player) e.getPlayer() ;
		//regardless the message I send counter is 2 already 
		if (this.lastMessage.containsKey(playerName)) {
			//Can only repeat the same message twice in a row 
			if (repeatedMessages.get(playerName) >= 2) {
				if (e.getMessage().equalsIgnoreCase(lastMessage.get(playerName))) {
					e.setCancelled(true) ;
					repeatedMessages.put(playerName, 1) ;
					p.sendMessage(Utils.chat("&3&lBLOCK 2")) ; 
					p.sendMessage("Counter: " + repeatedMessages.get(playerName).toString());
					p.sendMessage(Utils.chat("&c&lCant repeat the same message 3 times hoho"));	
				}
			} else { 
				if (e.getMessage().equalsIgnoreCase(lastMessage.get(playerName))) {
					lastMessage.put(playerName, e.getMessage()) ;
					repeatedMessages.put(playerName, repeatedMessages.get(playerName) + 1) ;
					p.sendMessage(Utils.chat("&3&lBLOCK 3")) ; 
					p.sendMessage("Counter: " + repeatedMessages.get(playerName).toString());
				} else { 
					//Update players message 
					lastMessage.put(playerName, e.getMessage()) ;
					repeatedMessages.put(playerName, 1) ;
					p.sendMessage(Utils.chat("&3&lBLOCK 4")) ; 
					p.sendMessage("Counter: " + repeatedMessages.get(playerName).toString());
				}
			}
			
		} else { 
			lastMessage.put(playerName, e.getMessage()) ;
			repeatedMessages.put(playerName, 1) ;
			p.sendMessage(Utils.chat("&3&lBLOCK 1")) ; 
			p.sendMessage("Counter: " + repeatedMessages.get(playerName).toString());
		}
		
		if (chatMuted) {
			if (!(p.hasPermission("mutechat.admin"))) {
				pMessages++ ; 
				if (!(talkedInChat.contains(p.getName()))) {
					talkedInChat.add(p.getName()) ;	
				}
				p.sendMessage(Utils.chat(plugin.getConfig().getString("sorrychatmuted"))) ;
				e.setCancelled(true) ;	
			}
		}
		
	}
}
