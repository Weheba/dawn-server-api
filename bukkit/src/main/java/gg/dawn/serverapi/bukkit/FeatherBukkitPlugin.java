package gg.dawn.serverapi.bukkit;

import gg.dawn.serverapi.api.FeatherAPI;
import gg.dawn.serverapi.bukkit.event.BukkitEventService;
import gg.dawn.serverapi.bukkit.messaging.BukkitMessagingService;
import gg.dawn.serverapi.bukkit.meta.BukkitMetaService;
import gg.dawn.serverapi.bukkit.player.BukkitPlayerService;
import gg.dawn.serverapi.bukkit.ui.BukkitUIService;
import gg.dawn.serverapi.bukkit.ui.rpc.RpcService;
import gg.dawn.serverapi.bukkit.update.UpdateNotifier;
import gg.dawn.serverapi.bukkit.waypoint.BukkitWaypointService;
import org.bukkit.plugin.java.JavaPlugin;

public class FeatherBukkitPlugin extends JavaPlugin {
  @Override
  public void onDisable() {
    super.onDisable();
  }

  @Override
  public void onEnable() {
    BukkitEventService eventService = new BukkitEventService(this);
    BukkitPlayerService playerService = new BukkitPlayerService(this);

    RpcService rpcService = new RpcService(this);
    BukkitMessagingService messagingService =
        new BukkitMessagingService(this, playerService, rpcService, new UpdateNotifier(this));
    BukkitUIService uiService = new BukkitUIService(messagingService, rpcService);

    BukkitWaypointService waypointService = new BukkitWaypointService(this, playerService);
    BukkitMetaService metaService = new BukkitMetaService(this);

    BukkitFeatherService bukkitFeatherService =
        new BukkitFeatherService(
            eventService, playerService, uiService, waypointService, metaService);
    FeatherAPI.register(bukkitFeatherService);

    super.onEnable();
  }
}
