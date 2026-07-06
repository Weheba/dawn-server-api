package gg.dawn.serverapi.messaging;

import gg.dawn.serverapi.messaging.messages.client.S2CClearDiscordActivity;
import gg.dawn.serverapi.messaging.messages.client.S2CCreateFUI;
import gg.dawn.serverapi.messaging.messages.client.S2CDestroyFUI;
import gg.dawn.serverapi.messaging.messages.client.S2CFUIMessage;
import gg.dawn.serverapi.messaging.messages.client.S2CFUIResponse;
import gg.dawn.serverapi.messaging.messages.client.S2CGetEnabledMods;
import gg.dawn.serverapi.messaging.messages.client.S2CMissPenaltyState;
import gg.dawn.serverapi.messaging.messages.client.S2CModsAction;
import gg.dawn.serverapi.messaging.messages.client.S2CServerBackground;
import gg.dawn.serverapi.messaging.messages.client.S2CSetDiscordActivity;
import gg.dawn.serverapi.messaging.messages.client.S2CSetFUIState;
import gg.dawn.serverapi.messaging.messages.client.S2CWaypointCreate;
import gg.dawn.serverapi.messaging.messages.client.S2CWaypointDestroy;
import gg.dawn.serverapi.messaging.messages.client.S2CWorldChange;

public interface ClientMessageHandler extends MessageHandler {
  void handle(S2CCreateFUI createFUI);

  void handle(S2CDestroyFUI destroyFUI);

  void handle(S2CSetFUIState setFUIState);

  void handle(S2CFUIMessage message);

  void handle(S2CFUIResponse response);

  void handle(S2CGetEnabledMods getEnabledMods);

  void handle(S2CModsAction blockMods);

  void handle(S2CServerBackground serverBackground);

  void handle(S2CWaypointCreate waypointCreate);

  void handle(S2CWaypointDestroy waypointDestroy);

  void handle(S2CWorldChange worldChange);

  void handle(S2CSetDiscordActivity setDiscordActivity);

  void handle(S2CClearDiscordActivity clearDiscordActivity);

  void handle(S2CMissPenaltyState missPenaltyState);
}
