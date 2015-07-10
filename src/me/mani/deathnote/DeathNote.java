package me.mani.deathnote;

import me.mani.deathnote.manager.SetupManager;
import me.mani.deathnote.util.Messenger;

import org.bukkit.plugin.java.JavaPlugin;

public class DeathNote extends JavaPlugin {
	
	private static DeathNote instance;
	
	@Override
	public void onEnable() {
		instance = this;
		Messenger.setPrefix("§8[§cDeathNote§8] §7");
		
		Messenger.sendAll("DeathNote - by Overload & Laubfrosch7");
		
		SetupManager setupManager = new SetupManager();
	}
	
	public static DeathNote getInstance() {
		return instance;
	}

}
