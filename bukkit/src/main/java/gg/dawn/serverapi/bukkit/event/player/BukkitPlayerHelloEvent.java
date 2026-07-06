package gg.dawn.serverapi.bukkit.event.player;

import java.util.Collection;
import java.util.Collections;
import gg.dawn.serverapi.api.event.player.PlayerHelloEvent;
import gg.dawn.serverapi.api.model.FeatherMod;
import gg.dawn.serverapi.api.model.Platform;
import gg.dawn.serverapi.api.player.FeatherPlayer;
import gg.dawn.serverapi.bukkit.event.BukkitFeatherEvent;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class BukkitPlayerHelloEvent extends BukkitFeatherEvent implements PlayerHelloEvent {
  private static final HandlerList HANDLER_LIST = new HandlerList();

  @NotNull private final Platform platform;
  @NotNull private final Collection<FeatherMod> featherMods;

  public BukkitPlayerHelloEvent(
      @NotNull FeatherPlayer player,
      @NotNull Platform platform,
      @NotNull Collection<FeatherMod> featherMods) {
    super(player);
    this.platform = platform;
    this.featherMods = featherMods;
  }

  public static HandlerList getHandlerList() {
    return HANDLER_LIST;
  }

  @NotNull
  @Override
  public Platform getPlatform() {
    return this.platform;
  }

  @NotNull
  @Override
  public Collection<FeatherMod> getFeatherMods() {
    return Collections.unmodifiableCollection(this.featherMods);
  }

  @Override
  public HandlerList getHandlers() {
    return HANDLER_LIST;
  }
}
