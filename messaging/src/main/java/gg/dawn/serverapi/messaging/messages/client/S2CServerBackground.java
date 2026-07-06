package gg.dawn.serverapi.messaging.messages.client;

import gg.dawn.serverapi.messaging.ClientMessageHandler;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;

public class S2CServerBackground implements Message<ClientMessageHandler> {

  private final Action action;
  private final byte[] data;

  public S2CServerBackground(Action action, byte[] data) {
    this.action = action;
    this.data = data;
  }

  public S2CServerBackground(MessageReader reader) {
    this.action = reader.readEnum(Action.class);
    this.data = reader.readByteArray();
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeEnum(this.action);
    writer.writeByteArray(this.data);
  }

  @Override
  public void handle(ClientMessageHandler handler) {
    handler.handle(this);
  }

  public Action getAction() {
    return this.action;
  }

  public byte[] getData() {
    return this.data;
  }

  public enum Action {
    HASH,
    DATA
  }
}
