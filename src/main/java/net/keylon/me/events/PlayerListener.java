package net.keylon.me.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.keylon.me.Core;
import net.keylon.me.utils.Common;
import net.keylon.me.utils.LobbyScoreboard;
import net.keylon.me.utils.PlayerData;

public class PlayerListener implements Listener {

	@EventHandler
	public void chatFormat(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();

		if (Queue.getKillers().contains(p.getName())) {
			event.setFormat(Common.colorize("&8(&4Killer&8) &c" + p.getDisplayName() + " &8» &e" + event.getMessage()));
			return;
		} else if (Queue.getGuardians().contains(p.getName())) {
			event.setFormat(
					Common.colorize("&8(&2Guardian&8) &7" + p.getDisplayName() + " &8» &a" + event.getMessage()));
			return;
		} else if (Queue.getAttackers().contains(p.getName())) {
			event.setFormat(
					Common.colorize("&8(&7Attacker&8) &7" + p.getDisplayName() + " &8» &7" + event.getMessage()));
			return;
		}
		event.setFormat(Common.colorize("&8(&7Lobby&8) &7" + p.getDisplayName() + " &8» &7" + event.getMessage()));

	}

	// STOP TEAM KILLING
	@EventHandler
	public void hitHandler(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player player = (Player) e.getDamager();
			Player hit = (Player) e.getEntity();
			if (Queue.getKillers().contains(player.getName()) && Queue.getKillers().contains(hit.getName())) {
				return;
			}
			if (Queue.getAttackers().contains(player.getName()) && Queue.getAttackers().contains(hit.getName())) {
				return;
			}
			if (Queue.getAttackers().contains(player.getName()) && Queue.getGuardians().contains(hit.getName())) {
				return;
			}
			if (Queue.getGuardians().contains(player.getName()) && Queue.getGuardians().contains(hit.getName())) {
				return;
			}
			if (Queue.getLobby().contains(player.getName()) || Queue.getLobby().contains(hit.getName())) {
				return;
			}
		}
	}

	@EventHandler
	public void setupPlayer(PlayerJoinEvent e) {

		for (String playern : Queue.getLobby()) {
			Player player = Bukkit.getPlayer(playern);
			if (!player.isOnline()) {
				continue;
			}
			LobbyScoreboard.buildScoreboard(player);
		}

		Player player = e.getPlayer();
		// BELOW IS TEST
		PlayerData pl = new PlayerData(player);

		GameStart.CheckStart();

	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onHungerDeplete(FoodLevelChangeEvent e) {

		e.setFoodLevel(20);

	}

	@EventHandler
	public void respawn(PlayerRespawnEvent e) {
		if (Queue.getKillers().contains(e.getPlayer().getName())) {
			Core.instance.getKitManager().getKit(new PlayerData(e.getPlayer()).getSelectedKit("KILLER"))
					.gear(e.getPlayer());
			return;
		}
		if (Queue.getAttackers().contains(e.getPlayer().getName())) {
			Core.instance.getKitManager().getKit(new PlayerData(e.getPlayer()).getSelectedKit("ATTACKER"))
					.gear(e.getPlayer());
			return;
		}
	}

	@EventHandler
	public void noTeamAttack(PlayerInteractEntityEvent e) {
		if (Queue.killers.contains(e.getPlayer().getName())) {
			if (e.getRightClicked().getType() == EntityType.PLAYER) {
				if (Queue.killers.contains(e.getRightClicked().getType().getName())) {
					e.setCancelled(true);
					return;
				}
				return;
			}
			return;
		} else if (Queue.attackers.contains(e.getPlayer().getName())) {
			if (e.getRightClicked().getType() == EntityType.PLAYER) {
				if (Queue.attackers.contains(e.getRightClicked().getType().getName())) {
					e.setCancelled(true);
					return;
				}
				return;
			}
			return;
		} else if (Queue.guardians.contains(e.getPlayer().getName())) {
			if (e.getRightClicked().getType() == EntityType.PLAYER) {
				if (Queue.guardians.contains(e.getRightClicked().getType().getName())) {
					e.setCancelled(true);
					return;
				}
				return;
			}
			return;
		}

	}

	@EventHandler
	public void dropFlag(PlayerDeathEvent e) {
		if (Queue.killers.contains(e.getEntity().getName())) {
			if (e.getDrops().contains(Material.LIME_WOOL)) {
				Location loc = new Location(e.getEntity().getWorld(), e.getEntity().getLocation().getBlockX(),
						e.getEntity().getLocation().getBlockY() + 1, e.getEntity().getLocation().getBlockZ());
				e.getEntity().getLocation().getBlockX();

				Bukkit.getServer().getWorld(e.getEntity().getWorld().getName()).getBlockAt(loc)
						.setType(Material.LIME_WOOL);
				Bukkit.broadcastMessage(Common.colorize("&7A &a&lFlag &7has been dropped!"));
			}
		}
	}

	@EventHandler
	public void onBuild(final BlockPlaceEvent e) {
		new BukkitRunnable() {

			@Override
			public void run() {
				Bukkit.getServer().getWorld(e.getPlayer().getWorld().getName())
						.getBlockAt(e.getBlockPlaced().getLocation()).setType(Material.AIR);
			}

		}.runTaskLater(Core.getInstance(), 100);
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {

		e.setCancelled(true);
	}
}
