package com.duplxey.blockeffects.listeners;

import com.duplxey.blockeffects.BlockEffects;
import com.duplxey.blockeffects.block.EBlockManager;
import com.duplxey.blockeffects.block.TriggerType;
import com.duplxey.blockeffects.config.ConfigManager;
import com.duplxey.blockeffects.util.ItemManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements ListenerComponent {

    private EBlockManager blockManager;
    private ConfigManager configManager;

    public PlayerInteractListener(BlockEffects blockEffects, EBlockManager blockManager, ConfigManager configManager) {
        this.blockManager = blockManager;
        this.configManager = configManager;

        register(blockEffects);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (block == null) return;
        if (!configManager.getEnabledWorlds().contains(player.getWorld()) || ItemManager.isNull(block) || !blockManager.isEBlock(block.getType())) return;
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            blockManager.tryToActivate(player, block.getType(), TriggerType.LEFT_CLICK);
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            blockManager.tryToActivate(player, block.getType(), TriggerType.RIGHT_CLICK);
        }
    }
}
