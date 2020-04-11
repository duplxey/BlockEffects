package com.duplxey.blockeffects;

import com.duplxey.blockeffects.block.EBlockManager;
import com.duplxey.blockeffects.commands.RegisterCommands;
import com.duplxey.blockeffects.config.ConfigManager;
import com.duplxey.blockeffects.listeners.RegisterListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockEffects extends JavaPlugin {

    private static BlockEffects instance;
    private String version = getDescription().getVersion();

    private ConfigManager configManager;
    private EBlockManager blockManager;

    public void onEnable() {
        instance = this;

        Bukkit.getLogger().info("[BlockEffects] Preparing the configuration files.");
        saveDefaultConfig();

        // Managers
        configManager = new ConfigManager(this);
        blockManager = new EBlockManager(this, configManager);

        Bukkit.getLogger().info("[BlockEffects] Registering commands & listeners.");
        new RegisterCommands(this, blockManager);
        new RegisterListeners(this, blockManager, configManager);

        Bukkit.getLogger().info("[BlockEffects] BlockEffects v" + version + " has been enabled.");
    }

    public void onDisable() {
        instance = null;

        Bukkit.getLogger().info("[BlockEffects] BlockEffects v" + version + " has been disabled.");
    }

    public String getVersion() {
        return version;
    }

    public static BlockEffects getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public EBlockManager getBlockManager() {
        return blockManager;
    }
}
