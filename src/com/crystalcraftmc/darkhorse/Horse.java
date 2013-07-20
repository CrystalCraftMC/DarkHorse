package com.crystalcraftmc.darkhorse;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Variant;

public class Horse implements CommandExecutor
{
	Main plugin;
	public Horse(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
	    if(cmd.getName().equalsIgnoreCase("spawnhorse"))
	    {
	    	// Make the letter 'p' a variable for the command sender (or the player).
			Player p = (Player) sender;
			
	        if(args.length != 1)
	        {
	            return false;
	        }
	        
	        else if(args[0].equalsIgnoreCase("normal"))
	        {
	        	// If the sender of the command has this permission...
	    		if(p.hasPermission("darkhorse.normal"))
	    		{
	        		// ...create a variable to find the player's location...
	        		Location location = p.getEyeLocation();
	        		
	        		// ...then spawn a horse at the player's current location...
	        		Horse horse = (Horse) location.getWorld().spawnEntity(location, EntityType.HORSE);
	        		
	        		// ...and change the type of horse to a normal horse.
	        		((org.bukkit.entity.Horse) horse).setVariant(Variant.HORSE);
	        		
	        		// Then, notify the player that the entity has been spawned.
	        		p.sendMessage(ChatColor.GOLD + "A normal horse has been spawned.");
	        		
	        		// If this has happened, the function will return true. 
	        		return true;
	        	}
	        }
	        
	        else if(args[1].equalsIgnoreCase("donkey"))
	        {
	        	// If the sender of the command has this permission...
	    		if(p.hasPermission("darkhorse.donkey"))
	    		{
	        		// ...create a variable to find the player's location...
	        		Location location = p.getEyeLocation();
	        		
	        		// ...then spawn a horse at the player's current location...
	        		Horse horse = (Horse) location.getWorld().spawnEntity(location, EntityType.HORSE);
	        		
	        		// ...and change the type of horse to a donkey.
	        		((org.bukkit.entity.Horse) horse).setVariant(Variant.DONKEY);
	        		
	        		// Then, notify the player that the entity has been spawned.
	        		p.sendMessage(ChatColor.GOLD + "A donkey has been spawned.");
	        		
	        		// If this has happened, the function will return true. 
	        		return true;
	        	}
	        }
	        
	        else if(args[2].equalsIgnoreCase("skeleton"))
	        {
	        	// If the sender of the command has this permission...
	    		if(p.hasPermission("darkhorse.skeleton"))
	    		{
	        		// ...create a variable to find the player's location...
	        		Location location = p.getEyeLocation();
	        		
	        		// ...then spawn a horse at the player's current location...
	        		Horse horse = (Horse) location.getWorld().spawnEntity(location, EntityType.HORSE);
	        		
	        		// ...and change the type of horse to a skeleton horse.
	        		((org.bukkit.entity.Horse) horse).setVariant(Variant.SKELETON_HORSE);
	        		
	        		// Then, notify the player that the entity has been spawned.
	        		p.sendMessage(ChatColor.GOLD + "A skeleton horse has been spawned.");
	        		
	        		// If this has happened, the function will return true. 
	        		return true;
	        	}
	        }
	        
	        else if(args[3].equalsIgnoreCase("zombie"))
	        {
	        	// If the sender of the command has this permission...
	    		if(p.hasPermission("darkhorse.zombie"))
	    		{
	        		// ...create a variable to find the player's location...
	        		Location location = p.getEyeLocation();
	        		
	        		// ...then spawn a horse at the player's current location...
	        		Horse horse = (Horse) location.getWorld().spawnEntity(location, EntityType.HORSE);
	        		
	        		// ...and change the type of horse to a zombie horse.
	        		((org.bukkit.entity.Horse) horse).setVariant(Variant.UNDEAD_HORSE);
	        		
	        		// Then, notify the player that the entity has been spawned.
	        		p.sendMessage(ChatColor.GOLD + "A zombie horse has been spawned.");
	        		
	        		// If this has happened, the function will return true. 
	        		return true;
	        	}
	        }

	    }
	    // If this hasn't happened, a value of false will be returned.
		return false;
	}
}