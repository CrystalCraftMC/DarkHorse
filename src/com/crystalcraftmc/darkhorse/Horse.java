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
	
	private void setVariant1(Variant horse)
	{
		// TODO Auto-generated method stub
	}
	
	private void setVariant2(Variant donkey)
	{
		// TODO Auto-generated method stub
	}
	
	private void setVariant3(Variant skeleton_horse)
	{
		// TODO Auto-generated method stub
	}
	
	private void setVariant4(Variant undead_horse)
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
	    if(cmd.getName().equalsIgnoreCase("spawnhorse"))
	    {
	    	// Make the letter 'p' a variable for the command sender (or the player).
			Player p = (Player) sender;
			
    		// Make 'location' a variable for the player's location.
    		Location location = p.getLocation();
			
    		Horse horse = (Horse) location.getWorld().spawnEntity(location, EntityType.HORSE);
			
	        if(args.length != 1)
	        {
	            return false;
	        }
	        
	        else if(args[0].equalsIgnoreCase("normal"))
	        {
	        	// If the sender of the command has this permission...
	    		if(p.hasPermission("darkhorse.normal"))
	    		{
	        		// ...and change the type of horse to a normal horse.
	        		horse.setVariant1(Variant.HORSE);
	        		
	        		// Then, notify the player that the entity has been spawned.
	        		p.sendMessage(ChatColor.GOLD + "A normal horse has been spawned.");
	        		
	        		// If this has happened, the function will return true. 
	        		return true;
	        	}
	        }
	        
	        else if(args[0].equalsIgnoreCase("donkey"))
	        {
	        	// If the sender of the command has this permission...
	    		if(p.hasPermission("darkhorse.donkey"))
	    		{
	    			// ...and change the type of horse to a donkey.
	        		horse.setVariant2(Variant.DONKEY);
	        		
	        		// Then, notify the player that the entity has been spawned.
	        		p.sendMessage(ChatColor.GOLD + "A donkey has been spawned.");
	        		
	        		// If this has happened, the function will return true. 
	        		return true;
	        	}
	        }
	        
	        else if(args[0].equalsIgnoreCase("skeleton"))
	        {
	        	// If the sender of the command has this permission...
	    		if(p.hasPermission("darkhorse.skeleton"))
	    		{
	        		// ...and change the type of horse to a skeleton horse.
	        		horse.setVariant3(Variant.SKELETON_HORSE);
	        		
	        		// Then, notify the player that the entity has been spawned.
	        		p.sendMessage(ChatColor.GOLD + "A skeleton horse has been spawned.");
	        		
	        		// If this has happened, the function will return true. 
	        		return true;
	        	}
	        }
	        
	        else if(args[0].equalsIgnoreCase("zombie"))
	        {
	        	// If the sender of the command has this permission...
	    		if(p.hasPermission("darkhorse.zombie"))
	    		{        		
	        		// ...and change the type of horse to a zombie horse.
	        		horse.setVariant4(Variant.UNDEAD_HORSE);
	        		
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