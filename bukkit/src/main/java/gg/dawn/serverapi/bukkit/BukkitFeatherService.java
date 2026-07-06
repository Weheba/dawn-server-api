package gg.dawn.serverapi.bukkit;

import gg.dawn.serverapi.api.FeatherService;
import gg.dawn.serverapi.api.event.EventService;
import gg.dawn.serverapi.api.meta.MetaService;
import gg.dawn.serverapi.api.player.PlayerService;
import gg.dawn.serverapi.api.ui.UIService;
import gg.dawn.serverapi.api.waypoint.WaypointService;
import gg.dawn.serverapi.bukkit.event.BukkitEventService;
import gg.dawn.serverapi.bukkit.meta.BukkitMetaService;
import gg.dawn.serverapi.bukkit.player.BukkitPlayerService;
import gg.dawn.serverapi.bukkit.ui.BukkitUIService;
import gg.dawn.serverapi.bukkit.waypoint.BukkitWaypointService;
import org.jetbrains.annotations.NotNull;

public class BukkitFeatherService implements FeatherService {
  @NotNull private final BukkitEventService eventService;
  @NotNull private final BukkitPlayerService playerService;
  @NotNull private final BukkitUIService uiService;
  @NotNull private final BukkitWaypointService waypointService;
  @NotNull private final BukkitMetaService metaService;

  public BukkitFeatherService(
      @NotNull BukkitEventService eventService,
      @NotNull BukkitPlayerService playerService,
      @NotNull BukkitUIService uiService,
      @NotNull BukkitWaypointService waypointService,
      @NotNull BukkitMetaService metaService) {
    this.eventService = eventService;
    this.playerService = playerService;
    this.uiService = uiService;
    this.waypointService = waypointService;
    this.metaService = metaService;
  }

  @Override
  public @NotNull EventService getEventService() {
    return this.eventService;
  }

  @Override
  public @NotNull PlayerService getPlayerService() {
    return this.playerService;
  }

  @Override
  public @NotNull UIService getUIService() {
    return this.uiService;
  }

  @Override
  public @NotNull WaypointService getWaypointService() {
    return this.waypointService;
  }

  @Override
  public @NotNull MetaService getMetaService() {
    return this.metaService;
  }
}
