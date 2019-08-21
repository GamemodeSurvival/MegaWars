package net.keylon.me.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.keylon.me.utils.Common;

public class KitSelectorCommand extends PlayerCommand {

	public KitSelectorCommand() {
		super("kits");
	}

	@Override
	protected void run(Player player, String[] args) {
			Inventory gui = Bukkit.createInventory(null, 27, ChatColor.GOLD + "" + ChatColor.BOLD + "Kits");
			
			ItemStack speed = new ItemStack(Material.IRON_SWORD);
			ItemMeta ms = speed.getItemMeta();
			
			ms.setDisplayName(Common.colorize("&7Attacker Kits"));
			ms.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
			ms.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			ms.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			
			speed.setItemMeta(ms);
			
			gui.setItem(11, speed);
			
			ItemStack jump = new ItemStack(Material.ENDER_EYE);
			ItemMeta mj = speed.getItemMeta();
			
			mj.setDisplayName(Common.colorize("&aGuardian Kits"));
			mj.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			mj.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			
			jump.setItemMeta(mj);
			
			gui.setItem(13, jump);
			
			
			ItemStack regen = new ItemStack(Material.DIAMOND_SWORD);
			ItemMeta mr = speed.getItemMeta();
			
			mr.setDisplayName(Common.colorize("&cKiller Kits"));
			ms.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
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
