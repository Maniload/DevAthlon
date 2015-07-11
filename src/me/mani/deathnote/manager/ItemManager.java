package me.mani.deathnote.manager;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import me.mani.deathnote.util.ItemUtil;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionType;

public class ItemManager {
	
	private Map<Material, ItemStack> items;
	
	public ItemManager() {
		items = new EnumMap<>(Material.class);
		prepareItemStacks();
	}
	
	private void prepareItemStacks() {
		items.put(Material.IRON_SWORD, ItemUtil.createItem(new ItemStack(Material.IRON_SWORD), "§7Schwert", "§eSchlage einen Gegner damit,", "§eum an sein Blut zu gelangen."));
		items.put(Material.GLASS_BOTTLE, ItemUtil.createItem(new ItemStack(Material.GLASS_BOTTLE), "§7leeres Glas", "§eIn diesem Glas wird,", "§eBlut eines Gegners gelagert."));
		items.put(Material.PAPER, ItemUtil.createItem(new ItemStack(Material.PAPER), "§7Death Note"));
		items.put(Material.COMPASS, ItemUtil.createItem(new ItemStack(Material.COMPASS), "§7Altarfinder"));
		items.put(Material.INK_SACK, ItemUtil.createItem(new ItemStack(Material.INK_SACK, 1, (short) 1), "§cHerzcontainer"));
		items.put(Material.NAME_TAG, ItemUtil.createItem(new ItemStack(Material.NAME_TAG), "§c???§7's Death Note"));
		items.put(Material.POTION, ItemUtil.createItem(new ItemStack(Material.POTION), "", PotionType.INSTANT_HEAL, 1, false, false));
	}
	
	public ItemStack getItemStack(Material material) {
		return items.get(material);
	}
	
	public ItemStack getItemStack(Material material, int amount) {
		ItemStack itemStack = items.get(material);
		itemStack.setAmount(amount);
		return itemStack;
	}

	public ItemStack getBloodPotion(Player player) {
		ItemStack itemStack = items.get(Material.POTION);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("§c" + player.getName() + "§7's Blut");
		itemMeta.setLore(Arrays.asList("§c" + player.getName()));
		itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

}
