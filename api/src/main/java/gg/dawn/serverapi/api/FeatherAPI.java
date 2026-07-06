package gg.dawn.serverapi.api;

import gg.dawn.serverapi.api.event.EventService;
import gg.dawn.serverapi.api.meta.MetaService;
import gg.dawn.serverapi.api.player.PlayerService;
import gg.dawn.serverapi.api.ui.UIService;
import gg.dawn.serverapi.api.waypoint.WaypointService;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;

public final class FeatherAPI {
  @Internal private static FeatherService featherService = null;

  @Internal
  private FeatherAPI() {
    throw new UnsupportedOperationException();
  }

  @Internal
  public static void register(@NotNull FeatherService featherService) {
    if (FeatherAPI.featherService != null) {
      throw new IllegalStateException();
    }

    FeatherAPI.featherService = featherService;
  }

  public static FeatherService getFeatherService() {
    return featherService;
  }

  /**
   * @see FeatherService#getEventService()
   */
  @NotNull
  public static EventService getEventService() {
    return featherService.getEventService();
  }

  /**
   * @see FeatherService#getPlayerService()
   */
  @NotNull
  public static PlayerService getPlayerService() {
    return featherService.getPlayerService();
  }

  /**
   * @see FeatherService#getUIService()
   */
  @NotNull
  public static UIService getUIService() {
    return featherService.getUIService();
  }

  /**
   * @see FeatherService#getWaypointService()
   */
  @NotNull
  public static WaypointService getWaypointService() {
    return featherService.getWaypointService();
  }

  /**
   * @see FeatherService#getMetaService()
   */
  @NotNull
  public static MetaService getMetaService() {
    return featherService.getMetaService();
  }
}
