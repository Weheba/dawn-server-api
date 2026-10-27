package gg.dawn.serverapi.messaging;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.Collections;
import gg.dawn.serverapi.messaging.domain.ClientBrand;
import gg.dawn.serverapi.messaging.domain.PayNowCheckoutType;
import gg.dawn.serverapi.messaging.domain.Platform;
import gg.dawn.serverapi.messaging.messages.server.C2SClientHello;
import org.junit.jupiter.api.Test;

class ClientHelloCompatTest {
  private static C2SClientHello decode(byte[] bytes) {
    return assertInstanceOf(C2SClientHello.class, MessageDecoder.SERVER_BOUND.decode(bytes));
  }

  // A protocol 7 (0.0.8) client writes no PayNow field.
  private static byte[] protocol7Hello(ClientBrand brand, String version) {
    SimpleMessageBuffer buffer = new SimpleMessageBuffer();
    buffer.writeVarInt(Messages.SERVER_BOUND.getId(C2SClientHello.class));
    buffer.writeEnum(Platform.FABRIC);
    buffer.writeCollection(Collections.emptyList(), (w, m) -> {});
    buffer.writeEnum(brand);
    buffer.writeUtf(version);
    return buffer.toByteArray();
  }

  @Test
  void protocol7HelloDecodesAsUnsupported() {
    C2SClientHello hello = decode(protocol7Hello(ClientBrand.values()[0], "1.2.3"));
    assertEquals(PayNowCheckoutType.UNSUPPORTED, hello.getPayNowCheckoutType());
    assertEquals("1.2.3", hello.getClientVersion());
  }

  @Test
  void fourArgumentConstructorAdvertisesNoPayNow() {
    C2SClientHello hello =
        new C2SClientHello(Platform.FABRIC, Collections.emptyList(), ClientBrand.values()[0], "1.2.3");
    assertEquals(PayNowCheckoutType.UNSUPPORTED, hello.getPayNowCheckoutType());
    C2SClientHello decoded = decode(MessageEncoder.SERVER_BOUND.encode(hello));
    assertEquals(PayNowCheckoutType.UNSUPPORTED, decoded.getPayNowCheckoutType());
  }

  @Test
  void protocol8HelloKeepsProtocol7Prefix() {
    ClientBrand brand = ClientBrand.values()[0];
    byte[] v8 =
        MessageEncoder.SERVER_BOUND.encode(
            new C2SClientHello(
                Platform.FABRIC, Collections.emptyList(), brand, "1.2.3", PayNowCheckoutType.BROWSER));
    byte[] v7 = protocol7Hello(brand, "1.2.3");
    // An old plugin reads exactly the protocol 7 fields and ignores the trailing PayNow byte.
    byte[] prefix = new byte[v7.length];
    System.arraycopy(v8, 0, prefix, 0, v7.length);
    assertArrayEquals(v7, prefix);
    assertEquals(v7.length + 1, v8.length);
    assertEquals(PayNowCheckoutType.BROWSER, decode(v8).getPayNowCheckoutType());
  }

  @Test
  void unknownPayNowTypeDecodesAsUnsupported() {
    byte[] v7 = protocol7Hello(ClientBrand.values()[0], "1.2.3");
    byte[] future = new byte[v7.length + 1];
    System.arraycopy(v7, 0, future, 0, v7.length);
    future[v7.length] = 42;
    assertEquals(PayNowCheckoutType.UNSUPPORTED, decode(future).getPayNowCheckoutType());
  }
}
