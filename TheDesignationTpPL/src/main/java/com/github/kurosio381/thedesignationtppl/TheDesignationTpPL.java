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

                    //configに記載されていない座標であれば処理を中止する
                    String data = getConfig().getString(args[1]);
                    if (data == null) return true;
                    String data2 = getConfig().getString(args[2]);
                    if (data2 == null) return true;

                    //移動前の座標
                    String[] loc = data.split(",");
                    World world = Bukkit.getServer().getWorld(loc[0]);
                    double x = Double.parseDouble(loc[1]);
                    double y = Double.parseDouble(loc[2]);
                    double z = Double.parseDouble(loc[3]);

                    Location location = new Location(world, x, y, z);
                    if (args[2].equalsIgnoreCase(args[2])){

                        //移動後の座標

                        String[] loc2 = data2.split(",");
                        World world2 = Bukkit.getServer().getWorld(loc2[0]);
                        double x2 = Double.parseDouble(loc2[1]);
                        double y2 = Double.parseDouble(loc2[2]);
                        double z2 = Double.parseDouble(loc2[3]);
                        Location location2a = new Location(world2, x2, y2, z2);

                        //コマンド入力者の座標
                        Location location1 = p.getLocation();
                        World world1 = location1.getWorld();
                        int x1 = (int) location1.getX();
                        int y1 = (int) location1.getY();
                        int z1 = (int) location1.getZ();
                        Location location2 = new Location(world1,x1,y1,z1);

                        if (location2.equals(location)) {
                            int Yaw = (int) location1.getYaw();
                            int Pitch = (int) location1.getPitch();
                            location2a.setYaw(Yaw);
                            location2a.setPitch(Pitch);
                            p.teleport(location2a);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a" + args[2] + "&rへテレポートしました"));
                        }
                    }
                }
                return true;
            }
        }
        return true;
    }
}
