package net.keylon.me.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import net.keylon.me.config.SimpleConfig;
import net.keylon.me.utils.Common;

public class MegaspawnCommand extends PlayerCommand {
	
	

	public MegaspawnCommand() {
		super("Megaspawn");
	}

	@Override
	protected void run(Player player, String[] args) {
		if (!player.hasPermission("megawars.admin")) {
			Common.tell(player, "&cError message [Unauthorized]");
			return;
		}
		
		if (args.length == 0) {
			Common.tell(player, "(TODO) HELP");
			return;
		}
		
		if (args[0].equalsIgnoreCase("set")) {
			setSpawns(player, args, args[1]);
		} else {
			Common.tell(player, "(TODO) help message"); 
		}
		
		
	}
	
	protected void setSpawns(Player player, String[] args, String type) {
		if (!(type.equalsIgnoreCase("killers") || type.equalsIgnoreCase("attackers") || type.equalsIgnoreCase("lobby"))) { Common.tell(player, "You must enter a valid type"); return;}
		Location pos = player.getLocation();
		if (pos.getWorld().getBlockAt(pos.getBlockX(), pos.getBlockY()-1, pos.getBlockZ()).getType() == Material.AIR) {
			Common.tell(player, "&4You must be on the ground to set a spawn point.");
			return;
		}
		Boolean isOpen = true;
		//This should get the blocks around the player's feet and head. Don't quote me. I'll test later.
		for (int x = -1; x < 2; x++) {
			for (int y = 0; y < 2; y++) {
				for (int z = -1; z < 2; z++) {
					Block block = pos.getWorld().getBlockAt(pos.getBlockX() + x, pos.getBlockY() + y, pos.getBlockZ() + z);
					if (!(block.getType() == Material.AIR)) {
						isOpen = false;
						break;
					}
				}
			}
		}
		if (!isOpen && type.equalsIgnoreCase("killers")) {
			Common.tell(player, "Please move to an open area.");
			return;
		}
		SimpleConfig config = new SimpleConfig("config.yml");
		config.set("spawnpoints." + type.toLowerCase(), getStringFromLocation(pos));
		Common.tell(player, "&6" + type + "'s &7spawn point has been set.");
		config.saveConfig();
	}
	
	protected String getStringFromLocation(Location loc) {  
	    if (loc == null) {
	    return "";
	    }
	    return loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ() + ":" + loc.getYaw() + ":" + loc.getPitch() ;  
    }
	
	protected Location getLocationFromString(String str) {
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
