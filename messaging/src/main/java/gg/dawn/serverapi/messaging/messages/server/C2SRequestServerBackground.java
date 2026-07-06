package gg.dawn.serverapi.messaging.messages.server;

import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;
import gg.dawn.serverapi.messaging.ServerMessageHandler;

public class C2SRequestServerBackground implements Message<ServerMessageHandler> {

  public C2SRequestServerBackground() {}

  public C2SRequestServerBackground(MessageReader reader) {}

  @Override
  public void write(MessageWriter writer) {}

  @Override
  public void handle(ServerMessageHandler handler) {
    handler.handle(this);
  }
}
