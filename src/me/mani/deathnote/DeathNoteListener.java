package me.mani.deathnote;

import org.bukkit.event.Listener;

public class DeathNoteListener implements Listener {
	
	public DeathNoteListener() {
		DeathNote.getInstance().getServer().getPluginManager().registerEvents(this, DeathNote.getInstance());
	}

}
