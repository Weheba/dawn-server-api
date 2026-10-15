package gg.dawn.serverapi.api.model;

// How the client opens a PayNow checkout. Clients older than protocol 8 are reported as UNSUPPORTED.
public enum PayNowCheckoutType {
  /** The client cannot open checkouts; a chat link is sent instead. */
  UNSUPPORTED,
  /** The checkout opens in an in-game overlay. */
  IN_GAME,
  /** The checkout opens in the player's system browser. */
  BROWSER
}
