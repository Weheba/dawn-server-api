package gg.dawn.serverapi.messaging.messages.client;

import java.util.Collection;
import gg.dawn.serverapi.messaging.ClientMessageHandler;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;
import gg.dawn.serverapi.messaging.domain.FeatherMod;

public class S2CModsAction implements Message<ClientMessageHandler> {
  private final Action action;
  private final Collection<FeatherMod> mods;

  public S2CModsAction(Action action, Collection<FeatherMod> mods) {
    this.action = action;
    this.mods = mods;
  }

  public S2CModsAction(MessageReader reader) {
    this.action = reader.readEnum(Action.class);
    this.mods = reader.readList(FeatherMod.DECODER);
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeEnum(this.action);
    writer.writeCollection(this.mods, FeatherMod.ENCODER);
  }

  @Override
  public void handle(ClientMessageHandler handler) {
    handler.handle(this);
  }

  public Action getAction() {
    return this.action;
  }

  public Collection<FeatherMod> getMods() {
    return this.mods;
  }

  public enum Action {
    BLOCK,
    UNBLOCK,
    ENABLE,
    DISABLE,
  }
}
