package gg.dawn.serverapi.messaging.messages.client;

import java.util.UUID;
import gg.dawn.serverapi.messaging.ClientMessageHandler;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;

public class S2CWorldChange implements Message<ClientMessageHandler> {

  private final UUID worldId;

  public S2CWorldChange(UUID worldId) {
    this.worldId = worldId;
  }

  public S2CWorldChange(MessageReader reader) {
    this.worldId = reader.readUUID();
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeUUID(this.worldId);
  }

  @Override
  public void handle(ClientMessageHandler handler) {
    handler.handle(this);
  }

  public UUID getWorldId() {
    return this.worldId;
  }
}
