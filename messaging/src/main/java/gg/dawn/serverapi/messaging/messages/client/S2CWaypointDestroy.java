package gg.dawn.serverapi.messaging.messages.client;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import gg.dawn.serverapi.messaging.ClientMessageHandler;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;

public class S2CWaypointDestroy implements Message<ClientMessageHandler> {
  private final Collection<UUID> ids;

  public S2CWaypointDestroy(UUID id) {
    this(Collections.singletonList(id));
  }

  public S2CWaypointDestroy(Collection<UUID> ids) {
    this.ids = ids;
  }

  public S2CWaypointDestroy(MessageReader reader) {
    this.ids = reader.readList(MessageReader::readUUID);
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeCollection(this.ids, MessageWriter::writeUUID);
  }

  @Override
  public void handle(ClientMessageHandler handler) {
    handler.handle(this);
  }

  public Collection<UUID> getIds() {
    return this.ids;
  }
}
