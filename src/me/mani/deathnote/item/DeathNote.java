package me.mani.deathnote.item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DeathNote extends ItemStack {
	
	private Player noter;
	private Player player;
	
	public DeathNote(Player noter, Player player) {
		this.noter = noter;
		this.player = player;
		ItemMeta itemMeta = getItemMeta();
		itemMeta.setDisplayName("§c" + player.getName() + "§7's Death Note");
		setItemMeta(itemMeta);
	}
	
	public Player getNoter() {
		return noter;
	}
	
	public Player getPlayer() {
		return player;
	}

}
