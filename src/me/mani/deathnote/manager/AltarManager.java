package me.mani.deathnote.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;






import me.mani.deathnote.DeathNote;
import me.mani.deathnote.map.Altar;



import me.mani.deathnote.util.Effects;

import me.mani.deathnote.util.Messenger;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.scheduler.BukkitTask;


public class AltarManager {
	
	private BukkitTask altarSwappingTask;
	
	@SuppressWarnings("deprecation")
	public void startAltarSwappingTask() {
		altarSwappingTask = Bukkit.getScheduler().runTaskTimer(DeathNote.getInstance(), () -> {
			
			for (Altar altar : Altar.getAltars()) {
				altar.setActiv(false);
				altar.getLocation().getBlock().getRelative(BlockFace.DOWN).setData((byte) 15);
			}
			
			List<Altar> altars = new ArrayList<>(Altar.getAltars());
			Collections.shuffle(altars);
			
			Altar altar = altars.get(0);
			altar.setActiv(true);
			altar.getLocation().getBlock().getRelative(BlockFace.DOWN).setData((byte) 10);
			
			Effects.playAll(Sound.PORTAL_TRAVEL);
			Messenger.sendAll("Die aktiven Altare wurden vertauscht.");
			
		}, 1200, 1200);
	}
	
	public void cancelAltarSwappingTask() {
		if (altarSwappingTask != null)
			altarSwappingTask.cancel();
	}
	
}
