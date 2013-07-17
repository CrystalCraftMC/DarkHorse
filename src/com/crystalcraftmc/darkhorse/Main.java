package com.crystalcraftmc.darkhorse;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin
{
	@Override
    public void onEnable()
	{
        // TODO Insert logic to be performed when the plugin is enabled
		getLogger().info("DarkHorse has been enabled!");
		
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("skeletonhorse").setExecutor(new Skeleton(this));
		
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("zombiehorse").setExecutor(new Zombie(this));
	}
	
    @Override
    public void onDisable()
    {
        // TODO Insert logic to be performed when the plugin is disabled
    	getLogger().info("DarkHorse has been disabled!");
    }
}
