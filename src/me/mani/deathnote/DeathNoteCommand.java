package me.mani.deathnote;

import me.mani.deathnote.util.Messenger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class DeathNoteCommand implements CommandExecutor {

	public DeathNoteCommand(String label) {
		DeathNote.getInstance().getCommand(label).setExecutor(this);
	}
	
	@Override
	public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;
		String response = onCommand((Player) sender, args);
		if (response != null)
			Messenger.send((Player) sender, response);
		return true;
	}
	
	public abstract String onCommand(Player player, String[] args);

}
