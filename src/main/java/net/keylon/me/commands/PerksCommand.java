package net.keylon.me.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.keylon.me.utils.Common;

public class PerksCommand extends PlayerCommand {
	
	public PerksCommand() {
		super("perks");
	}

	@Override
	protected void run(Player player, String[] args) {
		Inventory gui = Bukkit.createInventory(null, 27, ChatColor.GOLD + "" + ChatColor.BOLD + "Perk Shop");
		
		ItemStack speed = new ItemStack(Material.SUGAR);
		ItemMeta ms = speed.getItemMeta();
		
		ms.setDisplayName(Common.colorize("&aSpeed &8| &7500 Points"));
		ms.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		ms.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		speed.setItemMeta(ms);
		
		gui.setItem(10, speed);
		
		ItemStack jump = new ItemStack(Material.RABBIT_FOOT);
		ItemMeta mj = speed.getItemMeta();
		
		mj.setDisplayName(Common.colorize("&aJump Boost &8| &7500 Points"));
		mj.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		mj.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		jump.setItemMeta(mj);
		
		gui.setItem(12, jump);
		
		ItemStack strength = new ItemStack(Material.REDSTONE);
		ItemMeta mst = speed.getItemMeta();
		
		mst.setDisplayName(Common.colorize("&aStrength &8| &7750 Points"));
		mst.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		mst.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		strength.setItemMeta(mst);
		
		gui.setItem(14, strength);
		
		ItemStack regen = new ItemStack(Material.APPLE);
		ItemMeta mr = speed.getItemMeta();
		
		mr.setDisplayName(Common.colorize("&aRegeneration &8| &7750 Points"));
		mr.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		mr.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		regen.setItemMeta(mr);
		
		gui.setItem(16, regen);
		
		for(int slot = 0; slot < gui.getSize(); slot++) {
		    if(gui.getItem(slot) == null) {
		        gui.setItem(slot, new ItemStack(Material.RED_STAINED_GLASS_PANE));
		    }
		}
		
		player.openInventory(gui);
		
	}

}
