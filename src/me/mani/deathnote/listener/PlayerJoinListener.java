package me.mani.deathnote.listener;

import me.mani.deathnote.DeathNoteListener;
import me.mani.deathnote.DeathNotePlayer;
import me.mani.deathnote.util.Messenger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener extends DeathNoteListener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		
		Player player = ev.getPlayer();
		DeathNotePlayer.getDeathNotePlayer(player);
		ev.setJoinMessage("§c" + player.getName() + " §7ist dem Spiel beigetreten.");
		
		Messenger.send(player, "/start - Das Spiel starten.");
		Messenger.send(player, "/tutorial - Das (aufwendige) Tutorial anschauen.");
		
	}
	
}
