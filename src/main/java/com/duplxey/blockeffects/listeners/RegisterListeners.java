package com.duplxey.blockeffects.listeners;

import com.duplxey.blockeffects.BlockEffects;
import com.duplxey.blockeffects.block.EBlockManager;
import com.duplxey.blockeffects.config.ConfigManager;

public class RegisterListeners {

    public RegisterListeners(BlockEffects blockEffects, EBlockManager blockManager, ConfigManager configManager) {
        new PlayerMoveListener(blockEffects, blockManager, configManager);
        new PlayerInteractListener(blockEffects, blockManager, configManager);
        new PlayerBlockBreakListener(blockEffects, blockManager, configManager);
        new PlayerBlockPlaceListener(blockEffects, blockManager, configManager);
    }
}
