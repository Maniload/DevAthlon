package me.mani.deathnote.listener;

import java.util.ArrayList;

import me.mani.deathnote.DeathNote;
import me.mani.deathnote.DeathNoteListener;
import me.mani.deathnote.DeathNotePlayer;
import me.mani.deathnote.map.Altar;
import me.mani.deathnote.util.Effects;
import me.mani.deathnote.util.Messenger;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
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
						configuration.set("altarLocations", new ArrayList<>(Altar.getLocations()));
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
					configuration.set("spawnLocation", player.getLocation());
					DeathNote.getInstance().saveConfig();
					Messenger.send(player, "Neuer Spawn wurde gespeichert."); 
				}
			}
			else if (itemStack != null && itemStack.getType() == Material.EMPTY_MAP) {
				if (action == Action.RIGHT_CLICK_BLOCK) {
					if (block.getType() == Material.ENDER_PORTAL_FRAME && Altar.getAltar(block.getLocation()) != null) {
						if (Altar.getAltar(block.getLocation()).isActiv()) {
							DeathNotePlayer deathNotePlayer = DeathNotePlayer.getDeathNotePlayer(player);
								if (deathNotePlayer.isIngame() && !deathNotePlayer.hasSoulEffect()) {
									String rawOwnerName = itemStack.getItemMeta().getLore().get(0);
									Player otherPlayer = Bukkit.getPlayer(rawOwnerName.substring(2, rawOwnerName.length()));
									if (otherPlayer != null) {
										otherPlayer.setHealth(0.0);
										otherPlayer.getWorld().strikeLightningEffect(otherPlayer.getLocation());
									}
									player.setItemInHand(null);
									Bukkit.getOnlinePlayers().forEach((onlinePlayer) -> {
										onlinePlayer.spigot().playEffect(
											block.getLocation().add(0, 2, 0), 
											Effect.FLAME, 0, 0, 0.5f, 2f, 0.5f, 0, 10, 100
										);
									});
								}
								else if (deathNotePlayer.isIngame())
									Messenger.send(player, "Du darfst nicht im Seelenmodus eine Death Note einlösen.");
							}
						else
							Effects.play(player, Sound.ANVIL_LAND);
					}
				}
				player.setItemInHand(itemStack);
			}
			else if (itemStack != null && itemStack.getType() == Material.INK_SACK) {
				player.setMaxHealth(player.getMaxHealth() + 2);
				player.setHealthScale(player.getMaxHealth());
				player.setHealth(player.getMaxHealth());
				player.spigot().playEffect(player.getLocation(), Effect.HEART, 0, 0, 3, 3, 3, 0, 50, 100);
				Effects.play(player, Sound.SPLASH);
				Messenger.send(player, "+ 1 Herzcontainer");
				ItemStack handItemStack = player.getItemInHand();
				handItemStack.setAmount(handItemStack.getAmount() - 1);
				if (handItemStack.getAmount() <= 0)
					handItemStack = null;
				player.setItemInHand(handItemStack);				
			}
			if (action == Action.RIGHT_CLICK_BLOCK && block.getType() == Material.ENDER_CHEST) {
				DeathNote.getInstance().getGameManager().chestManager.openChest(player, block.getLocation());	
				ev.setCancelled(true);	
			}
		}	
	}

}