package me.nickdev.blockeffects.block;

import me.nickdev.blockeffects.config.ConfigManager;
import org.bukkit.Material;

import java.util.Collection;
import java.util.HashMap;

public class EBlockManager {

    private static HashMap<Material, EBlock> eBlocks = new HashMap<>();

    public EBlockManager(ConfigManager configManager) {
        // Loads all the EBlocks
        for (String blockName : configManager.getBlockSection().getKeys(false)) {
            registerEBlock(configManager.getEBlock(blockName));
        }
    }

    private void registerEBlock(EBlock eBlock) {
        EBlockManager.eBlocks.put(eBlock.getMaterial(), eBlock);
    }

    public boolean containsEBlock(Material material) {
        return EBlockManager.eBlocks.keySet().contains(material);
    }

    public EBlock getEBlock(Material material) {
        return EBlockManager.eBlocks.get(material);
    }

    public Collection<EBlock> getEBlocks() {
        return EBlockManager.eBlocks.values();
    }
}
