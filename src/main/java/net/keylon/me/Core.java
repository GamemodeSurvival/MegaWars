package net.keylon.me;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import net.keylon.me.commands.HelpCommand;
import net.keylon.me.commands.KitSelectorCommand;
import net.keylon.me.commands.MegaAdminCommand;
import net.keylon.me.commands.MegaspawnCommand;
import net.keylon.me.commands.PerksCommand;
import net.keylon.me.events.AttackerUpgrades;
import net.keylon.me.events.KitsListener;
import net.keylon.me.events.MenuListener;
import net.keylon.me.events.PlayerListener;
import net.keylon.me.events.Queue;
import net.keylon.me.events.SidebarListener;
import net.keylon.me.gamemodes.CaptureTheNexus;
import net.keylon.me.kits.KitManager;
import net.keylon.me.utils.Common;
import net.keylon.me.utils.Timer;
import net.md_5.bungee.api.ChatColor;

public class Core extends JavaPlugin {

	public static Core instance;

	private Scoreboard s;
	private Timer timer;
	private KitManager kitmanager;
	private CaptureTheNexus ctn;

	@Override
	public void onEnable() {
		getLogger().info("&6&lMegaWars &ehas been enabled!");

		s = Bukkit.getScoreboardManager().getMainScoreboard();

		registerHealthBar();

		timer = new Timer(15 * 60);
		instance = this;
		kitmanager = new KitManager();

		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(new Queue(), this);
		getServer().getPluginManager().registerEvents(ctn = new CaptureTheNexus(), this);
		getServer().getPluginManager().registerEvents(new SidebarListener(this), this);
		getServer().getPluginManager().registerEvents(new MenuListener(this), this);
		getServer().getPluginManager().registerEvents(new AttackerUpgrades(), this);
		getServer().getPluginManager().registerEvents(new KitsListener(this), this);

		Common.registerCommand(new MegaspawnCommand());
		Common.registerCommand(new MegaAdminCommand());
		Common.registerCommand(new PerksCommand());
		Common.registerCommand(new HelpCommand());
		Common.registerCommand(new KitSelectorCommand());
	}

	public Core() {
		instance = this;
	}

	public Timer getTimer() {
		return this.timer;
	}

	public CaptureTheNexus getCaptureTheNexus() {
		return ctn;
	}

	public static Core getInstance() {
		return instance;
	}

	public KitManager getKitManager() {
		return kitmanager;
	}

	public void registerHealthBar() {
		if (s.getObjective("health") != null) {
			s.getObjective("health").unregister();
		}
		@SuppressWarnings("deprecation")
		Objective o = s.registerNewObjective("health", "health");
		o.setDisplayName(ChatColor.RED + "❤");
		o.setDisplaySlot(DisplaySlot.BELOW_NAME);
	}

}
