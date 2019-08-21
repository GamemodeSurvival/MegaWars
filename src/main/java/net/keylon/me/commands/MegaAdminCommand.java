package net.keylon.me.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.keylon.me.events.GameEnd;
import net.keylon.me.events.GameStart;
import net.keylon.me.events.Queue;
import net.keylon.me.utils.Common;

public class MegaAdminCommand extends PlayerCommand {

	public MegaAdminCommand() {
		super("Mega");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(Player player, String[] args) {
		if (!player.hasPermission("megawars.admin")) {
			Common.tell(player, "&cError message [Unauthorized]");
			return;
		}
		
		if (args.length == 0) {
			Common.tell(player, "&cHelp message (TODO)");
			return;
		}
		
		if (args[0].equalsIgnoreCase("start")) {
			if(Bukkit.getOnlinePlayers().size() < 6) {
				Common.tell(player, "&7You must have a minimum of 6 players to start.");
				return;
			}
			Common.tell(player, "&7The game has been forced to start");
			GameStart.isForcedToBegin = true;
			GameStart.CheckStart();
		}
		
		if (args[0].equalsIgnoreCase("end")) {
			if(GameStart.isGameInProgress = true) {
			Common.tell(player, "&7The game has ended.");
			GameStart.isGameInProgress = false;
			GameStart.isForcedToBegin = false;
			GameStart.gameHasBegunAlready = false;
			Queue.gameBegun = false;
			GameEnd.gameEnd();
		} else {
			Common.tell(player, "&7There is no on-going game.");
		}
		}
		
		
	}

}
