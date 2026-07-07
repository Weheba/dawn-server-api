package gg.dawn.serverapi.messaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import gg.dawn.serverapi.messaging.messages.client.S2CClearDiscordActivity;
import gg.dawn.serverapi.messaging.messages.client.S2CCreateFUI;
import gg.dawn.serverapi.messaging.messages.client.S2CDestroyFUI;
import gg.dawn.serverapi.messaging.messages.client.S2CFUIMessage;
import gg.dawn.serverapi.messaging.messages.client.S2CFUIResponse;
import gg.dawn.serverapi.messaging.messages.client.S2CGetEnabledMods;
import gg.dawn.serverapi.messaging.messages.client.S2CHandshake;
import gg.dawn.serverapi.messaging.messages.client.S2CGetModList;
import gg.dawn.serverapi.messaging.messages.client.S2CMissPenaltyState;
import gg.dawn.serverapi.messaging.messages.client.S2CModsAction;
import gg.dawn.serverapi.messaging.messages.client.S2CServerBackground;
import gg.dawn.serverapi.messaging.messages.client.S2CSetDiscordActivity;
import gg.dawn.serverapi.messaging.messages.client.S2CSetFUIState;
import gg.dawn.serverapi.messaging.messages.client.S2CWaypointCreate;
import gg.dawn.serverapi.messaging.messages.client.S2CWaypointDestroy;
import gg.dawn.serverapi.messaging.messages.client.S2CWorldChange;
import gg.dawn.serverapi.messaging.messages.server.C2SClientHello;
import gg.dawn.serverapi.messaging.messages.server.C2SEnabledMods;
import gg.dawn.serverapi.messaging.messages.server.C2SFUILoadError;
import gg.dawn.serverapi.messaging.messages.server.C2SFUIRequest;
import gg.dawn.serverapi.messaging.messages.server.C2SFUIStateChange;
import gg.dawn.serverapi.messaging.messages.server.C2SHandshake;
import gg.dawn.serverapi.messaging.messages.server.C2SModList;
import gg.dawn.serverapi.messaging.messages.server.C2SRequestServerBackground;
import org.jetbrains.annotations.Nullable;

// Registration order is the wire id: append only, never reorder
public enum Messages {
  SERVER_BOUND(
      registry(ServerMessageHandler.class)
          .register(C2SHandshake.class, C2SHandshake::new)
          .register(C2SClientHello.class, C2SClientHello::new)
          .register(C2SFUIStateChange.class, C2SFUIStateChange::new)
          .register(C2SFUILoadError.class, C2SFUILoadError::new)
          .register(C2SFUIRequest.class, C2SFUIRequest::new)
          .register(C2SEnabledMods.class, C2SEnabledMods::new)
          .register(C2SRequestServerBackground.class, C2SRequestServerBackground::new)
          .register(C2SModList.class, C2SModList::new)),
  CLIENT_BOUND(
      registry(ClientMessageHandler.class)
          .register(S2CHandshake.class, S2CHandshake::new)
          .register(S2CCreateFUI.class, S2CCreateFUI::new)
          .register(S2CDestroyFUI.class, S2CDestroyFUI::new)
          .register(S2CSetFUIState.class, S2CSetFUIState::new)
          .register(S2CFUIMessage.class, S2CFUIMessage::new)
          .register(S2CFUIResponse.class, S2CFUIResponse::new)
          .register(S2CGetEnabledMods.class, S2CGetEnabledMods::new)
          .register(S2CModsAction.class, S2CModsAction::new)
          .register(S2CWaypointCreate.class, S2CWaypointCreate::new)
          .register(S2CWaypointDestroy.class, S2CWaypointDestroy::new)
          .register(S2CWorldChange.class, S2CWorldChange::new)
          .register(S2CServerBackground.class, S2CServerBackground::new)
          .register(S2CSetDiscordActivity.class, S2CSetDiscordActivity::new)
          .register(S2CClearDiscordActivity.class, S2CClearDiscordActivity::new)
          .register(S2CMissPenaltyState.class, S2CMissPenaltyState::new)
          .register(S2CGetModList.class, S2CGetModList::new));

  private final MessageRegistry<?> registry;

  Messages(MessageRegistry<?> registry) {
    this.registry = registry;
  }

  private static <T extends MessageHandler> MessageRegistry<T> registry(Class<T> handler) {
    return new MessageRegistry<>();
  }

  @Nullable
  public Message<?> createMessage(int messageId, MessageReader reader) {
    return registry.create(messageId, reader);
  }

  @SuppressWarnings("rawtypes")
  public int getId(Class<? extends Message> message) {
    return registry.getId(message);
  }

  private static class MessageRegistry<T extends MessageHandler> {
    private final List<Function<MessageReader, ? extends Message<T>>> idToMessageMapping =
        new ArrayList<>();
    private final Map<Class<? extends Message<T>>, Integer> messageToIdMapping = new HashMap<>();

    public <M extends Message<T>> MessageRegistry<T> register(
        Class<M> message, Function<MessageReader, M> deserializer) {
      int messageId = this.idToMessageMapping.size();
      Integer currentId = this.messageToIdMapping.putIfAbsent(message, messageId);
      if (currentId != null) {
        throw new AssertionError("Message " + message + " already registered with id " + currentId);
      }
      this.idToMessageMapping.add(deserializer);
      return this;
    }

    @Nullable
    public Message<T> create(int messageId, MessageReader reader) {
      Function<MessageReader, ? extends Message<T>> messageCreator =
          this.idToMessageMapping.get(messageId);
      return messageCreator != null ? messageCreator.apply(reader) : null;
    }

    @SuppressWarnings("rawtypes")
    public int getId(Class<? extends Message> message) {
      Integer id = this.messageToIdMapping.get(message);
      if (id == null) {
        throw new NoSuchElementException("Message " + message + " not registered");
      }
      return id;
    }
  }
}
