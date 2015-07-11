package me.mani.deathnote.listener;

import me.mani.deathnote.DeathNoteListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener extends DeathNoteListener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent ev) {
		
		ItemStack itemStack = ev.getCurrentItem();
		
		switch (itemStack.getType()) {
		case IRON_SWORD:
		case GLASS_BOTTLE:
			ev.setCancelled(true);
		default:
			break;
		}
		
	}
	
}
