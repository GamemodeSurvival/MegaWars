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

public class GuardianGUI {
	
	public static void attackerGUI(Player player) {
		Inventory gui = Bukkit.createInventory(null, 27, ChatColor.YELLOW + "" + ChatColor.BOLD + "Guardian Kits");
		
		ItemStack speed = new ItemStack(Material.BEACON);
		ItemMeta ms = speed.getItemMeta();
		
		ms.setDisplayName(Common.colorize("&aDefault"));
		ms.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		ms.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		speed.setItemMeta(ms);
		
		gui.setItem(11, speed);
		
		ItemStack jump = new ItemStack(Material.ENDER_PEARL);
		ItemMeta mj = speed.getItemMeta();
		
		mj.setDisplayName(Common.colorize("&aRetriever"));
		mj.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		mj.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		jump.setItemMeta(mj);
		
		gui.setItem(13, jump);
		
		
		ItemStack regen = new ItemStack(Material.SUGAR);
		ItemMeta mr = speed.getItemMeta();
		
		mr.setDisplayName(Common.colorize("&aRusher"));
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
