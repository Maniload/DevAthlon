package me.mani.deathnote.listener;

import me.mani.deathnote.DeathNoteListener;
import me.mani.deathnote.GameState;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener extends DeathNoteListener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent ev) {
		
		if (GameState.getGameState() == GameState.WARM_UP)
			ev.setCancelled(true);
		
	}
	
}
