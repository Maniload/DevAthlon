package me.mani.deathnote.manager;

import java.util.ArrayList;
import java.util.List;

import me.mani.deathnote.DeathNote;
import me.mani.deathnote.DeathNotePlayer;
import me.mani.deathnote.util.Effects;
import me.mani.deathnote.util.Messenger;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class DeathManager {
	
	public static void handleDeath(Player player, boolean deathNoted, Player killer) {
		
		DeathNotePlayer deathNotePlayer = DeathNotePlayer.getDeathNotePlayer(player);
		DeathNotePlayer deathNoteKiller = DeathNotePlayer.getDeathNotePlayer(killer);
		
		if (deathNotePlayer.isIngame()) {
			if (deathNoted) {
				Messenger.sendAll("§c" + player.getName() + " §7wurde von §c" + killer.getName() + "§7's Death Note getötet.");
				Messenger.send(killer, "+ 15 Sündenpunkte");
				Effects.play(player, Sound.ORB_PICKUP);
				deathNoteKiller.addSinPoints(15);
			}
			else {
				Messenger.sendAll("§c" + player.getName() + " §7wurde von §c" + killer.getName() + " §7getötet.");
				Messenger.send(killer, "+ 5 Sündenpunkte");
				Effects.play(player, Sound.ORB_PICKUP);
				deathNoteKiller.addSinPoints(5);
			}
			deathNotePlayer.setIngame(false);
			
			List<Player> playersLeft = new ArrayList<>();
			for (Player onlinePlayer : Bukkit.getOnlinePlayers())
				if (DeathNotePlayer.getDeathNotePlayer(onlinePlayer).isIngame())
					playersLeft.add(onlinePlayer);
			
			if (playersLeft.size() == 1) {
				Messenger.sendAll("§c" + playersLeft.get(0).getName() + " §7hat das Spiel gewonnen.");
				Messenger.send(killer, "+ 10 Sündenpunkte");
				deathNoteKiller.addSinPoints(10);
				DeathNote.getInstance().getGameManager().finishGame();
			}
		}
	}

}
