package me.mani.deathnote;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DeathNotePlayer implements Comparable<DeathNotePlayer> {
	
	private static Map<Player, DeathNotePlayer> deathNotePlayers = new HashMap<>();
	private Player player;	
	private boolean ingame;
	private boolean soulEffect;
	private int sinPoints;
	
	private DeathNotePlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean isIngame() {
		return ingame;
	}
	
	public void setIngame(boolean ingame) {
		this.ingame = ingame;
	}
	
	public void addSoulEffect(int ticks) {
		soulEffect = true;
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, ticks, 0, false, false));
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, ticks, 3, false, false));
		Bukkit.getScheduler().runTaskLater(DeathNote.getInstance(), () -> soulEffect = false, ticks);
	}
	
	public boolean hasSoulEffect() {
		return soulEffect;
	}
	
	public int getSinPoints() {
		return sinPoints;
	}
	
	public void addSinPoints(int sinPoints) {
		this.sinPoints += sinPoints;
	}
	
	public static DeathNotePlayer getDeathNotePlayer(Player player) {
		if (!deathNotePlayers.containsKey(player))
			deathNotePlayers.put(player, new DeathNotePlayer(player));
		return deathNotePlayers.get(player);
	}

	@Override
	public int compareTo(DeathNotePlayer o) {
		return o.getSinPoints() - sinPoints;
	}

}
