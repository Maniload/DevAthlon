package me.mani.deathnote.listener;

import java.util.ArrayList;

import me.mani.deathnote.DeathNote;
import me.mani.deathnote.DeathNoteListener;
import me.mani.deathnote.map.Altar;
import me.mani.deathnote.util.Messenger;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener extends DeathNoteListener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent ev) {
		
		Player player = ev.getPlayer();
		Block block = ev.getClickedBlock();
		ItemStack itemStack = ev.getItem();
		Action action = ev.getAction();
		
		if (action != Action.PHYSICAL) {
			if (itemStack != null && itemStack.getType() == Material.BLAZE_ROD) {
				if (action == Action.RIGHT_CLICK_BLOCK) {
					if (block.getType() == Material.ENDER_PORTAL_FRAME && block.getRelative(BlockFace.DOWN).getType() == Material.STAINED_GLASS) {
						Altar.getAltar(block.getLocation());
						FileConfiguration configuration = DeathNote.getInstance().getConfig();
						configuration.set("altarLocations", Altar.getLocations());
						DeathNote.getInstance().saveConfig();
						Messenger.send(player, "Alter hinzugefügt. Neue Anzahl: " + Altar.getAltars().size()); 
					}
					else
						Messenger.send(player, "Dies ist kein gültiger Altar.");
				}
				else if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
					FileConfiguration configuration = DeathNote.getInstance().getConfig();
					configuration.set("atlarLocations", new ArrayList<>());
					DeathNote.getInstance().saveConfig();
					Messenger.send(player, "Altare zurückgesetzt. Alle Altare wurden gelöscht."); 
				}
			}
			else if (itemStack != null && itemStack.getType() == Material.STICK) {
				if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
					FileConfiguration configuration = DeathNote.getInstance().getConfig();
					configuration.set("spawnLocations", player.getLocation());
					DeathNote.getInstance().saveConfig();
					Messenger.send(player, "Neuer Spawn wurde gespeichert."); 
				}
			}
		}
		else if (action == Action.RIGHT_CLICK_BLOCK) {
			if (block.getType() == Material.ENDER_CHEST)
				DeathNote.getInstance().getGameManager().chestManager.openChest(player, block.getLocation());
			if (player.getGameMode() != GameMode.CREATIVE)
				ev.setCancelled(true);
		}
		
	}

}