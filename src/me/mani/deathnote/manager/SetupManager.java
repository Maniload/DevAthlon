package me.mani.deathnote.manager;

import me.mani.deathnote.command.TutorialCommand;
import me.mani.deathnote.listener.PlayerJoinListener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class SetupManager {

	private FileConfiguration config;
	public LocationManager locationManager;
	
	public SetupManager(FileConfiguration config) {
		this.config = config;
		setup();
	}
	
	private void setup() {
		setupLocations();
		setupListener();
		setupCommands();
	}
	
	private void setupLocations() {
		World defaultWorld = Bukkit.getWorld("world");
		locationManager = new LocationManager((Location) config.get("spawnLocation", defaultWorld.getSpawnLocation()));
	}
	
	private void setupListener() {
		new PlayerJoinListener();
	}
	
	private void setupCommands() {
		new TutorialCommand();
	}
	
}
