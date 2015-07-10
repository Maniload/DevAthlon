package me.mani.deathnote.manager;

import me.mani.deathnote.DeathNotePlayer;
import me.mani.deathnote.util.Effects;
import me.mani.deathnote.util.Messenger;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GameManager {
	
	public LocationManager locationManager;
	public InventoryManager inventoryManager;
	
	public GameManager(SetupManager setupManager) {
		locationManager = setupManager.locationManager;
		inventoryManager = setupManager.inventoryManager;
	}
	
	public void startWarmUp() {
		Messenger.sendAll("Das Spiel beginnt in Kürze.");
		int playerCount = Bukkit.getOnlinePlayers().size();
		int i = 0;
		for (Player player : Bukkit.getOnlinePlayers()) {
			DeathNotePlayer deathNotePlayer = DeathNotePlayer.getDeathNotePlayer(player);
			deathNotePlayer.setIngame(true);
			player.teleport(locationManager.getSpawnLocation().clone().add(Math.sin(1 / playerCount * i) * 5, 0, Math.cos(1 / playerCount * i++)));
			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 160, 0));
		}
		CountdownManager.createCountdown((ev) -> {
			
			ev.setMessage("Das Spiel startet in " + ev.getCurrentNumber() + " Sekunde" + (ev.getCurrentNumber() == 1 ? null : 'n') + ".");
			ev.setSound(Sound.BLAZE_HIT);
			
			if (ev.getCurrentNumber() == 5) {
				Bukkit.getOnlinePlayers().forEach((player) -> inventoryManager.giveIngameInventory(player));
				Effects.playAll(Sound.ITEM_PICKUP);
			}
			
		}, () -> startGame(), 10, 0, 20L);
	}
	
	public void startGame() {
		
	}

}
