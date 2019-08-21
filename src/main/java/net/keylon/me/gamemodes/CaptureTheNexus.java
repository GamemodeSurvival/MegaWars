package net.keylon.me.gamemodes;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.keylon.me.config.SimpleConfig;
import net.keylon.me.events.GameEnd;
import net.keylon.me.events.GameStart;
import net.keylon.me.events.Queue;
import net.keylon.me.utils.Common;

public class CaptureTheNexus implements Listener {

	HashMap<Location, Boolean> collectedFlags = new HashMap<Location, Boolean>();
	public static int flagsCaptured = 0;
	
	
	
	
	public void restartGame() {
		collectedFlags.clear();
		SimpleConfig config = new SimpleConfig("config.yml");
		Location locationorig = getLocationFromString(config.getString("spawnpoints.killers"));
		Location loc2 = locationorig.add(1,0,0);
		Location loc3 = locationorig.add(-1,0,0);
		collectedFlags.put(locationorig, false);
		collectedFlags.put(loc2, false);
		collectedFlags.put(loc3, false);
	}
	
	@EventHandler
	public void collectCore(PlayerInteractEvent e) {
		if(Queue.getKillers().contains(e.getPlayer().getName())) {
		
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
		if(e.getClickedBlock().getType() == Material.LIME_WOOL) {
		e.getClickedBlock().setType(Material.AIR);
		ItemStack flag = new ItemStack(Material.LIME_WOOL);
		ItemMeta m = flag.getItemMeta();
		
		m.setDisplayName(Common.colorize("&a&lGreen &fFlag"));
		m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		
		flag.setItemMeta(m);
		
		e.getPlayer().getInventory().addItem(flag);
		
		e.getPlayer().getWorld().playEffect(e.getClickedBlock().getLocation(), Effect.DRAGON_BREATH, 40);
		String test = "testing {player}";
		test = test.replaceAll("{player}", e.getPlayer().getName());
		Bukkit.broadcastMessage(Common.colorize("&8(&4Killer&8) &c" + e.getPlayer().getName() + " &7has picked up a flag!"));
		
		
	}
		}
		}
	}
	SimpleConfig config = new SimpleConfig("config.yml");
	Location kSpawn = getLocationFromString(config.getString("spawnpoints.killers"));
	Location aSpawn = getLocationFromString(config.getString("spawnpoints.attackers")); 
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if(Queue.getKillers().contains(e.getEntity().getName())) {
			Location destination = new Location(kSpawn.getWorld(), kSpawn.getX(), kSpawn.getY()+1, kSpawn.getZ(), kSpawn.getYaw(), kSpawn.getPitch());
			Player player = e.getEntity();
			player.teleport(destination);
		}
		if(Queue.getAttackers().contains(e.getEntity().getName())) {
			Location destination = new Location(aSpawn.getWorld(), aSpawn.getX(), aSpawn.getY(), aSpawn.getZ(), aSpawn.getYaw(), aSpawn.getPitch());
			Player player = e.getEntity();
			player.teleport(destination);
		}
	}
	public void onFlagPlace(BlockPlaceEvent e) {
		if (!(Queue.getKillers().contains(e.getPlayer().getName()))) { return; }
			Location destination = new Location(kSpawn.getWorld(), kSpawn.getX(), kSpawn.getY() - 1, kSpawn.getZ(), kSpawn.getYaw(), kSpawn.getPitch());
			Location destination1 = new Location(kSpawn.getWorld(), kSpawn.getX() + 1, kSpawn.getY() - 1, kSpawn.getZ(), kSpawn.getYaw(), kSpawn.getPitch());
			Location destination2 = new Location(kSpawn.getWorld(), kSpawn.getX() - 1, kSpawn.getY() - 1, kSpawn.getZ(), kSpawn.getYaw(), kSpawn.getPitch());
			if(e.getBlockPlaced().getLocation() == destination) {
				Bukkit.broadcastMessage(Common.colorize("&8(&4Killer&8) &c" + e.getPlayer().getName() + " &7has captured up a flag!"));
				Bukkit.getServer().getWorld(e.getPlayer().getWorld().getName()).getBlockAt(e.getBlockPlaced().getLocation()).setType(Material.EMERALD_BLOCK);
				flagsCaptured++;
				if (flagsCaptured == 3) {
					End();
					return;
				}
				return;
			}
			if(e.getBlockPlaced().getLocation() == destination1) {
				Bukkit.broadcastMessage(Common.colorize("&8(&4Killer&8) &c" + e.getPlayer().getName() + " &7has captured up a flag!"));
				Bukkit.getServer().getWorld(e.getPlayer().getWorld().getName()).getBlockAt(e.getBlockPlaced().getLocation()).setType(Material.EMERALD_BLOCK);
				flagsCaptured++;
				if (flagsCaptured == 3) {
					End();
					return;
				}
				return;
			}
			if(e.getBlockPlaced().getLocation() == destination2) {
				Bukkit.broadcastMessage(Common.colorize("&8(&4Killer&8) &c" + e.getPlayer().getName() + " &7has captured up a flag!"));
				Bukkit.getServer().getWorld(e.getPlayer().getWorld().getName()).getBlockAt(e.getBlockPlaced().getLocation()).setType(Material.EMERALD_BLOCK);
				flagsCaptured++;
				if (flagsCaptured == 3) {
					End();
					return;
				}
				return;
			}
		
		return;
	}
	
	
	public void Start() {
		GameStart.CheckStart();
		restartGame();
	}
	
	public void End() {
		collectedFlags.clear();
		GameEnd.gameEnd();
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
