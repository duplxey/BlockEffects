package com.duplxey.blockeffects.listeners;

import com.duplxey.blockeffects.BlockEffects;
import com.duplxey.blockeffects.block.EBlockManager;
import com.duplxey.blockeffects.block.TriggerType;
import com.duplxey.blockeffects.config.ConfigManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerBlockPlaceListener implements ListenerComponent {

    private EBlockManager blockManager;
    private ConfigManager configManager;

    public PlayerBlockPlaceListener(BlockEffects blockEffects, EBlockManager blockManager, ConfigManager configManager) {
        this.blockManager = blockManager;
        this.configManager = configManager;

        register(blockEffects);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (!configManager.getEnabledWorlds().contains(player.getWorld()) || !blockManager.isEBlock(block.getType())) return;
        blockManager.tryToActivate(player, event.getBlock().getType(), TriggerType.PLACE);
    }
}
