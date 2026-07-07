package gg.dawn.serverapi.messaging.messages.server;

import java.util.Collection;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;
import gg.dawn.serverapi.messaging.ServerMessageHandler;
import gg.dawn.serverapi.messaging.domain.ClientMod;

public class C2SModList implements Message<ServerMessageHandler> {
  private final int id;
  private final Collection<ClientMod> mods;

  public C2SModList(int id, Collection<ClientMod> mods) {
    this.id = id;
    this.mods = mods;
  }

  public C2SModList(MessageReader reader) {
    this.id = reader.readVarInt();
    this.mods = reader.readList(ClientMod.DECODER);
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeVarInt(this.id);
    writer.writeCollection(this.mods, ClientMod.ENCODER);
  }

  @Override
  public void handle(ServerMessageHandler handler) {
    handler.handle(this);
  }

  public int getId() {
    return this.id;
  }

  public Collection<ClientMod> getMods() {
    return this.mods;
  }
}
