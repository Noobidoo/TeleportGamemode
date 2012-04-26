package net.crafthub.noobidoo.teleportgamemode;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

public class TeleportPlayerListener implements Listener{
	Teleportgamemode plugin;
	public TeleportPlayerListener(Teleportgamemode plugin) {
		super();
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event)
	{
		if (event.isCancelled()) {
		} else {
			Player pl = event.getPlayer();
			if(pl.getGameMode()==GameMode.CREATIVE && pl.hasPermission("nodrop.deny"))
				event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event)
	{
		Player pl = (Player) event.getEntity();
		if(Teleportgamemode.permission.getPrimaryGroup(pl)==Teleportgamemode.conf.getParticipantclass()){
			Teleportgamemode.permission.playerRemoveGroup(pl, Teleportgamemode.conf.getParticipantclass());
			Teleportgamemode.permission.playerAddGroup(pl, Teleportgamemode.conf.getSpectatorclass());
			pl.kickPlayer("Rejoin to spectate the match!");
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		SetMode(event.getPlayer());
	}

	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		if (event.isCancelled()) {
		} else {
			final Player player = event.getPlayer();
			SetMode(player);
		}
	}

	private void SetMode(final Player player) {
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				if(Teleportgamemode.permission.playerHas(player, "autogamemodepermissions.admin")){
					return;
				} else if (Teleportgamemode.permission.playerHas(player,"autogamemodepermissions.0")) {
					player.setGameMode(GameMode.SURVIVAL);                   
				} else if (Teleportgamemode.permission.playerHas(player,"autogamemodepermissions.1")) {
					player.setGameMode(GameMode.CREATIVE);
				}
			}
		}, 5);
	}
}
