package net.stevenrafferty.novoid.events;

import net.stevenrafferty.novoid.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class EnteredVoid implements Listener {

    Plugin plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();
        if (playerLocation.getBlockY() <= 0) {
            if (hasSetSpawn(player)) {
                player.teleport(getSpawn(player));
                player.setFallDistance(0);
            }
        }
    }

    private Location getSpawn(Player player) {
        Double x = plugin.getConfig().getDouble("x");
        Double y = plugin.getConfig().getDouble("y");
        Double z = plugin.getConfig().getDouble("z");

        float yaw = (float) plugin.getConfig().getDouble("yaw");
        float pitch = (float) plugin.getConfig().getDouble("pitch");

        Location spawn = new Location(player.getWorld(), x, y, z);

        spawn.setYaw(yaw);
        spawn.setPitch(pitch);

        return spawn;
    }

    private boolean hasSetSpawn(Player player) {
        boolean hasX = plugin.getConfig().isSet("x");
        boolean hasY = plugin.getConfig().isSet("y");
        boolean hasZ = plugin.getConfig().isSet("z");
        boolean hasYaw = plugin.getConfig().isSet("yaw");
        boolean hasPitch = plugin.getConfig().isSet("pitch");

        if (hasX && hasY && hasZ && hasYaw && hasPitch) {
            return true;
        }
        player.sendMessage("You have not yet set the spawn location for NoVoid\nYou can set the spawn location by entering /novoidspawn");
        return false;
    }

}
