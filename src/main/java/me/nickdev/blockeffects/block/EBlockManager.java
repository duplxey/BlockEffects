package me.nickdev.blockeffects.block;

import me.nickdev.blockeffects.config.ConfigManager;
import me.nickdev.blockeffects.constants.O;
import me.nickdev.blockeffects.constants.P;
import me.nickdev.blockeffects.util.Cooldown;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;

public class EBlockManager {

    private ConfigManager configManager;

    private HashMap<Material, EBlock> eBlocks = new HashMap<>();
    private Cooldown<Player> cooldown = new Cooldown<>();

    public EBlockManager(ConfigManager configManager) {
        this.configManager = configManager;

        // Loads all the EBlocks
        for (String blockName : configManager.getBlockSection().getKeys(false)) {
            registerEBlock(configManager.getEBlock(blockName));
        }
        cooldown.setCooldownTime(configManager.getSecurityCooldown());
    }

    public boolean tryToActivate(Player player, Material type, TriggerType triggerType) {
        if (cooldown.hasCooldown(player)) return false;
        if (configManager.isSecurityEnabled()) {
            cooldown.addCooldown(player);
        }
        EBlock eblock = getEBlock(type);
        if (eblock.getTriggerType() != triggerType) return false;
        if (eblock.getPermission() != null && !player.hasPermission(eblock.getPermission()) && configManager.isNoPermissionEnabled()) {
            player.sendMessage(P.PREFIX + ChatColor.RED + O.NO_PERMISSION.getText());
            return false;
        }
        eblock.activate(player);
        return true;
    }

    private void registerEBlock(EBlock eBlock) {
        eBlocks.put(eBlock.getMaterial(), eBlock);
    }

    public boolean containsEBlock(Material material) {
        return eBlocks.keySet().contains(material);
    }

    public EBlock getEBlock(Material material) {
        return eBlocks.get(material);
    }

    public Collection<EBlock> getEBlocks() {
        return eBlocks.values();
    }

    public Cooldown<Player> getCooldown() {
        return cooldown;
    }
}
