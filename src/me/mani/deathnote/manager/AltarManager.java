package me.mani.deathnote.manager;

import me.mani.deathnote.DeathNote;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class AltarManager {
	
	private BukkitTask altarSwappingTask;
	
	public void startAltarSwappingTask() {
		altarSwappingTask = Bukkit.getScheduler().runTaskTimer(DeathNote.getInstance(), () -> {
			
			
			
		}, 1200, 1200); 
	}
	
}
