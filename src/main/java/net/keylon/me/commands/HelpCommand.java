package net.keylon.me.commands;

import org.bukkit.entity.Player;

import net.keylon.me.utils.Common;

public class HelpCommand extends PlayerCommand {

	public HelpCommand() {
		super("help");
	}
	
	

	@Override
	protected void run(Player player, String[] args) {
		Common.tell(player, "&8&m-------------=&8( &6&lHelp &8)&8&m=-------------",
				"&e/perks &8» &7Open up the perks menu.",
				"&e/kits &8» &7Open up the kits menu.",
				"&e/upgrades &8» &7Open up the upgrades menu.",
				"&8&m------------------------------------");
		
	}

}
