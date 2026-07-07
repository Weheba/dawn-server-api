package gg.dawn.serverapi.messaging.domain;

import java.util.Objects;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;

// A mod loader mod on the client, as opposed to a Feather module.
public class ClientMod {
  @Internal public static final MessageReader.Decoder<ClientMod> DECODER = new Decoder();
  @Internal public static final MessageWriter.Encoder<ClientMod> ENCODER = new Encoder();

  @NotNull public final String id;
  @NotNull public final String version;

  public ClientMod(@NotNull String id, @NotNull String version) {
    this.id = id;
    this.version = version;
  }

  @NotNull
  public String getId() {
    return this.id;
  }

  @NotNull
  public String getVersion() {
    return this.version;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    ClientMod that = (ClientMod) other;
    return this.id.equals(that.id) && this.version.equals(that.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.version);
  }

  @Internal
  private static class Decoder implements MessageReader.Decoder<ClientMod> {

    @Override
    public ClientMod apply(MessageReader reader) {
      String id = reader.readUtf(128);
      String version = reader.readUtf(64);
      return new ClientMod(id, version);
    }
  }

  @Internal
  private static class Encoder implements MessageWriter.Encoder<ClientMod> {

    @Override
    public void accept(MessageWriter writer, ClientMod mod) {
      writer.writeUtf(mod.getId());
      writer.writeUtf(mod.getVersion());
    }
  }
}
