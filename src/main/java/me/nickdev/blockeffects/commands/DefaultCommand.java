package me.nickdev.blockeffects.commands;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlock;
import me.nickdev.blockeffects.block.EBlockManager;
import me.nickdev.blockeffects.constants.CC;
import me.nickdev.blockeffects.constants.O;
import me.nickdev.blockeffects.constants.P;
import me.nickdev.blockeffects.util.StringManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DefaultCommand implements CommandExecutor {

    private String version;
    private EBlockManager blockManager;

    public DefaultCommand(BlockEffects blockEffects, EBlockManager blockManager) {
        version = blockEffects.getVersion();
        this.blockManager = blockManager;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                sendHelp(sender);
                return true;
            }
            if (args[0].equalsIgnoreCase("blocks")) {
                if (!sender.hasPermission("blockeffects.blocks")) {
                    sender.sendMessage(P.PREFIX + O.NO_PERMISSION.getText());
                    return true;
                }
                sender.sendMessage(P.PREFIX + ChatColor.GRAY + "The following blocks are registered:");
                for (EBlock block : blockManager.getEBlocks()) {
                    sender.sendMessage("----------------");
                    for (String line : block.info()) {
                        sender.sendMessage(ChatColor.GRAY + StringManager.color(line));
                    }
                }
                sender.sendMessage("----------------");
                return true;
            }
        }
        sender.sendMessage(P.PREFIX + O.WRONG_SYNTAX.getText());
        return true;
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(P.PREFIX + CC.SPECIAL + "BlockEffects v" + version + CC.NORMAL + " coded by NickDEV.");
        sender.sendMessage(CC.NORMAL + "To see the registered blocks use: " + CC.VALUE + "/be blocks");
    }
}
