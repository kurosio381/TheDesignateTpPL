package com.github.kurosio381.thedesignationtppl;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

final class TheDesignateTpPL extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("TheDesignateTpPL")) {
            if (args.length <= 0) {
                return false;
            }
            if (args[0].equalsIgnoreCase("test")) {
                p.sendMessage("テスト");
                return true;
            }
        }
        return true;
    }

}