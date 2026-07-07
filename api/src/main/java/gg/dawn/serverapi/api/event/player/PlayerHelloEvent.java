package gg.dawn.serverapi.api.event.player;

import java.util.Collection;
import gg.dawn.serverapi.api.event.FeatherEvent;
import gg.dawn.serverapi.api.model.ClientBrand;
import gg.dawn.serverapi.api.model.FeatherMod;
import gg.dawn.serverapi.api.model.Platform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an event triggered when a player joins the Minecraft server with Feather. This event
 * contains information about the mod launcher used to launch Feather and which mods the player has
 * enabled.
 */
public interface PlayerHelloEvent extends FeatherEvent {

  /**
   * Retrieves the modding platform on which Feather is running.
   *
   * @return the modding platform
   */
  @NotNull
  Platform getPlatform();

  /**
   * Retrieves a collection of Feather mods that are enabled for the player.
   *
   * @return a collection of enabled Feather mods
   */
  @NotNull
  Collection<FeatherMod> getFeatherMods();

  /**
   * Retrieves the client family. Self-declared and spoofable; not for security decisions.
   *
   * @return the client brand
   * @since 0.0.6
   */
  @NotNull
  ClientBrand getClientBrand();

  /**
   * Retrieves the advertised client version, or {@code null} for pre-protocol-7 clients.
   *
   * @return the client version, or null if unknown
   * @since 0.0.6
   */
  @Nullable
  String getClientVersion();
}
