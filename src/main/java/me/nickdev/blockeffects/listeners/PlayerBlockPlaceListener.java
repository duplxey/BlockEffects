package me.nickdev.blockeffects.listeners;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlockManager;
import me.nickdev.blockeffects.block.TriggerType;
import me.nickdev.blockeffects.config.ConfigManager;
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

        if (!configManager.getEnabledWorlds().contains(player.getWorld()) || !blockManager.containsEBlock(block.getType())) return;
        blockManager.tryToActivate(player, event.getBlock().getType(), TriggerType.PLACE);
    }
}
