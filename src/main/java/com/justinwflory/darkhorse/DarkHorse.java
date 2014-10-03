/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Justin W. Flory
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.justinwflory.darkhorse;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DarkHorse extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DarkHorse v1.3 has been enabled!");

        // Link plugin with metrics.
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }

        // Generate the config.yml file and load defaults.
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();

        if (getConfig().getBoolean("auto-update")) {
            @SuppressWarnings({ "unused" })
            Updater updater = new Updater(this, 61717, getFile(), Updater.UpdateType.DEFAULT, true);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("DarkHorse v1.3 has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("dh")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be run by a player.");
                return false;
            }

            Player p = (Player) sender;

            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "/dh <horse type> [tamed] [chest]");
                return false;
            } else {
                if (args.length == 3 && args[1].equalsIgnoreCase("tamed") && args[2].equalsIgnoreCase("chest")) {
                    if (args[0].equalsIgnoreCase("horse") && p.hasPermission("darkhorse.horse")) {
                        spawnHorse(p, Variant.HORSE, true, true);
                        return true;
                    } else if (args[0].equalsIgnoreCase("donkey") && p.hasPermission("darkhorse.donkey")) {
                        spawnHorse(p, Variant.DONKEY, true, true);
                        return true;
                    } else if (args[0].equalsIgnoreCase("mule") && p.hasPermission("darkhorse.mule")) {
                        spawnHorse(p, Variant.DONKEY, true, true);
                        return true;
                    } else if (args[0].equalsIgnoreCase("skeleton") && p.hasPermission("darkhorse.skeleton")) {
                        spawnHorse(p, Variant.SKELETON_HORSE, true, true);
                        return true;
                    } else if (args[0].equalsIgnoreCase("zombie") && p.hasPermission("darkhorse.zombie")) {
                        spawnHorse(p, Variant.UNDEAD_HORSE, true, true);
                        return true;
                    } else {
                        return false;
                    }
                } else if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("tamed")) {
                        if (args[0].equalsIgnoreCase("horse") && p.hasPermission("darkhorse.horse")) {
                            spawnHorse(p, Variant.HORSE, true, false);
                            return true;
                        } else if (args[0].equalsIgnoreCase("donkey") && p.hasPermission("darkhorse.donkey")) {
                            spawnHorse(p, Variant.DONKEY, true, false);
                            return true;
                        } else if (args[0].equalsIgnoreCase("mule") && p.hasPermission("darkhorse.mule")) {
                            spawnHorse(p, Variant.MULE, true, false);
                            return true;
                        } else if (args[0].equalsIgnoreCase("skeleton") && p.hasPermission("darkhorse.skeleton")) {
                            spawnHorse(p, Variant.SKELETON_HORSE, true, false);
                            return true;
                        } else if (args[0].equalsIgnoreCase("zombie") && p.hasPermission("darkhorse.zombie")) {
                            spawnHorse(p, Variant.UNDEAD_HORSE, true, false);
                            return true;
                        } else {
                            return false;
                        }
                    } else if (args[1].equalsIgnoreCase("chest")) {
                        if (args[0].equalsIgnoreCase("horse") && p.hasPermission("darkhorse.horse")) {
                            spawnHorse(p, Variant.HORSE, false, true);
                            return true;
                        } else if (args[0].equalsIgnoreCase("donkey") && p.hasPermission("darkhorse.donkey")) {
                            spawnHorse(p, Variant.DONKEY, false, true);
                            return true;
                        } else if (args[0].equalsIgnoreCase("mule") && p.hasPermission("darkhorse.mule")) {
                            spawnHorse(p, Variant.MULE, false, true);
                            return true;
                        } else if (args[0].equalsIgnoreCase("skeleton") && p.hasPermission("darkhorse.skeleton")) {
                            spawnHorse(p, Variant.SKELETON_HORSE, false, true);
                            return true;
                        } else if (args[0].equalsIgnoreCase("zombie") && p.hasPermission("darkhorse.zombie")) {
                            spawnHorse(p, Variant.UNDEAD_HORSE, false, true);
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("horse") && p.hasPermission("darkhorse.horse")) {
                        spawnHorse(p, Variant.HORSE, false, false);
                        return true;
                    } else if (args[0].equalsIgnoreCase("donkey") && p.hasPermission("darkhorse.donkey")) {
                        spawnHorse(p, Variant.DONKEY, false, false);
                        return true;
                    } else if (args[0].equalsIgnoreCase("mule") && p.hasPermission("darkhorse.mule")) {
                        spawnHorse(p, Variant.MULE, false, false);
                        return true;
                    } else if (args[0].equalsIgnoreCase("skeleton") && p.hasPermission("darkhorse.skeleton")) {
                        spawnHorse(p, Variant.SKELETON_HORSE, false, false);
                        return true;
                    } else if (args[0].equalsIgnoreCase("zombie") && p.hasPermission("darkhorse.zombie")) {
                        spawnHorse(p, Variant.UNDEAD_HORSE, false, false);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    private void spawnHorse(Player player, Variant horseType, boolean tamed, boolean chest) {
        Location location = player.getLocation();
        Horse horse = location.getWorld().spawn(location, Horse.class);
        horse.setVariant(horseType);
        if (tamed) {
            horse.setTamed(true);
            horse.setOwner(player);
        }
        if (chest) {
            horse.setCarryingChest(true);
        }
        notifySpawn(player);
    }

    private void notifySpawn(Player player) {
        player.sendMessage(ChatColor.GOLD + "Say hello to your new friend!");
    }
}