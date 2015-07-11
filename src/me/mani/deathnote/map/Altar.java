package me.mani.deathnote.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;

public class Altar {

	private static Map<Location, Altar> altars = new HashMap<>();
	
	private Location location;
	private boolean activ;
	
	private Altar(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public boolean isActiv() {
		return activ;
	}
	
	public void setActiv(boolean activ) {
		this.activ = activ;
	}
	
	public static Altar getAltar(Location location) {
		if (!altars.containsKey(location))
			altars.put(location, new Altar(location));
		return altars.get(location);
	}
	
	public static Collection<Altar> getAltars() {
		return altars.values();
	}
	
	public static Set<Location> getLocations() {
		return altars.keySet();
	}
	
}
