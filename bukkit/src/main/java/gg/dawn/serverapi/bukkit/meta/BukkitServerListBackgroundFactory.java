package gg.dawn.serverapi.bukkit.meta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;
import java.util.Set;
import gg.dawn.serverapi.api.meta.ServerListBackground;
import gg.dawn.serverapi.api.meta.ServerListBackgroundFactory;
import gg.dawn.serverapi.api.meta.exception.ImageSizeExceededException;
import gg.dawn.serverapi.api.meta.exception.InvalidImageException;
import gg.dawn.serverapi.api.meta.exception.UnsupportedImageFormatException;
import gg.dawn.serverapi.api.meta.format.ImageFormat;
import org.jetbrains.annotations.NotNull;

class BukkitServerListBackgroundFactory implements ServerListBackgroundFactory {
  @Override
  public @NotNull Set<ImageFormat> getSupportedFormats() {
    return EnumSet.allOf(ImageFormat.class);
  }

  @Override
  public @NotNull ServerListBackground byPath(@NotNull Path path)
      throws IOException,
          UnsupportedImageFormatException,
          ImageSizeExceededException,
          InvalidImageException {
    byte[] imageBytes = Files.readAllBytes(path);
    ServerListBackgroundValidator.validateImageBytes(imageBytes);
    byte[] imageHash = HashingUtils.sha1(imageBytes);
    return new SimpleServerListBackground(imageBytes, imageHash, ImageFormat.PNG);
  }

  @Override
  public @NotNull ServerListBackground fromBytes(byte[] bytes, @NotNull ImageFormat format)
      throws UnsupportedImageFormatException, ImageSizeExceededException, InvalidImageException {
    ServerListBackgroundValidator.validateImageBytes(bytes);
    byte[] imageHash = HashingUtils.sha1(bytes);
    return new SimpleServerListBackground(bytes, imageHash, format);
  }

  @Override
  public @NotNull ServerListBackground fromBytes(byte[] bytes)
      throws UnsupportedImageFormatException, ImageSizeExceededException, InvalidImageException {
    // Currently only PNG is supported, so we can assume PNG format
    return fromBytes(bytes, ImageFormat.PNG);
  }
}
