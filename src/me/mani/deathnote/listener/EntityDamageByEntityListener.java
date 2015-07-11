package me.mani.deathnote.listener;

import me.mani.deathnote.DeathNote;
import me.mani.deathnote.DeathNoteListener;
import me.mani.deathnote.DeathNotePlayer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
				deathNotePlayer.addSoulEffect(200);
			}
			else if (damager.getItemInHand().getType() == Material.IRON_SWORD && !deathNotePlayer.hasSoulEffect() && deathNotePlayer.isIngame()) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 1));
				ev.setCancelled(true);
			}
			else
				ev.setCancelled(true);
		}
		else
			ev.setCancelled(true);
		
	}
	
}
