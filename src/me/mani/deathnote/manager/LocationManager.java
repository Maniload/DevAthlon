package me.mani.deathnote.manager;

import org.bukkit.Location;

public class LocationManager {
	
	private final Location spawnLocation;
	
	public LocationManager(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
	}
	
	public Location getSpawnLocation() {
		return spawnLocation;
	}

}
