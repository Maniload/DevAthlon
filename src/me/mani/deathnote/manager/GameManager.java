package me.mani.deathnote.manager;

import me.mani.deathnote.DeathNotePlayer;
import me.mani.deathnote.util.Messenger;

import org.bukkit.Bukkit;

public class GameManager {
	
	public LocationManager locationManager;
	
	public GameManager(SetupManager setupManager) {
		locationManager = setupManager.locationManager;
	}
	
	public void startWarmUp() {
		Messenger.sendAll("Das Spiel beginnt in Kürze.");
		Bukkit.getOnlinePlayers().forEach((player) -> {
			DeathNotePlayer deathNotePlayer = DeathNotePlayer.getDeathNotePlayer(player);
			deathNotePlayer.setIngame(true);
		});
		CountdownManager.createCountdown((ev) -> {
			
		}, () -> startGame(), 10, 0, 20L);
	}
	
	public void startGame() {
		
	}

}
