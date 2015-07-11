package me.mani.deathnote.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import me.mani.deathnote.DeathNotePlayer;
import me.mani.deathnote.GameState;
import me.mani.deathnote.util.Effects;
import me.mani.deathnote.util.Messenger;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GameManager {
	
	public LocationManager locationManager;
	public ItemManager itemManager;
	public InventoryManager inventoryManager;
	public ChestManager chestManager;
	public AltarManager altarManager;
	
	public GameManager(SetupManager setupManager) {
		locationManager = setupManager.locationManager;
		itemManager = setupManager.itemManager;
		inventoryManager = setupManager.inventoryManager;
		chestManager = setupManager.chestManager;
		altarManager = setupManager.altarManager;
		GameState.setGameState(GameState.NONE);
	}
	
	public void startWarmUp() {
		GameState.setGameState(GameState.WARM_UP);
		Messenger.sendAll("Das Spiel beginnt in Kürze.");
		Iterator<Location> it = locationManager.getSpawnLocations().iterator();
		for (Player player : Bukkit.getOnlinePlayers()) {
			DeathNotePlayer deathNotePlayer = DeathNotePlayer.getDeathNotePlayer(player);
			deathNotePlayer.setIngame(true);
			player.teleport(it.next());
			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 160, 0));
			
			player.setGameMode(GameMode.ADVENTURE);
			player.setMaxHealth(6.0);
			player.setHealthScale(6.0);
			player.setHealthScaled(true);
			player.setHealth(6.0);
			
			inventoryManager.giveIngameInventory(player);
			Effects.play(player, Sound.ITEM_PICKUP);
		}
		CountdownManager.createCountdown((ev) -> {
			
			if (ev.getCurrentNumber() == 10 || ev.getCurrentNumber() <= 5) {
				ev.setMessage("Das Spiel startet in " + ev.getCurrentNumber() + " Sekunde" + (ev.getCurrentNumber() == 1 ? "" : 'n') + ".");
				ev.setSound(Sound.BLAZE_HIT);
			}
			
		}, () -> startGame(), 10, 0, 20L);
	}
	
	private void startGame() {
		GameState.setGameState(GameState.INGAME);
		Effects.playAll(Sound.BLAZE_BREATH);
		for (Player player : Bukkit.getOnlinePlayers()) {
			DeathNotePlayer deathNotePlayer = DeathNotePlayer.getDeathNotePlayer(player);
			deathNotePlayer.addSoulEffect(200);
		}
		altarManager.startAltarSwappingTask();
	}

	public void finishGame() {
		GameState.setGameState(GameState.NONE);
		List<DeathNotePlayer> playersLeft = new ArrayList<>();
		for (Player onlinePlayer : Bukkit.getOnlinePlayers())
			playersLeft.add(DeathNotePlayer.getDeathNotePlayer(onlinePlayer));
		Collections.sort(playersLeft);
		for (DeathNotePlayer deathNotePlayer : playersLeft)
			Messenger.sendAll("#1 §c" + deathNotePlayer.getPlayer().getName() + " §7- " + deathNotePlayer.getSinPoints() + " Sündenpunkte");
	}

}
