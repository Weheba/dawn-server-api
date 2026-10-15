package gg.dawn.serverapi.messaging.messages.client;

import gg.dawn.serverapi.messaging.ClientMessageHandler;
import gg.dawn.serverapi.messaging.Message;
import gg.dawn.serverapi.messaging.MessageReader;
import gg.dawn.serverapi.messaging.MessageWriter;

/**
 * Opens the PayNow checkout for the given checkout token on the client.
 * Must only be sent in response to a player action.
 */
public class S2CPayNowCheckout implements Message<ClientMessageHandler> {
  public static final int MAX_TOKEN_LENGTH = 512;

  private final String checkoutToken;

  public S2CPayNowCheckout(String checkoutToken) {
    this.checkoutToken = checkoutToken;
  }

  public S2CPayNowCheckout(MessageReader reader) {
    this.checkoutToken = reader.readUtf(MAX_TOKEN_LENGTH);
  }

  @Override
  public void write(MessageWriter writer) {
    writer.writeUtf(this.checkoutToken, MAX_TOKEN_LENGTH);
  }

  @Override
  public void handle(ClientMessageHandler handler) {
    handler.handle(this);
  }

  public String getCheckoutToken() {
    return this.checkoutToken;
  }
}
