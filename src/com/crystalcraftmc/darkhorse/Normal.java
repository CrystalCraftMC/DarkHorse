package com.crystalcraftmc.darkhorse;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Variant;

public class Normal implements CommandExecutor
{
	Main plugin;
	public Normal(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		// Make the letter 'p' a variable for the command sender (or the player).
		Player p = (Player) sender;
		
		// If the player typed /horse1, then do the following...
    	if (cmd.getName().equalsIgnoreCase("horse1"))
    	{
    		if (p.hasPermission("darkhorse.normal"))
    		{
    			// ...create a variable to find the player's location...
        		Location location = p.getLocation();
        		
        		// ...then spawn a horse at the player's current location...
        		Horse horse = (Horse) location.getWorld().spawnEntity(location, EntityType.HORSE);
        		
        		// ...and change the type of horse to a normal horse.
        		horse.setVariant(Variant.HORSE);
        		
        		// Then, notify the player that the entity has been spawned.
        		p.sendMessage(ChatColor.GOLD + "A normal horse has been spawned.");
        		        		
        		// If this has happened, the function will return true. 
        		return true;
    		}
    	}
    	// If this hasn't happened, a value of false will be returned.
    	return false;
	}
}