package gg.dawn.serverapi.api.ui.handler;

import gg.dawn.serverapi.api.player.FeatherPlayer;
import org.jetbrains.annotations.NotNull;

/** Convenience class to create {@link UILoadHandler} objects. */
public class UILoadHandlerAdapter implements UILoadHandler {

  /**
   * Called when an error occurs while loading the UI page for the specified player.
   *
   * @param player the player for whom the UI page failed to load
   * @param errorText the error message describing the load failure
   */
  @Override
  public void onLoadError(@NotNull FeatherPlayer player, @NotNull String errorText) {}
}
