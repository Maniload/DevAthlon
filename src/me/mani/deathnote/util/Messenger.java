package me.mani.deathnote.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Messenger {

	private static String prefix;
	
	public static void send(Player player, String message) {
		send(player, message, true);
	}
	
	public static void send(Player player, String message, boolean prefix) {
		player.sendMessage((prefix ? Messenger.prefix : "") + message);
	}
	
	public static void sendAll(String message) {
		sendAll(message, true);
	}
	
	public static void sendAll(String message, boolean prefix) {
		Bukkit.broadcastMessage((prefix ? Messenger.prefix : "") + message);
	}
	
	public static void setPrefix(String prefix) {
		Messenger.prefix = prefix;
	}
	
}
