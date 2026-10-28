---
id: paynow
title: PayNow Checkout
sidebar_label: PayNow Checkout
description: Open a PayNow checkout for a player in game, with a browser fallback.
---

# PayNow Checkout

If your store runs on [PayNow](https://paynow.gg), open a checkout for a player straight from your server. Needs Dawn Server API `0.0.9` or newer.

```java
FeatherPlayer featherPlayer = FeatherAPI.getPlayerService().getPlayer(player.getUniqueId());
featherPlayer.openPayNowCheckout(checkoutToken);
```

## Checkout token

Create a checkout with the [PayNow API](https://docs.paynow.gg) on your backend and pass the `token` it returns. The token must match `[A-Za-z0-9._-]{1,512}`, otherwise `openPayNowCheckout` throws an `IllegalArgumentException`.

## Behavior

- **Windows and Linux:** the checkout opens in game over the current screen, with an **Open in Browser** button.
- **macOS:** the checkout opens in the system browser. The same happens on any platform if the in-game browser isn't available or PayNow fails to load.
- **Clients that can't open checkouts** (older versions): the player gets a chat link to `https://checkout.paynow.gg/?t=<token>`.
- `getPayNowCheckoutType()` returns `IN_GAME`, `BROWSER` or `UNSUPPORTED` (chat link).
- Nothing is returned and no event fires. Confirm purchases on your backend with PayNow webhooks.

:::warning
Only open a checkout in response to a player action, such as a command, a click or a GUI button. Never open one on join, on a timer or after a game ends.
:::
