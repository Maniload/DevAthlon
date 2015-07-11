package me.mani.deathnote.listener;

import me.mani.deathnote.DeathNoteListener;
import me.mani.deathnote.DeathNotePlayer;
import me.mani.deathnote.util.Messenger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener extends DeathNoteListener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent ev) {
		
		Player player = ev.getEntity();
		DeathNotePlayer deathNotePlayer = DeathNotePlayer.getDeathNotePlayer(player);
		Player killer = player.getKiller();
		
		if (deathNotePlayer.isIngame()) {
			ev.setKeepInventory(true);
			if (player.getLastDamageCause().getCause() == DamageCause.ENTITY_ATTACK) {
				ev.setDeathMessage("§c" + player + " §7wurde von §c" + killer.getName() + " §7getötet.");
				Messenger.send(killer, "+ 5 Sündenpunkte");
			}
			else if (player.getLastDamageCause().getCause() == DamageCause.CUSTOM) {
				
			}
			
		}
		
	}
	
}
