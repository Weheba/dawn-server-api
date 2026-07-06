package gg.dawn.serverapi.messaging.messages.server;

import java.util.Collection;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;
import gg.dawn.serverapi.messaging.ServerMessageHandler;
import gg.dawn.serverapi.messaging.domain.FeatherMod;
import org.jetbrains.annotations.NotNull;

public class C2SFeatherModsResponse implements Message<ServerMessageHandler> {
  @NotNull private final Collection<FeatherMod> featherMods;

  public C2SFeatherModsResponse(@NotNull Collection<FeatherMod> featherMods) {
    this.featherMods = featherMods;
  }

  public C2SFeatherModsResponse(MessageReader reader) {
    this.featherMods = reader.readList(FeatherMod.DECODER);
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeCollection(this.featherMods, FeatherMod.ENCODER);
  }

  @Override
  public void handle(ServerMessageHandler handler) {}

  @NotNull
  public Collection<FeatherMod> getFeatherMods() {
    return this.featherMods;
  }
}
