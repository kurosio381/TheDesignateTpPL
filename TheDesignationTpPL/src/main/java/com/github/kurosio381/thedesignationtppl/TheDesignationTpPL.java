package com.github.kurosio381.thedesignationtppl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TheDesignationTpPL extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("tdtp")) {
            if (args.length <= 0) {
                return true;
            }
            if (args[0].equalsIgnoreCase("set")) {
                if (args.length <= 1) {
                    return true;
                }
                String name0 = args[1];
                if (name0 == null) return true;
                if (args[1].equalsIgnoreCase(name0)){
                    if (sender.hasPermission("TheDesignation.permission.Admin")) {
                        Location loc = p.getLocation();
                        World world = loc.getWorld();
                        if (world == null) return true;
                        String name = world.getName();
                        int x = loc.getBlockX();
                        int y = loc.getBlockY();
                        int z = loc.getBlockZ();
                        getConfig().set(name0,name + "," + x + "," + y + "," + z);
                        saveConfig();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&aset&fしました"));
                        return true;
                    }
                }
            }

            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("TheDesignation.permission.Admin")) {
                    reloadConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&aconfig&fリロードしました"));
                    return true;
                }
            }

            if (args[0].equalsIgnoreCase("warp")) {
                if (args.length <= 1) {
                    return true;
                }
                if (args[1].equalsIgnoreCase(args[1])) {
                    if (args.length <= 2) {
                        return true;
                    }
                    if (args[2].equalsIgnoreCase(args[2])) {
                        String data = getConfig().getString(args[1]);
                        String data2 = getConfig().getString(args[2]);
                        if (data == null) return true;
                        if (data2 == null) return true;
                        String[] loc = data.split(",");
                        String[] loc2 = data2.split(",");
                        World world = Bukkit.getServer().getWorld(loc[0]);
                        World world2 = Bukkit.getServer().getWorld(loc2[0]);
                        double x = Double.parseDouble(loc[1]);
                        double y = Double.parseDouble(loc[2]);
                        double z = Double.parseDouble(loc[3]);
                        double x2 = Double.parseDouble(loc2[1]);
                        double y2 = Double.parseDouble(loc2[2]);
                        double z2 = Double.parseDouble(loc2[3]);
                        Location location = new Location(world, x, y, z);
                        Location location2 = new Location(world2, x2, y2, z2);
                        Location location11 = p.getLocation();
                        World world1 = location11.getWorld();
                        int Yaw = (int) location11.getYaw();
                        int Pitch = (int) location11.getPitch();
                        Location location1 = new Location(world1,location11.getX(),location11.getY(),location11.getZ());
                        if (!(location1.equals(location))) return true;
                        location2.setYaw(Yaw);
                        location2.setPitch(Pitch);
                        p.teleport(location2);
                    }
                }
                return true;
            }
        }
        return true;
    }
}
