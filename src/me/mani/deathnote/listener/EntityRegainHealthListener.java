package me.mani.deathnote.listener;

import me.mani.deathnote.DeathNoteListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

public class EntityRegainHealthListener extends DeathNoteListener {

	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent ev) {
		
		if (ev.getRegainReason() != RegainReason.CUSTOM)
			ev.setCancelled(true);
		
	}
	
}
