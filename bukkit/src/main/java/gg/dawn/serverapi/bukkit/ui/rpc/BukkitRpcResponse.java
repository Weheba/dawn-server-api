package gg.dawn.serverapi.bukkit.ui.rpc;

import gg.dawn.serverapi.api.ui.rpc.RpcResponse;
import gg.dawn.serverapi.bukkit.FeatherBukkitPlugin;
import gg.dawn.serverapi.bukkit.player.BukkitFeatherPlayer;
import gg.dawn.serverapi.messaging.messages.client.S2CFUIResponse;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class BukkitRpcResponse implements RpcResponse {
  private final FeatherBukkitPlugin plugin;
  public final int id;
  @NotNull public final BukkitFeatherPlayer player;

  public BukkitRpcResponse(
      int id, @NotNull BukkitFeatherPlayer player, @NotNull FeatherBukkitPlugin plugin) {
    this.id = id;
    this.player = player;
    this.plugin = plugin;
  }

  @Override
  public void respond(final @NotNull String jsonResponse) {
    sendOnMainThread(
        () -> this.player.sendMessage(new S2CFUIResponse(this.id, true, jsonResponse)));
  }

  private void sendOnMainThread(Runnable task) {
    if (!Bukkit.getServer().isPrimaryThread()) {
      Bukkit.getScheduler().runTask(this.plugin, task);
    } else {
      task.run();
    }
  }
}
