package net.keylon.me.events;

import java.util.ArrayList;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.keylon.me.Core;
import net.keylon.me.config.SimpleConfig;
import net.keylon.me.utils.Common;
import net.keylon.me.utils.PlayerData;
import net.keylon.me.utils.RandomNumberGenerator;

public class GameStart {
	
	public static boolean isForcedToBegin = false;
	public static boolean gameHasBegunAlready = false;
	public static boolean isGameInProgress = false;



	public static void CheckStart() {
		int startMinimum = Bukkit.getServer().getOnlinePlayers().size();
		
		if (gameHasBegunAlready) { return;}
		
		if((startMinimum >= 50 || isForcedToBegin) && !gameHasBegunAlready) {
			
			
			SimpleConfig config = new SimpleConfig("config.yml");
			if (!(config.isSet("spawnpoints.attackers") && config.isSet("spawnpoints.killers") && config.isSet("spawnpoints.lobby"))) {
				Bukkit.broadcastMessage(Common.colorize("&7The game cannot begin because the spawnpoints are not properly setup."));
				return;
			}
			
			isGameInProgress = true;
			new BukkitRunnable() {
				int numTillBegin = 30;
				boolean continueRunning = true;
				@Override
				public void run() {
					if (numTillBegin == 30) {
						Bukkit.broadcastMessage(Common.colorize("&6&l30 &7seconds until game begins!"));
					} else if (numTillBegin == 15){
						Bukkit.broadcastMessage(Common.colorize("&6&l15 &7seconds until game begins!"));
					} else if (numTillBegin == 10){
						Bukkit.broadcastMessage(Common.colorize("&6&l10 &7seconds until game begins!"));
					} else if (numTillBegin == 5) {
						Bukkit.broadcastMessage(Common.colorize("&6&l5 &7seconds until game begins!"));
					} else if (numTillBegin == 3){
						Bukkit.broadcastMessage(Common.colorize("&6&l3"));
					} else if (numTillBegin == 2){
						Bukkit.broadcastMessage(Common.colorize("&6&l2"));
					} else if (numTillBegin == 1){
						Bukkit.broadcastMessage(Common.colorize("&6&l1"));
					} else if (numTillBegin <= 0 && continueRunning){
						continueRunning = false;
						gameStart();
						gameHasBegunAlready = true;
						Queue.gameBegun = true;
						this.cancel();
					}
					numTillBegin--;
				}
			}.runTaskTimer(Core.getInstance(), 0, 20);
		}
	}
	
	public static void gameStart() {	
		Random rand = new Random();
		if (gameHasBegunAlready) { return;}
		if (!isGameInProgress) { return; }
		int startMinimum = Queue.getLobby().size(); 
		RandomNumberGenerator rg = new RandomNumberGenerator(3,Bukkit.getOnlinePlayers().size());
		ArrayList<Integer> number = rg.getNumbers();
		
		SimpleConfig config = new SimpleConfig("config.yml");
		
		Location kSpawn = getLocationFromString(config.getString("spawnpoints.killers"));
		Location aSpawn = getLocationFromString(config.getString("spawnpoints.attackers")); 
		Bukkit.broadcastMessage(Common.colorize("&7The game is now starting!"));
		Queue.getKillers().clear();
		String Killer1 = Queue.getLobby().get(number.get(0));
		Queue.getKillers().add(Killer1);
		String Killer2 = Queue.getLobby().get(number.get(1));
		Queue.getKillers().add(Killer2);
		String Killer3 = Queue.getLobby().get(number.get(2));
		Queue.getKillers().add(Killer3);
		Bukkit.broadcastMessage(Common.colorize("&7The killers are &c&l " + Killer1 + "&7, &c&l" + Killer2 + "&7, and &c&l" + Killer3));
		
		Queue.getLobby().remove(Killer1);
		Queue.getLobby().remove(Killer2);
		Queue.getLobby().remove(Killer3);
		
		/*Queue.getGuardians().clear();
		String Guard1 = Queue.getLobby().get(number.get(3));
		Queue.getGuardians().add(Guard1);
		String Guard2 = Queue.getLobby().get(number.get(4));
		Queue.getGuardians().add(Guard2);
		String Guard3 = Queue.getLobby().get(number.get(5));
		Queue.getGuardians().add(Guard3);
		
		Queue.getLobby().remove(Guard1);
		Queue.getLobby().remove(Guard2);
		Queue.getLobby().remove(Guard3);
		*/
		int x = -1;
		for (String name : Queue.getKillers()) {
			Location destination = new Location(kSpawn.getWorld(), kSpawn.getX() + x, kSpawn.getY(), kSpawn.getZ(), kSpawn.getYaw(), kSpawn.getPitch());
			x++;
			Player player = Bukkit.getPlayer(name);
			player.teleport(destination);
			//tb.setBoard(player);
			player.getInventory().clear();
			Core.instance.getKitManager().getKit(new PlayerData(player).getSelectedKit("KILLER")).gear(player);
		}
		int p = -1;
		for (String name : Queue.getLobby()) {
			Queue.attackers.add(name);
			Queue.lobby.remove(name);
			p++;
			Player player = Bukkit.getPlayer(name);
			player.teleport(aSpawn);
			//tb.setBoard(player);
			player.getInventory().clear();
			Core.instance.getKitManager().getKit(new PlayerData(player).getSelectedKit("ATTACKER")).gear(player);
		}
		/*for (String name : Queue.getGuardians()) {
			Queue.guardians.add(name);
			Queue.lobby.remove(name);
			Player player = Bukkit.getPlayer(name);
			player.teleport(aSpawn);
			Common.tell(player, "&aYou are a guardian!");
			player.getInventory().clear();
			Core.instance.getKitManager().getKit(new PlayerData(player).getSelectedKit("GUARDIAN")).gear(player);
		}
		*/
		Core.getInstance().getTimer().start();
		Core.getInstance().getCaptureTheNexus().restartGame();
		
	}
	
	protected static Location getLocationFromString(String str) {
		if (str == null || str.trim() == null) { return null; }
		String[] split = str.split(":");
		if (split.length != 6) { return null; }
		World world = Bukkit.getServer().getWorld(split[0]);
		Double x = Double.valueOf(split[1]);
		Double y = Double.valueOf(split[2]);
		Double z = Double.valueOf(split[3]);
		float yaw = Float.valueOf(split[4]);
		float pitch = Float.valueOf(split[5]);
		return new Location(world,x,y,z,yaw,pitch);
	}
	

}
