package net.keylon.me.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import net.keylon.me.utils.Common;
import net.keylon.me.utils.ItemBuilder;

public class DefaultGuardian extends Kit {

	public DefaultGuardian() {
		super("DefaultGuardian");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String kitType() {
		// TODO Auto-generated method stub
		return "Guardian";
	}
	
	@Override
	public void addUpgradesAndGear(Player player, ItemBuilder sword, ItemBuilder helmet, ItemBuilder chestplate,
			ItemBuilder leggings, ItemBuilder boots) {
		// TODO Auto-generated method stub
		player.getInventory().setArmorContents(new ItemStack[] {boots.build(), leggings.build(), chestplate.build(), helmet.build()});
		player.updateInventory();
		return;
	}

	@Override
	public Material inventoryItem() {
		// TODO Auto-generated method stub
		return Material.IRON_SWORD;
	}

	@Override
	public void gear(Player player) {
		ItemBuilder helmet = new ItemBuilder(Material.IRON_HELMET).name(Common.colorize("&6Guardian &7Helmet")).flag(ItemFlag.HIDE_ATTRIBUTES).unbreakable(true);
		ItemBuilder sword = new ItemBuilder(Material.IRON_SWORD).name(Common.colorize("&6Guardian &7Sword")).flag(ItemFlag.HIDE_ATTRIBUTES).unbreakable(true);
		ItemBuilder chestplate = new ItemBuilder(Material.IRON_CHESTPLATE).name(Common.colorize("&6Guardian &7Chestplate")).flag(ItemFlag.HIDE_ATTRIBUTES).unbreakable(true);
		ItemBuilder leggings = new ItemBuilder(Material.IRON_LEGGINGS).name(Common.colorize("&6Guardian &7Leggings")).flag(ItemFlag.HIDE_ATTRIBUTES).unbreakable(true);
		ItemBuilder boots = new ItemBuilder(Material.IRON_BOOTS).name(Common.colorize("&6Guardian &7Boots")).flag(ItemFlag.HIDE_ATTRIBUTES).unbreakable(true);
		
		addUpgradesAndGear(player, sword, helmet, chestplate, leggings, boots);
		
	}
	
}
