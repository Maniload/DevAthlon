package me.mani.deathnote.listener;

import me.mani.deathnote.DeathNoteListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamageListener extends DeathNoteListener {
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent ev) {
		
		if (ev.getCause() != DamageCause.ENTITY_ATTACK)
			ev.setCancelled(true);
		
	}

}
