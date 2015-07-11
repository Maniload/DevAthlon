package me.mani.deathnote.command;

import me.mani.deathnote.DeathNote;
import me.mani.deathnote.DeathNoteCommand;

import org.bukkit.entity.Player;

public class StartCommand extends DeathNoteCommand {

	public StartCommand() {
		super("start");
	}

	@Override
	public String onCommand(Player player, String[] args) {
		
		DeathNote.getInstance().getGameManager().startWarmUp();
		
		return "Das Spiel wurde gestartet";
	}

}
