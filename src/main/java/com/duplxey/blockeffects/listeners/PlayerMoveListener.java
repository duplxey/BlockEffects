package com.duplxey.blockeffects.listeners;

import com.duplxey.blockeffects.BlockEffects;
import com.duplxey.blockeffects.block.EBlock;
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
        // Current block is for checking if the player is in a fluid or cobweb or any other pass through block
        Block currentBlock = player.getLocation().getBlock();
        // Under block is the block the player stands on.
        Block underBlock = player.getLocation().subtract(0.0, 1.0, 0.0).getBlock();

        if (!configManager.getEnabledWorlds().contains(player.getWorld())) return;


        if (!ItemManager.isNull(currentBlock) && blockManager.isEBlock(currentBlock.getType())) {
            EBlock eBlock = blockManager.getEBlock(currentBlock.getType());
            if (eBlock.getTriggerType() == TriggerType.WALK_INSIDE) {
                blockManager.tryToActivate(player, currentBlock.getType(), TriggerType.WALK_INSIDE);
            }
        }
        if (!ItemManager.isNull(underBlock) && blockManager.isEBlock(underBlock.getType())) {
            EBlock eBlock = blockManager.getEBlock(underBlock.getType());
            if (eBlock.getTriggerType() == TriggerType.WALK) {
                blockManager.tryToActivate(player, underBlock.getType(), TriggerType.WALK);
            }
        }
    }
}
