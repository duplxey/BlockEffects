package me.nickdev.blockeffects.block;

import me.nickdev.blockeffects.config.ConfigManager;
import me.nickdev.blockeffects.util.Cooldown;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;

public class EBlockManager {

    private HashMap<Material, EBlock> eBlocks = new HashMap<>();
    private Cooldown<Player> cooldown = new Cooldown<>();

    public EBlockManager(ConfigManager configManager) {
        // Loads all the EBlocks
        for (String blockName : configManager.getBlockSection().getKeys(false)) {
            registerEBlock(configManager.getEBlock(blockName));
        }
        cooldown.setCooldownTime(configManager.getSecurityCooldown());
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
