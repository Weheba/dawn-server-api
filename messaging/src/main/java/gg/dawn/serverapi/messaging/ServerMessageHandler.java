package gg.dawn.serverapi.messaging;

import gg.dawn.serverapi.messaging.messages.server.C2SEnabledMods;
import gg.dawn.serverapi.messaging.messages.server.C2SFUILoadError;
import gg.dawn.serverapi.messaging.messages.server.C2SFUIRequest;
import gg.dawn.serverapi.messaging.messages.server.C2SFUIStateChange;
import gg.dawn.serverapi.messaging.messages.server.C2SRequestServerBackground;

public interface ServerMessageHandler extends MessageHandler {
  void handle(C2SFUIRequest request);

  void handle(C2SFUIStateChange stateChange);

  void handle(C2SFUILoadError loadError);

  void handle(C2SEnabledMods enabledMods);

  void handle(C2SRequestServerBackground serverBackground);
}
