package gg.dawn.serverapi.messaging.messages.client;

import java.util.ArrayList;
import java.util.List;
import gg.dawn.serverapi.messaging.ClientMessageHandler;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;
import gg.dawn.serverapi.messaging.exception.MessageException;

/**
 * Full-state sync of the client's active post effects: namespaced ids of
 * post_effect JSONs in the active resource packs, applied in list order.
 * An empty list clears all effects.
 */
public class S2CPostEffects implements Message<ClientMessageHandler> {
  public static final int MAX_EFFECTS = 16;
  public static final int MAX_ID_LENGTH = 256;

  private final List<String> effects;

  public S2CPostEffects(List<String> effects) {
    if (effects.size() > MAX_EFFECTS) {
      throw new IllegalArgumentException("Too many post effects (" + effects.size() + ")");
    }
    for (String effect : effects) {
      if (effect == null || effect.length() > MAX_ID_LENGTH) {
        throw new IllegalArgumentException("Invalid post effect id: " + effect);
      }
    }
    this.effects = effects;
  }

  public S2CPostEffects(MessageReader reader) {
    int size = reader.readVarInt();
    if (size < 0 || size > MAX_EFFECTS) {
      throw new MessageException("Post effect count out of range (" + size + ")");
    }
    List<String> effects = new ArrayList<>(size);
    for (int iii = 0; iii < size; iii++) {
      effects.add(reader.readUtf(MAX_ID_LENGTH));
    }
    this.effects = effects;
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeCollection(this.effects, MessageWriter::writeUtf);
  }

  @Override
  public void handle(ClientMessageHandler handler) {
    handler.handle(this);
  }

  public List<String> getEffects() {
    return this.effects;
  }
}
