package net.keylon.me.kits;

import java.util.ArrayList;
import java.util.List;

public class KitManager {
	
	private List<Kit> kits;
	
	public KitManager() {
		kits = new ArrayList<Kit>();
		
		kits.add(new DefaultAttacker());
		kits.add(new DefaultKiller());
		kits.add(new DefaultGuardian());
	}
	
	public List<Kit> getKits() {
		return kits;
	}
	
	public Kit getKit(String str) {
		for (Kit kit : kits) {
			if (str.equalsIgnoreCase(kit.name)) {
				return kit;
			}
		}
		return null;
	}
	
	
}
