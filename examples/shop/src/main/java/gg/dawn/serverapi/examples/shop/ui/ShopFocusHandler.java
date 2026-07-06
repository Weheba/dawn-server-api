package gg.dawn.serverapi.examples.shop.ui;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import gg.dawn.serverapi.api.FeatherAPI;
import gg.dawn.serverapi.api.player.FeatherPlayer;
import gg.dawn.serverapi.api.ui.handler.UIFocusHandlerAdapter;
import gg.dawn.serverapi.examples.shop.ShopPlugin;
import org.jetbrains.annotations.NotNull;

public class ShopFocusHandler extends UIFocusHandlerAdapter {
  private static final Gson GSON = new Gson();
  private final ShopPlugin plugin;

  public ShopFocusHandler(ShopPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public void onFocusGained(@NotNull FeatherPlayer player) {
    // Update player balance when focus is gained
    updatePlayerBalance(player);
  }

  private void updatePlayerBalance(FeatherPlayer player) {
    JsonObject message = new JsonObject();
    message.addProperty("type", "updateBalance");
    message.addProperty("balance", this.plugin.getEconomyProvider().getBalance(player));

    FeatherAPI.getUIService()
        .sendPageMessage(player, this.plugin.getShopPage(), GSON.toJson(message));
  }
}
