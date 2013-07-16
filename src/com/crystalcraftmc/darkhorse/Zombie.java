package com.crystalcraftmc.darkhorse;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Zombie implements CommandExecutor
{
	Main plugin;
	public Zombie(Main plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		// Make the letter 'p' a variable for the command sender (or the player).
		Player p = (Player) sender;
		
		// If the player typed /twitter, then do the following...
    	if (cmd.getName().equalsIgnoreCase("zombiehorse"))
    	{
    		Location location = p.getLocation();
    		p.getWorld().spawnEntity(location, EntityType.HORSE);
    		p.sendMessage(ChatColor.GOLD + "Zombie horse has been spawned.");
    		Bukkit.broadcastMessage(ChatColor.GOLD + "A new mob has appeared at " + location);
    		
    		// If this has happened, the function will return true. 
    		return true;
    	}
    	// If this hasn't happened, a value of false will be returned.
    	return false;
	}
}
