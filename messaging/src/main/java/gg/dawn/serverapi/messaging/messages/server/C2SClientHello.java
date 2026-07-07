package gg.dawn.serverapi.messaging.messages.server;

import java.util.Collection;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;
import gg.dawn.serverapi.messaging.ServerMessageHandler;
import gg.dawn.serverapi.messaging.domain.ClientBrand;
import gg.dawn.serverapi.messaging.domain.FeatherMod;
import gg.dawn.serverapi.messaging.domain.Platform;

public class C2SClientHello implements Message<ServerMessageHandler> {
  private final Platform platform;
  private final Collection<FeatherMod> featherMods;
  private final ClientBrand brand;
  private final String clientVersion;

  public C2SClientHello(Platform platform, Collection<FeatherMod> featherMods) {
    this(platform, featherMods, ClientBrand.FEATHER, "");
  }

  public C2SClientHello(
      Platform platform,
      Collection<FeatherMod> featherMods,
      ClientBrand brand,
      String clientVersion) {
    this.platform = platform;
    this.featherMods = featherMods;
    this.brand = brand;
    this.clientVersion = clientVersion;
  }

  public C2SClientHello(MessageReader reader) {
    this.platform = reader.readEnum(Platform.class);
    this.featherMods = reader.readList(FeatherMod.DECODER);
    // Protocol 7 trailing fields: absent from legacy clients, ignored by old plugins
    if (reader.hasRemaining()) {
      this.brand = reader.readEnum(ClientBrand.class);
      this.clientVersion = reader.readUtf(64);
    } else {
      this.brand = ClientBrand.FEATHER;
      this.clientVersion = "";
    }
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeEnum(this.platform);
    writer.writeCollection(this.featherMods, FeatherMod.ENCODER);
    writer.writeEnum(this.brand);
    writer.writeUtf(this.clientVersion);
  }

  @Override
  public void handle(ServerMessageHandler handler) {}

  public Platform getPlatform() {
    return this.platform;
  }

  public Collection<FeatherMod> getFeatherMods() {
    return this.featherMods;
  }

  public ClientBrand getBrand() {
    return this.brand;
  }

  public String getClientVersion() {
    return this.clientVersion;
  }
}
