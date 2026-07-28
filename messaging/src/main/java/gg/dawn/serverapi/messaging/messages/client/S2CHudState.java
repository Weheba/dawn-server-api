package gg.dawn.serverapi.messaging.messages.client;

import gg.dawn.serverapi.messaging.ClientMessageHandler;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;

/**
 * Hides or restores the client HUD, equivalent to the F1 toggle. The client
 * restores the HUD on disconnect. 
 * It is not enforcable, i.e. user can still press F1 to enable the hud again.
 */
public class S2CHudState implements Message<ClientMessageHandler> {
  private final boolean hidden;

  public S2CHudState(boolean hidden) {
    this.hidden = hidden;
  }

  public S2CHudState(MessageReader reader) {
    this.hidden = reader.readBool();
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeBool(this.hidden);
  }

  @Override
  public void handle(ClientMessageHandler handler) {
    handler.handle(this);
  }

  public boolean isHidden() {
    return this.hidden;
  }
}
