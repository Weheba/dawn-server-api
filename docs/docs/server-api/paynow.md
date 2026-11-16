---
id: paynow
title: PayNow Checkout
sidebar_label: PayNow Checkout
description: Open a PayNow checkout on Dawn clients, with a chat link fallback for other clients
---

# PayNow Checkout

## Overview

If your store runs on [PayNow](https://paynow.gg), you can open a checkout for a player directly from the server. Dawn clients open the checkout in an in-game overlay or the player's browser. Other clients get a chat link to `https://checkout.paynow.gg/?t=<token>` instead.

## Usage

Requires Server API `0.0.10` or newer.

Create a checkout with the PayNow API on your backend, then pass its checkout token to `openPayNowCheckout()`:

```java
FeatherPlayer featherPlayer = FeatherAPI.getPlayerService().getPlayer(player.getUniqueId());
featherPlayer.openPayNowCheckout(checkoutToken);
```

The token must match `[A-Za-z0-9._-]{1,512}`. Otherwise an `IllegalArgumentException` is thrown.

To see how the client will open the checkout, use `getPayNowCheckoutType()`:

- `IN_GAME`: opens in an in-game overlay. PayPal and Google Pay open in a separate window, and the player can switch to their browser at any time.
- `BROWSER`: opens in the player's system browser, for example on macOS.
- `UNSUPPORTED`: older clients get the chat link instead.

```java
if (featherPlayer.getPayNowCheckoutType() == PayNowCheckoutType.UNSUPPORTED) {
    // e.g. tell the player to click the link in chat
}
```

## Guidelines

:::warning
Only open a checkout **in response to a player action**, such as a command, a click, or a GUI button. Never open a checkout unprompted, for example on join, on a timer, or after a game ends.
:::

- Confirm purchases on your backend with PayNow webhooks. The client does not report whether the checkout succeeded.
- Only one checkout can be open at a time. After one opens or closes, new checkouts are ignored for 5 seconds.
- The overlay only opens while the player is in game, in chat, or in a container GUI. Otherwise the call is ignored, so open it from the click handler, not after closing the menu yourself.
- If the overlay can't load, the client opens the checkout in the browser instead.
- Dawn can turn off checkouts on a server. Contact the Dawn team if you need that.
