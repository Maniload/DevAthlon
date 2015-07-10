package me.mani.deathnote;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DeathNotePlayer {
	
	private static Map<Player, DeathNotePlayer> deathNotePlayers = new HashMap<>();
	private Player player;	
	private boolean ingame;
	private boolean soulEffect;
	
	private DeathNotePlayer(Player player) {
		this.player = player;
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
	}
	
	public boolean hasSoulEffect() {
		return soulEffect;
	}
	
	public static DeathNotePlayer getDeathNotePlayer(Player player) {
		if (!deathNotePlayers.containsKey(player))
			deathNotePlayers.put(player, new DeathNotePlayer(player));
		return deathNotePlayers.get(player);
	}

}
