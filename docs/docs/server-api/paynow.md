---
id: paynow
title: PayNow Checkout
sidebar_label: PayNow Checkout
description: Open a PayNow checkout on Dawn clients, with a chat link fallback for other clients
---

# PayNow Checkout

## Overview

If your store runs on [PayNow](https://paynow.gg), you can open a checkout for a player directly from the server. Dawn clients open the checkout in the player's browser. Other clients get a chat link to `https://checkout.paynow.gg/?t=<token>` instead.

## Usage

Create a checkout with the PayNow API on your backend, then pass its checkout token to `openPayNowCheckout()`:

```java
FeatherPlayer featherPlayer = FeatherAPI.getPlayerService().getPlayer(player.getUniqueId());
featherPlayer.openPayNowCheckout(checkoutToken);
```

The token must match `[A-Za-z0-9._-]{1,512}`. Otherwise an `IllegalArgumentException` is thrown.

To see how the client will open the checkout, use `getPayNowCheckoutType()`:

- `IN_GAME`: opens in an in-game overlay.
- `BROWSER`: opens in the player's system browser.
- `UNSUPPORTED`: the player gets the chat link instead.

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
