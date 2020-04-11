package com.duplxey.blockeffects.commands;

import com.duplxey.blockeffects.BlockEffects;
import com.duplxey.blockeffects.block.EBlockManager;

public class RegisterCommands {

    public RegisterCommands(BlockEffects blockEffects, EBlockManager blockManager) {
        blockEffects.getCommand("blockeffects").setExecutor(new DefaultCommand(blockEffects, blockManager));
    }
}
