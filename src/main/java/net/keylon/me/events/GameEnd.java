package net.keylon.me.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import net.keylon.me.config.SimpleConfig;
import net.keylon.me.gamemodes.CaptureTheNexus;
import net.keylon.me.utils.Common;
import net.keylon.me.utils.LobbyScoreboard;
import net.keylon.me.world.WorldManager;

public class GameEnd {

	@SuppressWarnings("deprecation")
	public static void gameEnd() {

		WorldManager.regenWorld();
		GameStart.isForcedToBegin = false;
		GameStart.gameHasBegunAlready = false;

		SimpleConfig config = new SimpleConfig("config.yml");

		Location lobbyPos = getLocationFromString(config.getString("spawnpoints.lobby"));

		for (String playern : Queue.getKillers()) {
			Player player = Bukkit.getPlayer(playern);
			if (!player.isOnline()) {
				continue;
			}
			player.setHealth(player.getMaxHealth());
			player.getInventory().clear();
			player.teleport(lobbyPos);
			LobbyScoreboard.buildScoreboard(player);
			Queue.killers.remove(playern);
			Queue.lobby.add(playern);
		}
		for (String playern : Queue.getAttackers()) {
			Queue.attackers.remove(playern);
			Queue.lobby.add(playern);
			Player player = Bukkit.getPlayer(playern);
			if (!player.isOnline()) {
				continue;
			}
			player.setHealth(player.getMaxHealth());
			player.getInventory().clear();
			player.teleport(lobbyPos);
			LobbyScoreboard.buildScoreboard(player);
		}
		for (String playern : Queue.getGuardians()) {
			Queue.guardians.remove(playern);
			Queue.lobby.add(playern);
			Player player = Bukkit.getPlayer(playern);
			if (!player.isOnline()) {
				continue;
			}
			player.setHealth(player.getMaxHealth());
			player.getInventory().clear();
			player.teleport(lobbyPos);
			LobbyScoreboard.buildScoreboard(player);
		}
		GameStart.isGameInProgress = false;
		if (CaptureTheNexus.flagsCaptured == 3) {
			Bukkit.broadcastMessage(Common.colorize("&c&lKillers &7have won the game!"));
			CaptureTheNexus.flagsCaptured = 0;
			return;
		} else {
			Bukkit.broadcastMessage(Common.colorize("&9&lAttackers &7have won the game!"));
			CaptureTheNexus.flagsCaptured = 0;
			return;
		}
	}

	protected static Location getLocationFromString(String str) {
		if (str == null || str.trim() == null) {
			return null;
		}
		String[] split = str.split(":");
		if (split.length != 6) {
			return null;
		}
		World world = Bukkit.getServer().getWorld(split[0]);
		Double x = Double.valueOf(split[1]);
		Double y = Double.valueOf(split[2]);
		Double z = Double.valueOf(split[3]);
		float yaw = Float.valueOf(split[4]);
		float pitch = Float.valueOf(split[5]);
		return new Location(world, x, y, z, yaw, pitch);
	}

}
