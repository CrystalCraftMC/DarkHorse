package com.crystalcraftmc.darkhorse;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin
{
	@Override
    public void onEnable()
	{
        // TODO Insert logic to be performed when the plugin is enabled
		getLogger().info("DarkHorse has been enabled!");
		
		// ...link plugin with online stats.
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e){
			// Failed to submit the stats :-(
		}
		
		// Generate the config.yml file...
		this.saveDefaultConfig();
		
		// ...load the configuration file and copy the defaults into the plugin...
		this.getConfig().options().copyDefaults(true);
		
		// ...and save the configuration file.
        this.saveConfig();
        
        // ...see if the config file allows auto-updating...
        if (this.getConfig().getBoolean("auto-update"))
        {
        	// ...and if so, run the auto-update class.
        	@SuppressWarnings("unused")
			Updater updater = new Updater(this, "darkhorse", this.getFile(), Updater.UpdateType.DEFAULT, true);
        }
        
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("spawnhorse").setExecutor(new Horse(this));
	}
	
    @Override
    public void onDisable()
    {
        // TODO Insert logic to be performed when the plugin is disabled
    	getLogger().info("DarkHorse has been disabled!");
    }
}