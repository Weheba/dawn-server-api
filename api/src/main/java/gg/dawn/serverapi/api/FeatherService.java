package gg.dawn.serverapi.api;

import gg.dawn.serverapi.api.event.EventService;
import gg.dawn.serverapi.api.meta.MetaService;
import gg.dawn.serverapi.api.player.PlayerService;
import gg.dawn.serverapi.api.ui.UIService;
import gg.dawn.serverapi.api.waypoint.WaypointService;
import org.jetbrains.annotations.NotNull;

public interface FeatherService {
  @NotNull
  EventService getEventService();

  @NotNull
  PlayerService getPlayerService();

  @NotNull
  UIService getUIService();

  @NotNull
  WaypointService getWaypointService();

  @NotNull
  MetaService getMetaService();
}
