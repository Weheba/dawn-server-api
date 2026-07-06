package gg.dawn.serverapi.messaging.messages.client;

import gg.dawn.serverapi.messaging.ClientMessageHandler;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageConstants;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;

public class S2CHandshake implements Message<ClientMessageHandler> {
  private final int protocolVersion;

  public S2CHandshake() {
    this.protocolVersion = MessageConstants.VERSION;
  }

  public S2CHandshake(MessageReader reader) {
    this.protocolVersion = reader.readVarInt();
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeVarInt(this.protocolVersion);
  }

  @Override
  public void handle(ClientMessageHandler handler) {}

  public int getProtocolVersion() {
    return this.protocolVersion;
  }
}
