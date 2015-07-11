package me.mani.deathnote.listener;

import me.mani.deathnote.DeathNote;
import me.mani.deathnote.DeathNoteListener;
import me.mani.deathnote.DeathNotePlayer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener extends DeathNoteListener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent ev) {
			
		if (ev.getEntity() instanceof Player && ev.getDamager() instanceof Player) {
			Player player = (Player) ev.getEntity();
			DeathNotePlayer deathNotePlayer = DeathNotePlayer.getDeathNotePlayer(player);
			Player damager = (Player) ev.getDamager();
			DeathNotePlayer deathNoteDamager = DeathNotePlayer.getDeathNotePlayer(damager);
			if (damager.getItemInHand().getType() == Material.IRON_SWORD && !deathNoteDamager.hasSoulEffect() && !deathNotePlayer.hasSoulEffect() && deathNotePlayer.isIngame()) {
				ev.setDamage(2.0);
				damager.getInventory().setItem(1, DeathNote.getInstance().getGameManager().itemManager.getBloodPotion(player));
				deathNotePlayer.addSoulEffect((int) (10 - player.getHealth() * 100));
			}
			else
				ev.setCancelled(true);
		}
		else
			ev.setCancelled(true);
		
	}
	
}
