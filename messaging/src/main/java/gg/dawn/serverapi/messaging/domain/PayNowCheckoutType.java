package gg.dawn.serverapi.messaging.domain;

// Hellos older than protocol 8 decode as UNSUPPORTED. Ordinal on the wire: append only.
public enum PayNowCheckoutType {
  UNSUPPORTED,
  IN_GAME,
  BROWSER
}
