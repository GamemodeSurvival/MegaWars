package net.keylon.me.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.keylon.me.utils.Common;

public class AttackerUpgrades implements Listener {

	@EventHandler
	public void upgradeAttacker(PlayerDeathEvent e) {
		if (e.getEntity().getKiller() == null) { return; }
		String user = e.getEntity().getName();
		if(Queue.getKillers().contains(user)) {
			Player attacker = e.getEntity().getKiller();
			int level = 1;
			Common.tell(attacker, "&7Your gear has been upgraded to &6&lLevel " + level);
		}
	}
	
}
