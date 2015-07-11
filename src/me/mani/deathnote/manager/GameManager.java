package me.mani.deathnote.manager;

import me.mani.deathnote.DeathNotePlayer;
import me.mani.deathnote.util.Effects;
import me.mani.deathnote.util.Messenger;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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
	}
	
	public void startWarmUp() {
		Messenger.sendAll("Das Spiel beginnt in Kürze.");
		int playerCount = Bukkit.getOnlinePlayers().size();
		int i = 0;
		for (Player player : Bukkit.getOnlinePlayers()) {
			DeathNotePlayer deathNotePlayer = DeathNotePlayer.getDeathNotePlayer(player);
			deathNotePlayer.setIngame(true);
			player.teleport(locationManager.getSpawnLocation().clone().add(Math.cos(Math.toRadians(360 / playerCount * i)) * 5, 0, Math.sin(Math.toRadians(360 / playerCount * i++))));
			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 160, 0));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 255));
			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 255));
			
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
		Effects.playAll(Sound.BLAZE_BREATH);
		for (Player player : Bukkit.getOnlinePlayers()) {
			DeathNotePlayer deathNotePlayer = DeathNotePlayer.getDeathNotePlayer(player);
			deathNotePlayer.addSoulEffect(200);
		}
		altarManager.startAltarSwappingTask();
	}

}
