package me.nickdev.blockeffects.commands;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlockManager;

public class RegisterCommands {

    public RegisterCommands(BlockEffects blockEffects, EBlockManager blockManager) {
        blockEffects.getCommand("blockeffects").setExecutor(new DefaultCommand(blockEffects, blockManager));
    }
}
