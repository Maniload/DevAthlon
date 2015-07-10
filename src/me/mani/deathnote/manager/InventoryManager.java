package me.mani.deathnote.manager;

import me.mani.deathnote.util.ItemUtil;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

	private ItemStack[] ingameInventoryItems;
	
	public InventoryManager() {
		prepareIngameInventory();
	}
	
	private void prepareIngameInventory() {
		ingameInventoryItems = new ItemStack[36];
		ingameInventoryItems[0] = ItemUtil.createItem(new ItemStack(Material.IRON_SWORD), "�7Schwert", "�eSchlage einen Gegner damit,", "�eum an sein Blut zu gelangen.");
		ingameInventoryItems[1] = ItemUtil.createItem(new ItemStack(Material.GLASS_BOTTLE), "�7leeres Gef��", "�eIn diesem Gef�� wird,", "�eBlut eines Gegners gelagert.");
		for (int i = 9; i < ingameInventoryItems.length; i++)
			ingameInventoryItems[i] = ItemUtil.createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15), "");
	}
	
	public void giveIngameInventory(Player player) {
		player.getInventory().setContents(ingameInventoryItems);
	}
	
}
