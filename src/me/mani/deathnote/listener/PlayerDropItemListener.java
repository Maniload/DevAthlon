package me.mani.deathnote.listener;

import me.mani.deathnote.DeathNoteListener;
import me.mani.deathnote.DeathNotePlayer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener extends DeathNoteListener {

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent ev) {
		
		if (DeathNotePlayer.getDeathNotePlayer(ev.getPlayer()).isIngame())
			ev.setCancelled(true);
		
	}
	
}
