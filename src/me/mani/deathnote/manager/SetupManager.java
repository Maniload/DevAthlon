package me.mani.deathnote.manager;

import me.mani.deathnote.command.TutorialCommand;
import me.mani.deathnote.listener.PlayerJoinListener;

public class SetupManager {

	public SetupManager() {
		setup();
	}
	
	private void setup() {
		setupListener();
		setupCommands();
	}
	
	private void setupListener() {
		new PlayerJoinListener();
	}
	
	private void setupCommands() {
		new TutorialCommand();
	}
	
}
