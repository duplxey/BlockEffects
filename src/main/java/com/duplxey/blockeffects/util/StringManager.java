package com.duplxey.blockeffects.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class StringManager {

    /**
     * Colors the message.
     *
     * @param str  Text
     * @return  Colored text
     */
    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    /**
     * Colors the messages.
     *
     * @param messages  Messages
     * @return  Colored messages
     */
    public static List<String> color(List<String> messages) {
        messages.forEach(StringManager::color);
        return messages;
    }

    /**
     * Replaces %player% with player's name.
     *
     * @param str  Message
     * @param player  Player
     * @return  Formatted message.
     */
    public static String playerFormat(String str, Player player) {
        return str.replaceAll("%player%", player.getName());
    }
}
