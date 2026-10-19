package gg.dawn.serverapi.api.player;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import gg.dawn.serverapi.api.model.ClientBrand;
import gg.dawn.serverapi.api.model.PayNowCheckoutType;
import gg.dawn.serverapi.api.model.ClientMod;
import gg.dawn.serverapi.api.model.FeatherMod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Represents a player using Feather */
public interface FeatherPlayer {

  /**
   * Returns the player's Minecraft UUID.
   *
   * @return The UUID of the player.
   */
  @NotNull
  UUID getUniqueId();

  /**
   * Returns the name of the player.
   *
   * @return The name of the player.
   */
  @NotNull
  String getName();

  /**
   * Blocks and disables the specified mods for the player, preventing them from being enabled.
   * Blocked mods cannot be enabled by the player or through the {@link #enableMods(Collection)}
   * method.
   *
   * @param mods The collection of {@link FeatherMod} instances to be blocked.
   */
  void blockMods(@NotNull Collection<@NotNull FeatherMod> mods);

  /**
   * Unblocks the specified mods for the player.
   *
   * @param mods The collection of mods to be unblocked.
   */
  void unblockMods(@NotNull Collection<@NotNull FeatherMod> mods);

  /**
   * Returns the collection of mods that are currently blocked for the player.
   *
   * @return A CompletableFuture that resolves to a collection of blocked mods.
   */
  CompletableFuture<@NotNull Collection<@NotNull FeatherMod>> getBlockedMods();

  /**
   * Enables the specified mods for the player.
   *
   * @param mods The collection of mods to be enabled.
   */
  void enableMods(@NotNull Collection<@NotNull FeatherMod> mods);

  /**
   * Disables the specified mods for the player.
   *
   * @param mods The collection of mods to be disabled.
   */
  void disableMods(@NotNull Collection<@NotNull FeatherMod> mods);

  /**
   * Requests a list of enabled mods from the player.
   *
   * @return A {@link CompletableFuture} that resolves to a collection of enabled mods.
   */
  @NotNull
  CompletableFuture<@NotNull Collection<@NotNull FeatherMod>> getEnabledMods();

  /**
   * Sets whether to bypass the miss penalty feature.
   *
   * <p>In vanilla Minecraft, miss penalty is enabled by default in survival mode, preventing
   * players from attacking for a short duration after missing. This setting determines whether
   * players can attack immediately after missing or must wait for the vanilla cooldown period.
   *
   * @param bypass true to bypass the miss penalty, false to use vanilla cooldown behavior
   * @since 0.0.5
   */
  void bypassMissPenalty(boolean bypass);

  /**
   * Returns the client family. Self-declared and spoofable; not for security decisions.
   *
   * @return the client brand
   * @since 0.0.6
   */
  @NotNull
  ClientBrand getClientBrand();

  /**
   * Returns the advertised client version, or {@code null} for pre-protocol-7 clients.
   *
   * @return the client version, or null if unknown
   * @since 0.0.6
   */
  @Nullable
  String getClientVersion();

  /**
   * Requests the mod loader mod list (not Feather modules). Times out exceptionally for
   * pre-protocol-7 clients.
   *
   * @return A {@link CompletableFuture} that resolves to the client's mod list.
   * @since 0.0.6
   */
  @NotNull
  CompletableFuture<@NotNull Collection<@NotNull ClientMod>> getClientMods();

  /**
   * Full-state sync of the client's active post effects: namespaced ids of
   * post_effect JSONs in the active resource packs, applied in list order.
   * An empty list clears all effects.
   * Rendered on Dawn clients on Minecraft 1.21.6+, ignored elsewhere.
   *
   * @param effectIds The ordered post effect ids, at most 16, each at most 256 characters.
   * @throws IllegalArgumentException if the limits are exceeded
   * @since 0.0.8
   */
  void setPostEffects(@NotNull List<@NotNull String> effectIds);

  /**
   * Hides or restores the client HUD, equivalent to the F1 toggle. The client
   * restores the HUD on disconnect. 
   * It is not enforcable, i.e. user can still press F1 to enable the hud again.
   *
   * @param hidden true to hide the HUD
   * @since 0.0.8
   */
  void setHudHidden(boolean hidden);

  /**
   * How this client opens a PayNow checkout via {@link #openPayNowCheckout(String)}.
   * {@link PayNowCheckoutType#UNSUPPORTED} means the chat link fallback is used.
   *
   * @since 0.0.9
   */
  @NotNull
  PayNowCheckoutType getPayNowCheckoutType();

  /**
   * Opens the PayNow checkout for the given checkout token. Clients whose
   * {@link #getPayNowCheckoutType()} is {@link PayNowCheckoutType#UNSUPPORTED} receive a chat link to {@code https://checkout.paynow.gg/?t=<token>} instead.
   * Only call this in response to a player action (command, click, GUI button).
   *
   * @param checkoutToken The PayNow checkout token, matching {@code [A-Za-z0-9._-]{1,512}}.
   * @throws IllegalArgumentException if the token is malformed
   * @since 0.0.9
   */
  void openPayNowCheckout(@NotNull String checkoutToken);
}
