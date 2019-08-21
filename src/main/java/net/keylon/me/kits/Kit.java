package net.keylon.me.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import net.keylon.me.utils.ItemBuilder;

public abstract class Kit {
	
	public String name;
	public Kit(String name) {
		this.name = name;
	}
	
	public abstract void gear(Player player);
	
	public abstract Material inventoryItem();
	
	public abstract String kitType();
	
	public abstract void addUpgradesAndGear(Player player, ItemBuilder sword, ItemBuilder helmet, ItemBuilder chestplate, ItemBuilder leggings, ItemBuilder boots);
	

}
