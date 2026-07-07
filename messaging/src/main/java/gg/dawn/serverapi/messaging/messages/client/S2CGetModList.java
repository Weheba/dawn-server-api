package gg.dawn.serverapi.messaging.messages.client;

import gg.dawn.serverapi.messaging.ClientMessageHandler;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;

public class S2CGetModList implements Message<ClientMessageHandler> {
  private final int id;

  public S2CGetModList(int id) {
    this.id = id;
  }

  public S2CGetModList(MessageReader reader) {
    this.id = reader.readVarInt();
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeVarInt(this.id);
  }

  @Override
  public void handle(ClientMessageHandler handler) {
    handler.handle(this);
  }

  public int getId() {
    return this.id;
  }
}
