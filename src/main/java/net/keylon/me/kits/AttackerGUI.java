package net.keylon.me.kits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.keylon.me.utils.Common;

public class AttackerGUI {
	
	public static void attackerGUI(Player player) {
		Inventory gui = Bukkit.createInventory(null, 27, ChatColor.GRAY + "" + ChatColor.BOLD + "Attacker Kits");
		
		ItemStack speed = new ItemStack(Material.ARMOR_STAND);
		ItemMeta ms = speed.getItemMeta();
		
		ms.setDisplayName(Common.colorize("&aDefault"));
		ms.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		ms.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		speed.setItemMeta(ms);
		
		gui.setItem(11, speed);
		
		ItemStack jump = new ItemStack(Material.ANVIL);
		ItemMeta mj = speed.getItemMeta();
		
		mj.setDisplayName(Common.colorize("&aStriker"));
		mj.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		mj.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		jump.setItemMeta(mj);
		
		gui.setItem(13, jump);
		
		
		ItemStack regen = new ItemStack(Material.ARROW);
		ItemMeta mr = speed.getItemMeta();
		
		mr.setDisplayName(Common.colorize("&aArcher"));
		mr.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		mr.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		regen.setItemMeta(mr);
		
		gui.setItem(15, regen);
		
		for(int slot = 0; slot < gui.getSize(); slot++) {
		    if(gui.getItem(slot) == null) {
		        gui.setItem(slot, new ItemStack(Material.RED_STAINED_GLASS_PANE));
		    }
		}
		
		player.openInventory(gui);
	}


}
