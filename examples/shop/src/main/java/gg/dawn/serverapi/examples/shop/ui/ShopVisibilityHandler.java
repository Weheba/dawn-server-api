package gg.dawn.serverapi.examples.shop.ui;

import gg.dawn.serverapi.api.player.FeatherPlayer;
import gg.dawn.serverapi.api.ui.handler.UIVisibilityHandlerAdapter;
import gg.dawn.serverapi.examples.shop.ShopPlugin;
import org.jetbrains.annotations.NotNull;

public class ShopVisibilityHandler extends UIVisibilityHandlerAdapter {
  private final ShopPlugin plugin;

  public ShopVisibilityHandler(ShopPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public void onShow(@NotNull FeatherPlayer player) {
    // Update shop data when shown
    this.plugin.sendShopData(player);
  }
}
