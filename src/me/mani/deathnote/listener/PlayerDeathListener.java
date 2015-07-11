package me.mani.deathnote.listener;

import me.mani.deathnote.DeathNoteListener;
import me.mani.deathnote.manager.DeathManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener extends DeathNoteListener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent ev) {
		
		ev.setKeepInventory(true);
		if (ev.getEntity().getLastDamageCause().getCause() == DamageCause.ENTITY_ATTACK)
			DeathManager.handleDeath(ev.getEntity(), false, ev.getEntity().getKiller());
		
	}
	
}
