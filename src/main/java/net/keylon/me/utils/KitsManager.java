package net.keylon.me.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import net.keylon.me.Core;
import net.keylon.me.config.SimpleConfig;
import net.keylon.me.utils.Common;

public class KitsManager {

	private static Core plugin;

	@SuppressWarnings("static-access")
	public KitsManager(Core plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public static void createKit(Player p, String kitName) {
		SimpleConfig config = new SimpleConfig("config.yml");
		PlayerInventory inv = p.getInventory();

		if (config.getConfigurationSection("kits." + kitName) != null) {
			p.sendMessage(Common
					.colorize("&a" + kitName + " &7already exists! If you are updating a kit please use &a/update"));
			return;
		}

		String path = "kits." + kitName + ".";
		config.createSection("kits." + kitName);

		for (int i = 0; i < 36; i++) {
			ItemStack is = inv.getItem(i);

			if (is == null || is.getType() == Material.AIR)
				continue;

			String slot = path + "items." + i;
			config.set(slot + ".type", is.getType().toString().toLowerCase());
			config.set(slot + ".amount", is.getAmount());

			if (!is.hasItemMeta())
				continue;

			if (is.getItemMeta().hasDisplayName())
				config.set(slot + ".name", is.getItemMeta().getDisplayName());

			if (is.getItemMeta().hasLore())
				config.set(slot + ".lore", is.getItemMeta().getLore());

			if (is.getItemMeta().hasEnchants()) {
				Map<Enchantment, Integer> enchants = is.getEnchantments();
				List<String> enchantList = new ArrayList<String>();
				for (Enchantment e : is.getEnchantments().keySet()) {
					int level = enchants.get(e);
					enchantList.add(e.getName().toLowerCase() + ":" + level);
				}
				config.set(slot + ".enchants", enchantList);
			}
			continue;
		}

		config.set(path + "armor.helmet",
				inv.getHelmet() != null ? inv.getHelmet().getType().toString().toLowerCase() : "air");

		config.set(path + "armor.chestplate",
				inv.getChestplate() != null ? inv.getChestplate().getType().toString().toLowerCase() : "air");

		config.set(path + "armor.leggings",
				inv.getLeggings() != null ? inv.getLeggings().getType().toString().toLowerCase() : "air");

		config.set(path + "armor.boots",
				inv.getBoots() != null ? inv.getBoots().getType().toString().toLowerCase() : "air");

		config.saveConfig();

	}

	@SuppressWarnings("deprecation")
	public static void updateKit(Player p, String kitName) {
		SimpleConfig config = new SimpleConfig("config.yml");
		PlayerInventory inv = p.getInventory();

		if (config.getConfigurationSection("kits." + kitName) == null) {
			p.sendMessage(Common.colorize("&a" + kitName + " &7does not exist!"));
			return;
		}

		String path = "kits." + kitName + ".";

		for (int i = 0; i < 36; i++) {
			ItemStack is = inv.getItem(i);

			if (is == null || is.getType() == Material.AIR)
				continue;

			String slot = path + "items." + i;
			config.set(slot + ".type", is.getType().toString().toLowerCase());
			config.set(slot + ".amount", is.getAmount());

			if (!is.hasItemMeta())
				continue;

			if (is.getItemMeta().hasDisplayName())
				config.set(slot + ".name", is.getItemMeta().getDisplayName());

			if (is.getItemMeta().hasLore())
				config.set(slot + ".lore", is.getItemMeta().getLore());

			if (is.getItemMeta().hasEnchants()) {
				Map<Enchantment, Integer> enchants = is.getEnchantments();
				List<String> enchantList = new ArrayList<String>();
				for (Enchantment e : is.getEnchantments().keySet()) {
					int level = enchants.get(e);
					enchantList.add(e.getName().toLowerCase() + ":" + level);
				}
				config.set(slot + ".enchants", enchantList);
			}
			continue;
		}

		config.set(path + "armor.helmet",
				inv.getHelmet() != null ? inv.getHelmet().getType().toString().toLowerCase() : "air");

		config.set(path + "armor.chestplate",
				inv.getChestplate() != null ? inv.getChestplate().getType().toString().toLowerCase() : "air");

		config.set(path + "armor.leggings",
				inv.getLeggings() != null ? inv.getLeggings().getType().toString().toLowerCase() : "air");

		config.set(path + "armor.boots",
				inv.getBoots() != null ? inv.getBoots().getType().toString().toLowerCase() : "air");

		config.saveConfig();

	}

	@SuppressWarnings("deprecation")
	public static void giveKit(Player p, String kitName) {
		SimpleConfig config = new SimpleConfig("config.yml");
		if (config.getConfigurationSection("kits." + kitName) == null) {
			p.sendMessage(kitName + " does not exist!");
			return;
		}

		p.getInventory().clear();
		String path = "kits." + kitName + ".";
		ConfigurationSection s = config.getConfigurationSection(path + "items");
		for (String str : s.getKeys(false)) {
			int slot = Integer.parseInt(str);
			if (0 > slot && slot > 36)
				return;

			String string = path + "items." + slot + ".";
			String type = config.getString(string + "type");
			String name = config.getString(string + "name");
			List<String> lore = config.getStringList(string + "lore");
			List<String> enchants = config.getStringList(string + "enchants");
			int amount = config.getInt(string + "amount");

			ItemStack is = new ItemStack(Material.matchMaterial(type.toUpperCase()), amount);
			ItemMeta im = is.getItemMeta();

			if (im == null)
				continue;

			if (name != null)
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

			if (lore != null)
				im.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', lore.toString())));

			if (enchants != null) {
				for (String s1 : enchants) {
					String[] indiEnchants = s1.split(":");
					im.addEnchant(Enchantment.getByName(indiEnchants[0].toUpperCase()),
							Integer.parseInt(indiEnchants[1]), true);
				}
			}

			is.setItemMeta(im);
			p.getInventory().setItem(slot, is);
		}

		String helmet = config.getString(path + "armor.helmet").toUpperCase();
		String chestplate = config.getString(path + "armor.chestplate").toUpperCase();
		String leggings = config.getString(path + "armor.leggings").toUpperCase();
		String boots = config.getString(path + "armor.boots").toUpperCase();

		p.getInventory().setHelmet(new ItemStack(helmet != null ? Material.matchMaterial(helmet) : Material.AIR));
		p.getInventory()
				.setChestplate(new ItemStack(chestplate != null ? Material.matchMaterial(chestplate) : Material.AIR));
		p.getInventory().setLeggings(new ItemStack(leggings != null ? Material.matchMaterial(leggings) : Material.AIR));
		p.getInventory().setBoots(new ItemStack(boots != null ? Material.matchMaterial(boots) : Material.AIR));

		p.updateInventory();
	}

	public Core getPlugin() {
		return plugin;
	}
}
