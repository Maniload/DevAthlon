package me.mani.deathnote.listener;

import me.mani.deathnote.DeathNoteListener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;



public class PlayerMoveListener extends DeathNoteListener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent ev) {
		
		Player player = ev.getPlayer();
		
//		Bukkit.getOnlinePlayers().forEach((onlinePlayer) -> {
//			if (!onlinePlayer.equals(player)) {
//				Location locationA = player.getLocation();
//				Location locationB = onlinePlayer.getLocation();
//				int points = (int) Math.round(locationA.distance(locationB));
//				if (points < 100) {
//					Vector vector = locationB.subtract(locationA).toVector();
//					for (int i = 0; i <= points; i++)
//						Effects.show(player, Effect.SMALL_SMOKE, locationA.add(vector.multiply(i / points)), 0, 10);
//				}	
//			}
//		});
		
	}
	
}
