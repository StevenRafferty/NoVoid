package net.stevenrafferty.novoid.commands;

import net.stevenrafferty.novoid.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class NoVoidSpawn implements CommandExecutor {

    Plugin plugin = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Location location = player.getLocation();

            plugin.getConfig().set("x", location.getX());
            plugin.getConfig().set("y", location.getY());
            plugin.getConfig().set("z", location.getZ());

            plugin.getConfig().set("yaw", location.getYaw());
            plugin.getConfig().set("pitch", location.getPitch());

            plugin.saveConfig();
        } else {
           sender.sendMessage("The console cannot execute this command");
        }
        return true;
    }
}
