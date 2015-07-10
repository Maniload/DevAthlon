package me.mani.deathnote.manager;

import me.mani.deathnote.command.StartCommand;
import me.mani.deathnote.command.TutorialCommand;
import me.mani.deathnote.listener.PlayerInteractListener;
import me.mani.deathnote.listener.PlayerJoinListener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class SetupManager {

	private FileConfiguration config;
	public LocationManager locationManager;
	public InventoryManager inventoryManager;
	
	public SetupManager(FileConfiguration config) {
		this.config = config;
		setup();
	}
	
	private void setup() {
		setupLocations();
		setupInventories();
		setupListener();
		setupCommands();
	}
	
	private void setupLocations() {
		World defaultWorld = Bukkit.getWorld("world");
		locationManager = new LocationManager((Location) config.get("spawnLocation", defaultWorld.getSpawnLocation()));
	}
	
	private void setupInventories() {
		inventoryManager = new InventoryManager();
	}
	
	private void setupListener() {
		new PlayerJoinListener();
		new PlayerInteractListener();
	}
	
	private void setupCommands() {
		new TutorialCommand();
		new StartCommand();
	}
	
}
