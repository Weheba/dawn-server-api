package gg.dawn.serverapi.messaging.messages.client;

import gg.dawn.serverapi.messaging.ClientMessageHandler;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;

public class S2CClearDiscordActivity implements Message<ClientMessageHandler> {

  public S2CClearDiscordActivity() {}

  public S2CClearDiscordActivity(MessageReader reader) {}

  @Override
  public void write(MessageWriter writer) {}

  @Override
  public void handle(ClientMessageHandler handler) {
    handler.handle(this);
  }
}
