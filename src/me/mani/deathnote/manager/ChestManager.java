package me.mani.deathnote.manager;

import java.util.HashMap;
import java.util.Map;

import me.mani.deathnote.util.ItemUtil;
import me.mani.deathnote.util.Messenger;
import me.mani.deathnote.util.RandomUtil;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChestManager {

	private Map<Location, Inventory> chests;
	
	private ItemStack deathNoteItemStack = ItemUtil.createItem(new ItemStack(Material.PAPER), "§7Death Note");
	private ItemStack altarFinderItemStack = ItemUtil.createItem(new ItemStack(Material.COMPASS), "§7Altarfinder");
	private ItemStack lifeItemStack = ItemUtil.createItem(new ItemStack(Material.INK_SACK, 1, (short) 1), "§cLeben");
	
	public ChestManager() {
		chests = new HashMap<>();
	}
	
	public void openChest(Player player, Location location) {
		if (!chests.containsKey(location)) {
			Inventory inventory = Bukkit.createInventory(null, 9 * 5, "Equipment");
			int randomInteger = RandomUtil.getRandomInteger(0, 100);
			if (randomInteger < 8)
				inventory.setItem(RandomUtil.getRandomInteger(0, inventory.getSize() - 1), lifeItemStack);
			else if (randomInteger < 18)
				inventory.setItem(RandomUtil.getRandomInteger(0, inventory.getSize() - 1), altarFinderItemStack);
			else if (randomInteger < 58)
				inventory.setItem(RandomUtil.getRandomInteger(0, inventory.getSize() - 1), deathNoteItemStack);
			chests.put(location, inventory);
			Messenger.sendAll(randomInteger + "");
		}
		player.openInventory(chests.get(location));
	}
	
}
