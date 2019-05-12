package me.nickdev.blockeffects.listeners;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlock;
import me.nickdev.blockeffects.block.EBlockManager;
import me.nickdev.blockeffects.config.ConfigManager;
import me.nickdev.blockeffects.constants.O;
import me.nickdev.blockeffects.constants.P;
import me.nickdev.blockeffects.util.ItemManager;
import org.bukkit.ChatColor;
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
        if (blockManager.getCooldown().hasCooldown(player)) return;

        EBlock eblock = blockManager.getEBlock(block.getType());

        if (eblock.getPermission() != null && !player.hasPermission(eblock.getPermission()) && configManager.isNoPermissionEnabled()) {
            player.sendMessage(P.PREFIX + ChatColor.RED + O.NO_PERMISSION.getText());
            return;
        }

        eblock.activate(player);

        // Add the cooldown to the player
        if (configManager.isSecurityEnabled()) {
            blockManager.getCooldown().addCooldown(player);
        }
    }
}
