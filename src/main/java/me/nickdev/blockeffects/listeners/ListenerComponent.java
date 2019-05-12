package me.nickdev.blockeffects.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public interface ListenerComponent extends Listener {
    /**
     * Registers the listener.
     *
     * @param plugin  JavaPlugin child.
     */
    default void register(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
