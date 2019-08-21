package net.keylon.me.world;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import net.keylon.me.config.SimpleConfig;

public class WorldManager implements Listener {

	public static List<iBlock> BlockData = new ArrayList<iBlock>();

	@EventHandler
	public void blockBreak(BlockBreakEvent e) {
		SimpleConfig config = new SimpleConfig("config.yml");
		if (e.getPlayer().getWorld() == Bukkit.getServer().getWorld(config.getString("world"))) {
			BlockData.add(new iBlock(e.getBlock()));
		}
	}

	public static void regenWorld() {
		for (iBlock blockData : BlockData) {
			Block block = blockData.getLocation().getWorld().getBlockAt(blockData.getLocation());
			block.setType(blockData.getMaterial());
		}
	}

}
