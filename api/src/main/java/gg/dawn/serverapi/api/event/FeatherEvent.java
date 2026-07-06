package gg.dawn.serverapi.api.event;

import gg.dawn.serverapi.api.player.FeatherPlayer;
import org.jetbrains.annotations.NotNull;

public interface FeatherEvent {

  /**
   * Returns the player associated with this event.
   *
   * @return the player associated with this event
   */
  @NotNull
  FeatherPlayer getPlayer();
}
