package net.keylon.me.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.keylon.me.Core;
import net.keylon.me.kits.AttackerGUI;
import net.keylon.me.kits.GuardianGUI;
import net.keylon.me.kits.KillerGUI;
import net.keylon.me.utils.Common;

public class KitsListener implements Listener {
	
	private Core main;

	public KitsListener(Core main) {
		this.main = main;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if (player.getInventory().getName().equals(Common.colorize("&6&lKits"))){
		if (e.getCurrentItem() != null) {

			switch (e.getCurrentItem().getType()) {
				case IRON_SWORD:
					AttackerGUI.attackerGUI(player);
					break;
				case ENDER_EYE:
					GuardianGUI.attackerGUI(player);
					break;
				case DIAMOND_SWORD:
					KillerGUI.attackerGUI(player);
					
					break;
				default:
					return;
			}
		}
		}
	}

}
