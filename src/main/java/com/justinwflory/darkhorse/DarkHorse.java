/*
 * Copyright (c) 2015 Justin W. Flory
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.justinwflory.darkhorse;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/**
 * This plugin allows a user to spawn in any variety of horse available within a vanilla Minecraft client. This
 * includes some of the more exclusive horses such as skeleton and zombie horses.
 *
 * @author Justin W. Flory
 */
public class DarkHorse extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DarkHorse has been enabled!");

        // Generate the config.yml file and load defaults.
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();

        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }

        if (getConfig().getBoolean("auto-update")) {
            @SuppressWarnings({ "unused" })
            Updater updater = new Updater(this, 61717, getFile(), Updater.UpdateType.DEFAULT, true);
        }
    }

    @Override
    public void onDisable() { getLogger().info("DarkHorse has been disabled!"); }

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
                        notify(p);
                        return true;
                    } else if (args[0].equalsIgnoreCase("donkey") && p.hasPermission("darkhorse.donkey")) {
                        spawnHorse(p, Variant.DONKEY, true, true);
                        notify(p);
                        return true;
                    } else if (args[0].equalsIgnoreCase("mule") && p.hasPermission("darkhorse.mule")) {
                        spawnHorse(p, Variant.DONKEY, true, true);
                        notify(p);
                        return true;
                    } else if (args[0].equalsIgnoreCase("skeleton") && p.hasPermission("darkhorse.skeleton")) {
                        spawnHorse(p, Variant.SKELETON_HORSE, true, true);
                        notify(p);
                        return true;
                    } else if (args[0].equalsIgnoreCase("zombie") && p.hasPermission("darkhorse.zombie")) {
                        spawnHorse(p, Variant.UNDEAD_HORSE, true, true);
                        notify(p);
                        return true;
                    } else {
                        return false;
                    }
                } else if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("tamed")) {
                        if (args[0].equalsIgnoreCase("horse") && p.hasPermission("darkhorse.horse")) {
                            spawnHorse(p, Variant.HORSE, true, false);
                            notify(p);
                            return true;
                        } else if (args[0].equalsIgnoreCase("donkey") && p.hasPermission("darkhorse.donkey")) {
                            spawnHorse(p, Variant.DONKEY, true, false);
                            notify(p);
                            return true;
                        } else if (args[0].equalsIgnoreCase("mule") && p.hasPermission("darkhorse.mule")) {
                            spawnHorse(p, Variant.MULE, true, false);
                            notify(p);
                            return true;
                        } else if (args[0].equalsIgnoreCase("skeleton") && p.hasPermission("darkhorse.skeleton")) {
                            spawnHorse(p, Variant.SKELETON_HORSE, true, false);
                            notify(p);
                            return true;
                        } else if (args[0].equalsIgnoreCase("zombie") && p.hasPermission("darkhorse.zombie")) {
                            spawnHorse(p, Variant.UNDEAD_HORSE, true, false);
                            notify(p);
                            return true;
                        } else {
                            return false;
                        }
                    } else if (args[1].equalsIgnoreCase("chest")) {
                        if (args[0].equalsIgnoreCase("horse") && p.hasPermission("darkhorse.horse")) {
                            spawnHorse(p, Variant.HORSE, false, true);
                            notify(p);
                            return true;
                        } else if (args[0].equalsIgnoreCase("donkey") && p.hasPermission("darkhorse.donkey")) {
                            spawnHorse(p, Variant.DONKEY, false, true);
                            notify(p);
                            return true;
                        } else if (args[0].equalsIgnoreCase("mule") && p.hasPermission("darkhorse.mule")) {
                            spawnHorse(p, Variant.MULE, false, true);
                            notify(p);
                            return true;
                        } else if (args[0].equalsIgnoreCase("skeleton") && p.hasPermission("darkhorse.skeleton")) {
                            spawnHorse(p, Variant.SKELETON_HORSE, false, true);
                            notify(p);
                            return true;
                        } else if (args[0].equalsIgnoreCase("zombie") && p.hasPermission("darkhorse.zombie")) {
                            spawnHorse(p, Variant.UNDEAD_HORSE, false, true);
                            notify(p);
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("horse") && p.hasPermission("darkhorse.horse")) {
                        spawnHorse(p, Variant.HORSE, false, false);
                        notify(p);
                        return true;
                    } else if (args[0].equalsIgnoreCase("donkey") && p.hasPermission("darkhorse.donkey")) {
                        spawnHorse(p, Variant.DONKEY, false, false);
                        notify(p);
                        return true;
                    } else if (args[0].equalsIgnoreCase("mule") && p.hasPermission("darkhorse.mule")) {
                        spawnHorse(p, Variant.MULE, false, false);
                        notify(p);
                        return true;
                    } else if (args[0].equalsIgnoreCase("skeleton") && p.hasPermission("darkhorse.skeleton")) {
                        spawnHorse(p, Variant.SKELETON_HORSE, false, false);
                        notify(p);
                        return true;
                    } else if (args[0].equalsIgnoreCase("zombie") && p.hasPermission("darkhorse.zombie")) {
                        spawnHorse(p, Variant.UNDEAD_HORSE, false, false);
                        notify(p);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    private void notify(Player player) {
        player.sendMessage(ChatColor.GOLD + "Say hello to your new friend!");
    }

    private void spawnHorse(Player player, Variant variant, boolean tamed, boolean chest) {
        Location location = player.getLocation();
        Horse horse = location.getWorld().spawn(location, Horse.class);
        horse.setVariant(variant);
        if (tamed) {
            horse.setTamed(true);
            horse.setOwner(player);
        }
        if (chest) {
            horse.setCarryingChest(true);
        }
    }
}