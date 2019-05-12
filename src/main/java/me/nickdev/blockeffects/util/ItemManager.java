package me.nickdev.blockeffects.util;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class ItemManager {

    /**
     * Return true if the block is null.
     *
     * @param block  Block
     * @return  Boolean
     */
    public static boolean isNull(Block block) {
        return block == null || block.getType() == Material.AIR;
    }
}
