package me.nickdev.blockeffects.listeners;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlockManager;
import me.nickdev.blockeffects.block.TriggerType;
import me.nickdev.blockeffects.config.ConfigManager;
import me.nickdev.blockeffects.util.ItemManager;
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
        if (!configManager.getEnabledWorlds().contains(player.getWorld()) || ItemManager.isNull(block) || !blockManager.containsEBlock(block.getType())) return;
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            blockManager.tryToActivate(player, block.getType(), TriggerType.LEFT_CLICK);
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            blockManager.tryToActivate(player, block.getType(), TriggerType.RIGHT_CLICK);
        }
    }
}
