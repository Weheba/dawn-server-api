package gg.dawn.serverapi.api.model;

// Clients older than protocol 7 advertise no brand and are reported as FEATHER.
public enum ClientBrand {
  FEATHER,
  DAWN,
  DAWN_MCPVP
}
