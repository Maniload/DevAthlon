package me.mani.deathnote.command;

import org.bukkit.entity.Player;

import me.mani.deathnote.DeathNote;
import me.mani.deathnote.DeathNoteCommand;

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
