package net.keylon.me.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.keylon.me.Core;
import net.keylon.me.utils.Common;

public class MenuListener implements Listener {
	
	public static List<String> speedPlayers = new ArrayList<String>();
	public static List<String> jumpPlayers = new ArrayList<String>();
	public static List<String> strongPlayers = new ArrayList<String>();
	public static List<String> regenPlayers = new ArrayList<String>();

	private Core main;

	
	public MenuListener(Core main) {
		this.main = main;
	}

	public void resetStats() {
		speedPlayers.clear();
		jumpPlayers.clear();
		strongPlayers.clear();
		regenPlayers.clear();
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();

		if (e.getCurrentItem() != null) {
			if (!(e.getInventory().getName().trim().equalsIgnoreCase((ChatColor.GOLD + "" + ChatColor.BOLD + "Perk Shop").trim()))) { return; }
			switch (e.getCurrentItem().getType()) {
				case SUGAR:
					if(speedPlayers.contains(player.getName())) {
						Common.tell(player, "&7Sorry! You have already purchased this!");
						player.closeInventory();
						return;
					}
					Common.tell(player, "&7You now have &6&lSpeed I");
					speedPlayers.add(player.getName());
					
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, true, false, false));
					player.closeInventory();
					break;
				case RABBIT_FOOT:
					if(jumpPlayers.contains(player.getName())) {
						Common.tell(player, "&7Sorry! You have already purchased this!");
						player.closeInventory();
						return;
					}
					Common.tell(player, "&7You now have &6&lJump Boost I");
					jumpPlayers.add(player.getName());
					
					player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 1, true, false, false));
					player.closeInventory();
					break;
				case REDSTONE:
					if(strongPlayers.contains(player.getName())) {
						Common.tell(player, "&7Sorry! You have already purchased this!");
						player.closeInventory();
						return;
					}
					Common.tell(player, "&7You now have &6&lStrength I");
					strongPlayers.add(player.getName());
					
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 1, true, false, false));
					player.closeInventory();
					break;
				case APPLE:
					if(regenPlayers.contains(player.getName())) {
						Common.tell(player, "&7Sorry! You have already purchased this!");
						player.closeInventory();
						return;
					}
					Common.tell(player, "&7You now have &6&lRegeneration I");
					regenPlayers.add(player.getName());
					
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000, 1, true, false, false));
					player.closeInventory();
					break;
				default:
					return;
			}
		}
	}
	
	@EventHandler
	public void playerSpawnRestore(PlayerRespawnEvent e) {
		if(speedPlayers.contains(e.getPlayer().getName())) {
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, true, false, false));
		} 
		if(jumpPlayers.contains(e.getPlayer().getName())) {
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 1, true, false, false));
		}
		if(strongPlayers.contains(e.getPlayer().getName())) {
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 1, true, false, false));
		}
		if(regenPlayers.contains(e.getPlayer().getName())) {
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000, 1, true, false, false));
		}
	}
	
	@EventHandler
	public void restoreStats(PlayerJoinEvent e) {
		if(speedPlayers.contains(e.getPlayer().getName())) {
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, true, false, false));
		} 
		if(jumpPlayers.contains(e.getPlayer().getName())) {
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 1, true, false, false));
		}
		if(strongPlayers.contains(e.getPlayer().getName())) {
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 1, true, false, false));
		}
		if(regenPlayers.contains(e.getPlayer().getName())) {
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000, 1, true, false, false));
		}
	}

}
