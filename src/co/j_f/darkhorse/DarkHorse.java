package co.j_f.darkhorse;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DarkHorse extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DarkHorse v1.2.2 has been enabled!");
        
        // Link plugin with online stats.
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e){
            // Failed to submit the stats :-(
        }
        
        // Generate the config.yml file...
        saveDefaultConfig();
        // ...load the configuration file and copy the defaults into the plugin...
        getConfig().options().copyDefaults(true);
        // ...and save the configuration file.
        saveConfig();

        // ...see if the config file allows auto-updating...
        if (getConfig().getBoolean("auto-update")) {
            // ...and if so, run the auto-update class.
            @SuppressWarnings({ "unused" })
            Updater updater = new Updater(this, 61717, getFile(), Updater.UpdateType.DEFAULT, true);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("DarkHorse v1.2.2 has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("dh")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be run by a player.");
                return false;
            }
            // Make the letter 'p' a variable for the command sender (or the player).
            Player p = (Player) sender;
            if (args.length == 0) {
                sender.sendMessage("/dh <type> [tamed]");
                return false;
            } else {
                if(args[0].equalsIgnoreCase("horse")) {
                    if (args.length == 2 && args[1].equalsIgnoreCase("tamed")) {
                        if (p.hasPermission("darkhorse.horse.tamed")) {
                            spawnHorse(p, Variant.HORSE, true);
                            p.sendMessage(ChatColor.GOLD + "A tamed horse has been spawned.");
                            return true;
                        }
                    } else {
                        if (p.hasPermission("darkhorse.horse")) {
                            spawnHorse(p, Variant.HORSE, false);
                            p.sendMessage(ChatColor.GOLD + "A horse has been spawned.");
                            return true;
                        }
                    }
                } else if(args[0].equalsIgnoreCase("donkey")) {
                    if (args.length == 2 && args[1].equalsIgnoreCase("tamed")) {
                        if (p.hasPermission("darkhorse.donkey.tamed")) {
                            spawnHorse(p, Variant.DONKEY, true);
                            p.sendMessage(ChatColor.GOLD + "A tamed donkey has been spawned.");
                            return true;
                        }
                    } else {
                        if (p.hasPermission("darkhorse.donkey")) {
                            spawnHorse(p, Variant.DONKEY, false);
                            p.sendMessage(ChatColor.GOLD + "A donkey has been spawned.");
                            return true;
                        }
                    }
                } else if(args[0].equalsIgnoreCase("mule")) {
                    if (args.length == 2 && args[1].equalsIgnoreCase("tamed")) {
                        if (p.hasPermission("darkhorse.mule.tamed")) {
                            spawnHorse(p, Variant.MULE, true);
                            p.sendMessage(ChatColor.GOLD + "A tamed mule has been spawned.");
                            return true;
                        }
                    } else {
                        if (p.hasPermission("darkhorse.mule")) {
                            spawnHorse(p, Variant.MULE, false);
                            p.sendMessage(ChatColor.GOLD + "A mule has been spawned.");
                            return true;
                        }
                    }
                } else if(args[0].equalsIgnoreCase("skeleton")) {
                    if (args.length == 2 && args[1].equalsIgnoreCase("tamed")) {
                        if (p.hasPermission("darkhorse.skeleton.tamed")) {
                            spawnHorse(p, Variant.SKELETON_HORSE, true);
                            p.playSound(p.getLocation(), Sound.HORSE_SKELETON_IDLE, 1, 1);
                            p.sendMessage(ChatColor.GOLD + "A tamed skeleton horse has been spawned.");
                            return true;
                        }
                    } else {
                        if (p.hasPermission("darkhorse.skeleton")) {
                            spawnHorse(p, Variant.SKELETON_HORSE, false);
                            p.playSound(p.getLocation(), Sound.HORSE_SKELETON_IDLE, 1, 1);
                            p.sendMessage(ChatColor.GOLD + "A skeleton horse has been spawned.");
                            return true;
                        }
                    }
                } else if(args[0].equalsIgnoreCase("zombie")) {
                    if (args.length == 2 && args[1].equalsIgnoreCase("tamed")) {
                        if (p.hasPermission("darkhorse.zombie.tamed")) {
                            spawnHorse(p, Variant.UNDEAD_HORSE, true);
                            p.playSound(p.getLocation(), Sound.HORSE_ZOMBIE_IDLE, 1, 1);
                            p.sendMessage(ChatColor.GOLD + "A tamed zombie horse has been spawned.");
                            return true;
                        }
                    } else {
                        if (p.hasPermission("darkhorse.zombie")) {
                            spawnHorse(p, Variant.UNDEAD_HORSE, false);
                            p.playSound(p.getLocation(), Sound.HORSE_ZOMBIE_IDLE, 1, 1);
                            p.sendMessage(ChatColor.GOLD + "A zombie horse has been spawned.");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void spawnHorse(Player player, Variant variant, boolean tamed) {
        // ...create a variable to find the player's location...
        Location location = player.getLocation();
        // ...then spawn a horse at the player's current location...
        Horse horse = location.getWorld().spawn(location, Horse.class);
        // ...and change the type of the horse
        horse.setVariant(variant);
        if(tamed) {
            // ...and make it tamed if said so
            horse.setTamed(true);
            horse.setOwner(player);
        }
    }

}