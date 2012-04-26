package net.crafthub.noobidoo.teleportgamemode;

import java.util.logging.Logger;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Teleportgamemode extends JavaPlugin implements Listener {
	private static final Logger log = Logger.getLogger("Minecraft");
	public static Permission permission = null;
	public static Config conf;

	public void onDisable() {
        // TODO: Place any custom disable code here.
    }

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new TeleportPlayerListener(this), this);
        if (!setupPermissions()){
            log.info(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        conf = new Config(this);
    }
    
    private boolean setupPermissions()
    {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
}

