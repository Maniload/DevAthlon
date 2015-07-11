package me.mani.deathnote.manager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

	private ItemManager itemManager;
	private ItemStack[] ingameInventoryItems;
	
	public InventoryManager(ItemManager itemManager) {
		this.itemManager = itemManager;
		prepareIngameInventory();
	}
	
	private void prepareIngameInventory() {
		ingameInventoryItems = new ItemStack[36];
		ingameInventoryItems[0] = itemManager.getItemStack(Material.IRON_SWORD);
		ingameInventoryItems[1] = itemManager.getItemStack(Material.GLASS_BOTTLE);
	}
	
	public void giveIngameInventory(Player player) {
		player.getInventory().setContents(ingameInventoryItems);
	}
	
}
