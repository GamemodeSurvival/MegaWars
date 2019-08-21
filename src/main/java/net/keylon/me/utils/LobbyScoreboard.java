package net.keylon.me.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class LobbyScoreboard {
	
	@SuppressWarnings("deprecation")
	public static void buildScoreboard(Player player) {
		
		int online = Bukkit.getServer().getOnlinePlayers().size();	
		String status1 = "";
		if(online < 50) {
			status1 = Common.colorize("&eGathering more players...");
		} else if(online >= 50) {
			status1 = Common.colorize("&ePreparing game...");
		}
		
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Objective obj = board.registerNewObjective("dummy", "test");
		
		obj.setDisplayName(Common.colorize("&6&lMegaWars"));
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		Score placeHolder = obj.getScore(Common.colorize("&8&m───────────────"));
		placeHolder.setScore(6);
		
		Score players = obj.getScore(Common.colorize("&7&lIn lobby"));
		players.setScore(5);
		
		Score lobbyCount = obj.getScore(Common.colorize("&e" + online + "&8/&e50"));
		lobbyCount.setScore(4);
		
		Score space = obj.getScore(" ");
		space.setScore(3);
		
		Score status = obj.getScore(Common.colorize("&7&lStatus"));
		status.setScore(2);
		
		Score statusSet = obj.getScore(status1);
		statusSet.setScore(1);
		
	
		player.setScoreboard(board);
	}
	
	

}
