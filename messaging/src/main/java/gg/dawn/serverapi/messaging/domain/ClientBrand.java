package gg.dawn.serverapi.messaging.domain;

// Legacy hellos decode as FEATHER. Ordinal on the wire: append only.
public enum ClientBrand {
  FEATHER,
  DAWN,
  DAWN_MCPVP
}
