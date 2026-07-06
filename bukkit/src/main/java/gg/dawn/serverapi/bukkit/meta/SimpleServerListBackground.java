package gg.dawn.serverapi.bukkit.meta;

import gg.dawn.serverapi.api.meta.ServerListBackground;
import gg.dawn.serverapi.api.meta.format.ImageFormat;
import org.jetbrains.annotations.NotNull;

class SimpleServerListBackground implements ServerListBackground {
  private final byte[] image;
  private final byte[] hash;
  private final ImageFormat imageFormat;

  SimpleServerListBackground(byte[] image, byte[] hash, ImageFormat imageFormat) {
    this.image = image;
    this.hash = hash;
    this.imageFormat = imageFormat;
  }

  @Override
  public byte[] getImage() {
    return this.image;
  }

  @Override
  public byte[] getHash() {
    return this.hash;
  }

  @NotNull
  @Override
  public ImageFormat getImageFormat() {
    return this.imageFormat;
  }
}
