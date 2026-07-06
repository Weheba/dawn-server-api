package gg.dawn.serverapi.messaging.messages.client;

import gg.dawn.serverapi.messaging.ClientMessageHandler;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;

public class S2CMissPenaltyState implements Message<ClientMessageHandler> {
  private final boolean missPenalty;

  public S2CMissPenaltyState(boolean missPenalty) {
    this.missPenalty = missPenalty;
  }

  public S2CMissPenaltyState(MessageReader reader) {
    this.missPenalty = reader.readBool();
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeBool(this.missPenalty);
  }

  @Override
  public void handle(ClientMessageHandler handler) {
    handler.handle(this);
  }

  public boolean getMissPenalty() {
    return this.missPenalty;
  }
}
