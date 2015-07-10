package me.mani.deathnote.command;

import me.mani.deathnote.DeathNote;
import me.mani.deathnote.DeathNoteCommand;
import me.mani.deathnote.util.ItemUtil;
import me.mani.deathnote.util.PlayerRunnable;
import me.mani.deathnote.util.Title;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TutorialCommand extends DeathNoteCommand {

	private PlayerRunnable[] actions = new PlayerRunnable[] {
		(player) -> player.getInventory().clear(),
		(player) -> new Title("§cDeathNote", "§7Schnelles, spannendes Survivalspiel.", 5, 100, 5, false).send(player),
		(player) -> new Title("§cIdee §8[2/4]", "§7Idee ist es seine Gegner durch taktisches Blutabnehmen mit dem Messer", 5, 100, 5, false).send(player),
		(player) -> player.getInventory().setItem(4, ItemUtil.createItem(new ItemStack(Material.IRON_SWORD), "§7Messer")),
		(player) -> new Title("§cIdee §8[3/4]", "§7und Hilfe der 'Death Note' zu besiegen.", 5, 100, 5, false).send(player),
		(player) -> player.getInventory().setItem(4, ItemUtil.createItem(new ItemStack(Material.PAPER), "§7Death Note"))
	};
	private int[] delays = new int[] {
		0,
		120,
		120,
		120,
		0,
		120,
		0,
		120
	};
	
	public TutorialCommand() {
		super("tutorial");
	}

	@Override
	public String onCommand(Player player, String[] args) {
		
		runActions(0, player);
		
		return null;
	}
	
	private void runActions(int index, Player player) {
		if (actions.length > index)
			Bukkit.getScheduler().runTaskLater(DeathNote.getInstance(), () -> {
				actions[index].run(player);
				runActions(index + 1, player);
			}, delays[index]);
	}

}
