package net.keylon.me.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.keylon.me.Core;
import net.keylon.me.utils.LobbyScoreboard;

public class SidebarListener implements Listener {
	
	private Core main;
	
	public SidebarListener(Core main) {
		this.main = main;
	}
	
	@EventHandler
	public void boardBuilding(PlayerJoinEvent e) {
		
		Player player = e.getPlayer();
		
		LobbyScoreboard.buildScoreboard(player);
	}
}
