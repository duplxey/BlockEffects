package me.nickdev.blockeffects.listeners;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlockManager;
import me.nickdev.blockeffects.config.ConfigManager;

public class RegisterListeners {

    public RegisterListeners(BlockEffects blockEffects, EBlockManager blockManager, ConfigManager configManager) {
        new PlayerMoveListener(blockEffects, blockManager, configManager);
        new PlayerInteractListener();
    }
}
