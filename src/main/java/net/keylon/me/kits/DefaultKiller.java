package net.keylon.me.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import net.keylon.me.utils.Common;
import net.keylon.me.utils.ItemBuilder;
import net.keylon.me.utils.PlayerData;

public class DefaultKiller extends Kit {
	
	public DefaultKiller() {
		super("DefaultKiller");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String kitType() {
		return "Killer";
	}
	
	@Override
	public void gear(Player player) {	
	ItemBuilder helmet = new ItemBuilder(Material.DIAMOND_HELMET).name(Common.colorize("&cKiller &7Helmet")).flag(ItemFlag.HIDE_ATTRIBUTES).unbreakable(true);
	ItemBuilder sword = new ItemBuilder(Material.DIAMOND_SWORD).name(Common.colorize("&cKiller &7Sword")).flag(ItemFlag.HIDE_ATTRIBUTES).unbreakable(true);
	ItemBuilder chestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE).name(Common.colorize("&cKiller &7Chestplate")).flag(ItemFlag.HIDE_ATTRIBUTES).unbreakable(true);
	ItemBuilder leggings = new ItemBuilder(Material.DIAMOND_LEGGINGS).name(Common.colorize("&cKiller &7Leggings")).flag(ItemFlag.HIDE_ATTRIBUTES).unbreakable(true);
	ItemBuilder boots = new ItemBuilder(Material.DIAMOND_BOOTS).name(Common.colorize("&cKiller &7Boots")).flag(ItemFlag.HIDE_ATTRIBUTES).unbreakable(true);
	
	addUpgradesAndGear(player, sword, helmet, chestplate, leggings, boots);
	}
	
	@Override
	public void addUpgradesAndGear(Player player, ItemBuilder sword, ItemBuilder helmet, ItemBuilder chestplate,
			ItemBuilder leggings, ItemBuilder boots) {
		PlayerData dt = new PlayerData(player);
		if (dt.getKitWeaponLevel(this) == 2) {
			sword.enchantment(Enchantment.DAMAGE_ALL, 1);
		}
		//Add other upgrades here.
		
		player.getInventory().addItem(sword.build());
		player.getInventory().setArmorContents(new ItemStack[] {boots.build(), leggings.build(), chestplate.build(), helmet.build()});
		player.updateInventory();
		return;
	}

	@Override
	public Material inventoryItem() {
		// TODO Auto-generated method stub
		return Material.DIAMOND_SWORD;
	}

}
