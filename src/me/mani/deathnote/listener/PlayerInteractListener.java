package me.mani.deathnote.listener;

import me.mani.deathnote.DeathNote;
import me.mani.deathnote.DeathNoteListener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener extends DeathNoteListener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent ev) {
		
		Player player = ev.getPlayer();
		Block block = ev.getClickedBlock();
		Action action = ev.getAction();
		
		if (action == Action.RIGHT_CLICK_BLOCK) {
			if (block.getType() == Material.ENDER_CHEST)
				DeathNote.getInstance().getGameManager().chestManager.openChest(player, block.getLocation());
			if (player.getGameMode() != GameMode.CREATIVE)
				ev.setCancelled(true);
		}
		
	}

}