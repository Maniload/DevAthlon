package me.mani.deathnote;

import me.mani.deathnote.manager.GameManager;
import me.mani.deathnote.manager.SetupManager;
import me.mani.deathnote.util.Messenger;

import org.bukkit.plugin.java.JavaPlugin;

public class DeathNote extends JavaPlugin {
	
	private static DeathNote instance;
	private GameManager gameManager;
	
	@Override
	public void onEnable() {
		instance = this;
		Messenger.setPrefix("§8[§cDeathNote§8] §7");
		
		Messenger.sendAll("DeathNote - by Overload & Laubfrosch7");
		
		gameManager = new GameManager(new SetupManager(getConfig()));
	}
	
	public GameManager getGameManager() {
		return gameManager;
	}
	
	public static DeathNote getInstance() {
		return instance;
	}

}
