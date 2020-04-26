package net.stevenrafferty.novoid;

import net.stevenrafferty.novoid.commands.NoVoidSpawn;
import net.stevenrafferty.novoid.events.EnteredVoid;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Listener
        getServer().getPluginManager().registerEvents(new EnteredVoid(), this);

        // Command
        getCommand("novoidspawn").setExecutor(new NoVoidSpawn());

        loadConfig();

        System.out.print(ChatColor.GREEN + "NoVoid: Enabled");
    }

    @Override
    public void onDisable() {
        System.out.print(ChatColor.RED + "NoVoid: Disabled");
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

}
