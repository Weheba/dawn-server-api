---
id: events
title: Events
sidebar_label: Events
description: Learn how to subscribe to and handle Dawn events on your Minecraft server
sidebar_position: 2
---

import Collapse from '@site/src/components/Collapse';

# Events

Events allows your server plugin to respond to various Dawn-specific events. This enables you to detect when players join with Dawn, monitor their enabled mods, and react accordingly.

## Available Events

The Dawn Server API provides several events that your plugin can subscribe to:

<Collapse summary="PlayerHelloEvent">
Triggered when a player joins the server with Dawn.

| Method | Return Type | Description |
| ------ | ----------- | ----------- |
| `getPlatform()` | `@NotNull Platform` | Retrieves the modding platform on which Dawn is running (FORGE or FABRIC). |
| `getFeatherMods()` | `@NotNull Collection<FeatherMod>` | Retrieves a collection of Dawn mods that are enabled for the player. |

**Usage Example:**
```java
eventService.subscribe(PlayerHelloEvent.class, event -> {
    FeatherPlayer player = event.getPlayer();
    Platform platform = event.getPlatform();
    Collection<FeatherMod> mods = event.getFeatherMods();
    
    getLogger().info(player.getName() + " joined with Dawn on " + platform);
});
```
</Collapse>

## Getting Started with Events

### Accessing the Event Service

The Event Service is accessed through the `FeatherAPI`:

```java
EventService eventService = FeatherAPI.getEventService();
```

### Basic Event Subscription

To subscribe to an event, use the `subscribe()` method:

```java
EventSubscription<PlayerHelloEvent> subscription = eventService.subscribe(
    PlayerHelloEvent.class,
    event -> {
        // Handle the event
        FeatherPlayer player = event.getPlayer();
        System.out.println("Player " + player.getName() + " joined with Dawn!");
    }
);
```

### Unsubscribing from Events

When your plugin is disabled or you no longer need to listen to an event, you should unsubscribe:

```java
// Store the subscription when you subscribe
EventSubscription<PlayerHelloEvent> subscription = eventService.subscribe(
    PlayerHelloEvent.class, 
    this::handlePlayerHello
);

// Later, when you want to unsubscribe
subscription.unsubscribe();
```

## Working with Player Events

### Detecting Players with Dawn

One of the most common use cases is detecting when players join with Dawn:

```java
eventService.subscribe(PlayerHelloEvent.class, event -> {
    FeatherPlayer player = event.getPlayer();
    Platform platform = event.getPlatform();
    Collection<FeatherMod> mods = event.getFeatherMods();
    
    getLogger().info(player.getName() + " joined with Dawn on " + platform);
    getLogger().info("Enabled mods: " + mods.stream()
        .map(FeatherMod::getName)
        .collect(Collectors.joining(", ")));
});
```

### Checking for Specific Mods

You might want to check if players are using specific Dawn mods:

```java
eventService.subscribe(PlayerHelloEvent.class, event -> {
    boolean hasFps = event.getFeatherMods().stream()
        .anyMatch(mod -> mod.getName().equals("fps"));
        
    if (hasFps) {
        getLogger().info(event.getPlayer().getName() + " is using the FPS mod!");
    }
});
```