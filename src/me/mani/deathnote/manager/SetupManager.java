package me.mani.deathnote.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import me.mani.deathnote.command.StartCommand;
import me.mani.deathnote.command.TutorialCommand;
import me.mani.deathnote.listener.CraftItemListener;
import me.mani.deathnote.listener.EntityDamageByEntityListener;
import me.mani.deathnote.listener.EntityDamageListener;
import me.mani.deathnote.listener.EntityRegainHealthListener;
import me.mani.deathnote.listener.PlayerDeathListener;
import me.mani.deathnote.listener.PlayerInteractListener;
import me.mani.deathnote.listener.PlayerJoinListener;
import me.mani.deathnote.listener.PlayerMoveListener;
import me.mani.deathnote.map.Altar;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ShapelessRecipe;

public class SetupManager {

	private FileConfiguration config;
	public LocationManager locationManager;
	public ItemManager itemManager;
	public InventoryManager inventoryManager;
	public ChestManager chestManager;
	public AltarManager altarManager;
	
	public SetupManager(FileConfiguration config) {
		this.config = config;
		setup();
	}
	
	private void setup() {
		setupLocations();
		setupManagers();
		setupCraftingRecipes();
		setupListener();
		setupCommands();
	}
	
	@SuppressWarnings("unchecked")
	private void setupLocations() {
		locationManager = new LocationManager((List<Location>) config.getList("spawnLocations", new ArrayList<>()));
		for (Location location : (List<Location>) config.getList("altarLocations", new ArrayList<>()))
			Altar.getAltar(location);
	}
	
	private void setupManagers() {
		itemManager = new ItemManager();
		inventoryManager = new InventoryManager(itemManager);
		chestManager = new ChestManager(itemManager);
		altarManager = new AltarManager();
	}
	
	@SuppressWarnings("deprecation")
	private void setupCraftingRecipes() {
		ShapelessRecipe deathNoteCraftingRecipe = new ShapelessRecipe(itemManager.getItemStack(Material.NAME_TAG));
		deathNoteCraftingRecipe.addIngredient(Material.PAPER).addIngredient(Material.POTION.getNewData((byte) 5));
		Bukkit.addRecipe(deathNoteCraftingRecipe);
	}
	
	private void setupListener() {
		new PlayerJoinListener();
		new PlayerInteractListener();
		new PlayerMoveListener();
		new CraftItemListener();
		new EntityDamageListener();
		new EntityDamageByEntityListener();
		new EntityRegainHealthListener();
		new PlayerDeathListener();
	}
	
	private void setupCommands() {
		new TutorialCommand();
		new StartCommand();
	}
	
}
