package net.keylon.me.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import net.keylon.me.Core;
import net.keylon.me.config.SimplePlayerFile;
import net.keylon.me.kits.Kit;

public class PlayerData {
	
	private UUID uuid;
	private Player player;
	Integer kills;
	Integer deaths;
	String currentKit;
	SimplePlayerFile dataF;
	public PlayerData(UUID uuid) {
		this.uuid = uuid;
		this.player = Bukkit.getPlayer(uuid);
		dataF = new SimplePlayerFile(uuid.toString() + ".yml");
		reloadData();
	}
	
	public PlayerData(Player player) {
		this.uuid = player.getUniqueId();
		this.player = player;
		dataF = new SimplePlayerFile(uuid.toString() + ".yml");
		reloadData();
	}
	
	public UUID getUUID() {
		return uuid;
	}
	
	public Integer getKitWeaponLevel(Kit kit) {
		return getKitWeaponLevel(kit.name);
	}
	
	public Integer getKitWeaponLevel(String kit) {
		return dataF.getConfig().getInt("kits." + kit + ".weapon");
	}
	
	public Integer getKitGearLevel(Kit kit) {
		return getKitGearLevel(kit.name);
	}
	
	public Integer getKitGearLevel(String kit) {
		return dataF.getConfig().getInt("kits." + kit + ".gear");
	}
	
	public void setKitGearLevel(Kit kit, Integer level) {
		setKitGearLevel(kit.name, level);
	}
	
	public void setKitGearLevel(String kit, Integer level) {
		dataF.getConfig().set("kits." + kit + ".gear", level);
		dataF.saveFile();
	}
	
	public void setKitWeaponLevel(Kit kit, Integer level) {
		setKitGearLevel(kit.name, level);
	}
	
	public void setKitWeaponLevel(String kit, Integer level) {
		dataF.getConfig().set("kits." + kit + ".weapon", level);
		dataF.saveFile();
	}
	
	public void setPlayerLastUsedKit(String type, String kit) {
		ArrayList<String> types = new ArrayList<String>( Arrays.asList("KILLER", "ATTACKER", "GUARDIAN"));
		if (!types.contains(type.toUpperCase())) {return;}
		dataF.getConfig().set("kits-selected." + type.toUpperCase(), kit);
	}
	
	public String getSelectedKit(String type) {
		ArrayList<String> types = new ArrayList<String>( Arrays.asList("KILLER", "ATTACKER", "GUARDIAN"));
		if (!types.contains(type.toUpperCase())) {return null;}
		return dataF.getConfig().getString("kits-selected." + type.toUpperCase());
	}
	
	public void reloadData() {
		File dataFolder = new File(Core.getInstance().getDataFolder() + File.separator + "players" + File.separator);
		dataFolder.mkdirs();
		SimplePlayerFile dataF = new SimplePlayerFile(uuid.toString() + ".yml");
		if (dataF.getConfig().isSet("data.kills")) {
			this.kills = dataF.getConfig().getInt("data.kills");
			
		} else {
			this.kills = 0;
			dataF.getConfig().set("data.kills", player.getStatistic(Statistic.PLAYER_KILLS));
		}
		
		if (!dataF.getConfig().isSet("kits-selected.KILLER")) {
			dataF.getConfig().set("kits-selected.KILLER", "DefaultKiller");
		}
		
		if (!dataF.getConfig().isSet("kits-selected.ATTACKER")) {
			dataF.getConfig().set("kits-selected.ATTACKER", "DefaultAttacker");
		}
		
		if (!dataF.getConfig().isSet("kits-selected.GUARDIAN")) {
			dataF.getConfig().set("kits-selected.GUARDIAN", "DefaultGuardian");
		}
		
		for (Kit kit : Core.getInstance().getKitManager().getKits()) {
			if (!(dataF.getConfig().isSet("kits." + kit.name))) {
				dataF.getConfig().set("kits." + kit.name + ".weapon", 1);
				dataF.getConfig().set("kits." + kit.name + ".gear", 1);
			}
		}
		
		dataF.saveFile();
		
	}
	
	
	
}
