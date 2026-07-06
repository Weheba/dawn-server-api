package gg.dawn.serverapi.messaging.messages.server;

import java.util.Collection;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;
import gg.dawn.serverapi.messaging.ServerMessageHandler;
import gg.dawn.serverapi.messaging.domain.FeatherMod;
import gg.dawn.serverapi.messaging.domain.Platform;

public class C2SClientHello implements Message<ServerMessageHandler> {
  private final Platform platform;
  private final Collection<FeatherMod> featherMods;

  public C2SClientHello(Platform platform, Collection<FeatherMod> featherMods) {
    this.platform = platform;
    this.featherMods = featherMods;
  }

  public C2SClientHello(MessageReader reader) {
    this.platform = reader.readEnum(Platform.class);
    this.featherMods = reader.readList(FeatherMod.DECODER);
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeEnum(this.platform);
    writer.writeCollection(this.featherMods, FeatherMod.ENCODER);
  }

  @Override
  public void handle(ServerMessageHandler handler) {}

  public Platform getPlatform() {
    return this.platform;
  }

  public Collection<FeatherMod> getFeatherMods() {
    return this.featherMods;
  }
}
