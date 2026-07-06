package gg.dawn.serverapi.bukkit.ui.rpc;

import gg.dawn.serverapi.api.player.FeatherPlayer;
import gg.dawn.serverapi.api.ui.rpc.RpcRequest;
import gg.dawn.serverapi.bukkit.player.BukkitFeatherPlayer;
import org.jetbrains.annotations.NotNull;

public class BukkitRpcRequest implements RpcRequest {
  @NotNull private final BukkitFeatherPlayer player;
  @NotNull private final String body;

  public BukkitRpcRequest(@NotNull BukkitFeatherPlayer player, @NotNull String body) {
    this.player = player;
    this.body = body;
  }

  @Override
  public @NotNull FeatherPlayer getSource() {
    return this.player;
  }

  @Override
  public @NotNull String getBody() {
    return this.body;
  }
}
