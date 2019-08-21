package net.keylon.me.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import net.keylon.me.Core;
import net.keylon.me.utils.Common;
import net.keylon.me.utils.LobbyScoreboard;

public class Queue implements Listener {

	public static List<String> lobby = new ArrayList<String>();
	public static List<String> killers = new ArrayList<String>();
	public static List<String> attackers = new ArrayList<String>();
	public static List<String> guardians = new ArrayList<String>();
	public static boolean gameBegun = false;
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String user = p.getName();
		
		p.getInventory().addItem(new ItemStack(Material.FEATHER));
		p.getInventory().addItem(new ItemStack(Material.BEACON));

		lobby.add(user);
		if(Bukkit.getServer().getOnlinePlayers().size() < 50) {
			Bukkit.broadcastMessage(Common.colorize("&e" + e.getPlayer().getName() + " &7has joined! &8&l(&6&l" + Bukkit.getServer().getOnlinePlayers().size() + "&7/&6&l50&8&l)"));
return;
		} else if(Bukkit.getServer().getOnlinePlayers().size() == 50) {
			GameStart.CheckStart();
			Bukkit.broadcastMessage(Common.colorize("&e" + e.getPlayer().getName() + " &7has joined!"));
			return;
		}else {
		}
			Bukkit.broadcastMessage(Common.colorize("&e" + e.getPlayer().getName() + " &7has joined!"));
			return;
		}
	

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		for (String playern : Queue.getLobby()) {
			Player player = Bukkit.getPlayer(playern);
			if (!player.isOnline()) { continue; }
			LobbyScoreboard.buildScoreboard(player);
		}
		if (lobby.contains(e.getPlayer().getName())) {
			lobby.remove(e.getPlayer().getName());
		} else if (attackers.contains(e.getPlayer().getName())) {
			attackers.remove(e.getPlayer().getName());
		} else if (killers.contains(e.getPlayer().getName())) {
			killers.remove(e.getPlayer().getName());
		} else if (guardians.contains(e.getPlayer().getName())) {
			guardians.remove(e.getPlayer().getName());
		}

		if(Bukkit.getServer().getOnlinePlayers().size() < 50) {
			int nowOnline = Bukkit.getServer().getOnlinePlayers().size() -1; 
			Bukkit.broadcastMessage(Common.colorize("&e" + e.getPlayer().getName() + " &7has left! &8&l(&6&l" + nowOnline + "&7/&6&l50&8&l)"));
			return;
		} else {
			Bukkit.broadcastMessage(Common.colorize("&e" + e.getPlayer().getName() + " &7has left!"));
			return;
		}
	}

	public void onGameStart() {

	}

	public void onGameEnd() {
		attackers.clear();
		killers.clear();
		return;
	}

	public static List<String> getAttackers() {
		return attackers;
	}
	
	public static List<String> getLobby() {
		return lobby;
	}

	public static List<String> getKillers() {
		return killers;
	}
	
	public static List<String> getGuardians() {
		return guardians;
	}

}
