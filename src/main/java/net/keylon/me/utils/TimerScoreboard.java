package net.keylon.me.utils;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.keylon.me.Core;
import net.md_5.bungee.api.ChatColor;

public class TimerScoreboard {
	
	Timer timer;
	Scoreboard board;
	public TimerScoreboard(Timer timer) {
		this.timer = timer;
	}
	
	@SuppressWarnings("deprecation")
	public void makeBoard() {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Objective obj = board.registerNewObjective("dummy", "testTimer");
		
		obj.setDisplayName(Common.colorize("&6&lMegaWars"));
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		final Team timerT = board.registerNewTeam("timeLeft");
		timerT.addPlayer(Bukkit.getOfflinePlayer(ChatColor.RED.toString() + ChatColor.DARK_PURPLE.toString()));
		obj.getScore(ChatColor.RED.toString() + ChatColor.DARK_PURPLE.toString()).setScore(1);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Integer mins = timer.getTimeRemaining()/60;
				Integer seconds = timer.getTimeRemaining()%60;
				timerT.setPrefix(Common.colorize("&8&lTime left: &e" + mins + "M" + seconds + "s"));
				if (timer.getTimeRemaining() <= 0) {
					this.cancel();
				}
			}
		}.runTaskTimer(Core.getInstance(), 0, 10);
		
		this.board = board;
	}
	public void setBoard(Player player) {
		player.setScoreboard(board);
	}
	
	
}
