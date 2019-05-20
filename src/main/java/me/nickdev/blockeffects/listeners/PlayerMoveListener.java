package me.nickdev.blockeffects.listeners;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlockManager;
import me.nickdev.blockeffects.block.TriggerType;
import me.nickdev.blockeffects.config.ConfigManager;
import me.nickdev.blockeffects.util.ItemManager;
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

        if (!configManager.getEnabledWorlds().contains(player.getWorld()) || ItemManager.isNull(block) || !blockManager.containsEBlock(block.getType())) return;

        blockManager.tryToActivate(player, block.getType(), TriggerType.WALK);
    }
}
