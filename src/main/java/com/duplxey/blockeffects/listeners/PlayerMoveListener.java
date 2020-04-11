package com.duplxey.blockeffects.listeners;

import com.duplxey.blockeffects.BlockEffects;
import com.duplxey.blockeffects.block.EBlockManager;
import com.duplxey.blockeffects.block.TriggerType;
import com.duplxey.blockeffects.config.ConfigManager;
import com.duplxey.blockeffects.util.ItemManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements ListenerComponent {

    private EBlockManager blockManager;
    private ConfigManager configManager;

    public PlayerMoveListener(BlockEffects blockEffects, EBlockManager blockManager, ConfigManager configManager) {
        this.blockManager = blockManager;
        this.configManager = configManager;

        register(blockEffects);
    }

    @EventHandler
    public void onMove( PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Block block = player.getLocation().subtract(0.0, 1.0, 0.0).getBlock();

        if (!configManager.getEnabledWorlds().contains(player.getWorld()) || ItemManager.isNull(block) || !blockManager.isEBlock(block.getType())) return;
        blockManager.tryToActivate(player, block.getType(), TriggerType.WALK);
    }
}
