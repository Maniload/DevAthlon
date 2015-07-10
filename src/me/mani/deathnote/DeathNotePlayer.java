package me.mani.deathnote;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class DeathNotePlayer {
	
	private static Map<Player, DeathNotePlayer> deathNotePlayers = new HashMap<>();
	private Player player;	
	private boolean ingame;
	
	private DeathNotePlayer(Player player) {
		this.player = player;
	}
	
	public boolean isIngame() {
		return ingame;
	}
	
	public void setIngame(boolean ingame) {
		this.ingame = ingame;
	}
	
	public static DeathNotePlayer getDeathNotePlayer(Player player) {
		if (!deathNotePlayers.containsKey(player))
			deathNotePlayers.put(player, new DeathNotePlayer(player));
		return deathNotePlayers.get(player);
	}

}
