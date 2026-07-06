package gg.dawn.serverapi.bukkit.event;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.Consumer;
import gg.dawn.serverapi.api.event.EventService;
import gg.dawn.serverapi.api.event.EventSubscription;
import gg.dawn.serverapi.api.event.FeatherEvent;
import gg.dawn.serverapi.api.event.player.PlayerHelloEvent;
import gg.dawn.serverapi.bukkit.FeatherBukkitPlugin;
import gg.dawn.serverapi.bukkit.event.player.BukkitPlayerHelloEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class BukkitEventService implements EventService {
  private static final EventPriority DEFAULT_PRIORITY = EventPriority.NORMAL;

  private static final Map<Class<? extends FeatherEvent>, Class<? extends BukkitFeatherEvent>>
      MAPPING =
          ImmutableMap.<Class<? extends FeatherEvent>, Class<? extends BukkitFeatherEvent>>builder()
              .put(PlayerHelloEvent.class, BukkitPlayerHelloEvent.class)
              .build();

  private final FeatherBukkitPlugin plugin;

  public BukkitEventService(FeatherBukkitPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public @NotNull <T extends FeatherEvent> EventSubscription<T> subscribe(
      @NotNull Class<T> eventClazz, @NotNull Consumer<? super T> handler) {
    return subscribe(eventClazz, handler, this.plugin);
  }

  @SuppressWarnings("unchecked")
  @Override
  public @NotNull <T extends FeatherEvent> EventSubscription<T> subscribe(
      @NotNull Class<T> eventClazz, @NotNull Consumer<? super T> handler, @NotNull Object plugin) {
    if (!(plugin instanceof Plugin)) {
      throw new IllegalArgumentException("Invalid plugin object");
    }

    Class<? extends BukkitFeatherEvent> bukkitEventClass = MAPPING.get(eventClazz);
    if (bukkitEventClass == null) {
      throw new UnsupportedOperationException("Event not implemented");
    }

    BukkitEventSubscription<T> subscription =
        new BukkitEventSubscription<>((Class<T>) bukkitEventClass, handler);

    Bukkit.getPluginManager()
        .registerEvent(
            bukkitEventClass,
            subscription,
            DEFAULT_PRIORITY,
            (listener, event) -> {
              try {
                handler.accept((T) event);
              } catch (Throwable throwable) {
                throwable.printStackTrace();
              }
            },
            (Plugin) plugin,
            true);

    return subscription;
  }
}
