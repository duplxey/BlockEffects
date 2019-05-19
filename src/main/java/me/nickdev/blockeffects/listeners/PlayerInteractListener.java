package me.nickdev.blockeffects.listeners;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlock;
import me.nickdev.blockeffects.block.EBlockManager;
import me.nickdev.blockeffects.block.TriggerType;
import me.nickdev.blockeffects.config.ConfigManager;
import me.nickdev.blockeffects.constants.O;
import me.nickdev.blockeffects.constants.P;
import me.nickdev.blockeffects.util.ItemManager;
import org.bukkit.ChatColor;
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
        if (blockManager.getCooldown().hasCooldown(player)) return;

        // Add the cooldown to the player
        if (configManager.isSecurityEnabled()) {
            blockManager.getCooldown().addCooldown(player);
        }

        EBlock eblock = blockManager.getEBlock(block.getType());
        if (eblock.getTriggerType() == TriggerType.RIGHT_CLICK && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (eblock.getTriggerType() == TriggerType.LEFT_CLICK && event.getAction() != Action.LEFT_CLICK_BLOCK) return;

        if (eblock.getPermission() != null && !player.hasPermission(eblock.getPermission()) && configManager.isNoPermissionEnabled()) {
            player.sendMessage(P.PREFIX + ChatColor.RED + O.NO_PERMISSION.getText());
            return;
        }

        eblock.activate(player);
    }
}
