package me.mani.deathnote.manager;

import java.util.HashMap;
import java.util.Map;

import me.mani.deathnote.util.RandomUtil;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ChestManager {
	
	private ItemManager itemManager;
	private Map<Location, Inventory> chests;
	
	public ChestManager(ItemManager itemManager) {
		this.itemManager = itemManager;
		chests = new HashMap<>();
	}
	
	public void openChest(Player player, Location location) {
		if (!chests.containsKey(location)) {
			Inventory inventory = Bukkit.createInventory(null, 9 * 5, "Equipment");
			int randomInteger = RandomUtil.getRandomInteger(0, 100);
			if (randomInteger == 0)
				inventory.setItem(RandomUtil.getRandomInteger(0, inventory.getSize() - 1), itemManager.getItemStack(Material.INK_SACK, 3));
			if (randomInteger < 8)
				inventory.setItem(RandomUtil.getRandomInteger(0, inventory.getSize() - 1), itemManager.getItemStack(Material.INK_SACK));
			else if (randomInteger < 18)
				inventory.setItem(RandomUtil.getRandomInteger(0, inventory.getSize() - 1), itemManager.getItemStack(Material.COMPASS));
			else if (randomInteger < 58)
				inventory.setItem(RandomUtil.getRandomInteger(0, inventory.getSize() - 1), itemManager.getItemStack(Material.PAPER));
			chests.put(location, inventory);
		}
		player.openInventory(chests.get(location));
	}
	
	public void resetChests() {
		chests.clear();
	}
	
}
