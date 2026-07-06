package gg.dawn.serverapi.messaging.messages.server;

import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageConstants;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;
import gg.dawn.serverapi.messaging.ServerMessageHandler;

public class C2SHandshake implements Message<ServerMessageHandler> {
  private final int protocolVersion;

  public C2SHandshake() {
    this.protocolVersion = MessageConstants.VERSION;
  }

  public C2SHandshake(MessageReader reader) {
    this.protocolVersion = reader.readVarInt();
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeVarInt(this.protocolVersion);
  }

  @Override
  public void handle(ServerMessageHandler handler) {}

  public int getProtocolVersion() {
    return protocolVersion;
  }
}
