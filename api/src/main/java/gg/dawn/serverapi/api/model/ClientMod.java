package gg.dawn.serverapi.api.model;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

// A mod loader mod on the client, as opposed to a Feather module.
public class ClientMod {

  @NotNull public final String id;
  @NotNull public final String version;

  public ClientMod(@NotNull String id, @NotNull String version) {
    this.id = id;
    this.version = version;
  }

  /**
   * Returns the mod loader id of the mod (e.g. "sodium").
   *
   * @return the mod id
   */
  @NotNull
  public String getId() {
    return this.id;
  }

  /**
   * Returns the version of the mod as reported by the mod loader.
   *
   * @return the mod version
   */
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
}
