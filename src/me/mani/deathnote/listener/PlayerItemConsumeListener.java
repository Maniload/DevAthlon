package me.mani.deathnote.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import me.mani.deathnote.DeathNoteListener;

public class PlayerItemConsumeListener extends DeathNoteListener {
	
	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent ev) {
		
		if (ev.getItem().getType() == Material.EMPTY_MAP)
			ev.setCancelled(true);
		
	}

}
