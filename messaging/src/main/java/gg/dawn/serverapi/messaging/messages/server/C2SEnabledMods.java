package gg.dawn.serverapi.messaging.messages.server;

import java.util.Collection;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;
import gg.dawn.serverapi.messaging.ServerMessageHandler;
import gg.dawn.serverapi.messaging.domain.FeatherMod;

public class C2SEnabledMods implements Message<ServerMessageHandler> {
  private final int id;
  private final Collection<FeatherMod> mods;

  public C2SEnabledMods(int id, Collection<FeatherMod> mods) {
    this.id = id;
    this.mods = mods;
  }

  public C2SEnabledMods(MessageReader reader) {
    this.id = reader.readVarInt();
    this.mods = reader.readList(FeatherMod.DECODER);
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeVarInt(this.id);
    writer.writeCollection(this.mods, FeatherMod.ENCODER);
  }

  @Override
  public void handle(ServerMessageHandler handler) {
    handler.handle(this);
  }

  public int getId() {
    return this.id;
  }

  public Collection<FeatherMod> getMods() {
    return this.mods;
  }
}
