package com.duplxey.blockeffects.block;

import com.duplxey.blockeffects.constants.CC;
import com.duplxey.blockeffects.util.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class EBlock  {

    // Required data
    private String name;
    private Material material;
    private TriggerType triggerType;

    // Optional data
    private PotionEffect potionEffect;
    private String message;
    private String permission;
    private List<String> commands;

    /**
     * Creates a new EBlock.
     *
     * @param name  Name
     * @param material  Material
     * @param potionEffect  PotionEffect
     * @param message  Message
     * @param permission  Permission
     * @param commands  List of commands
     */
    public EBlock(String name, Material material, TriggerType triggerType, PotionEffect potionEffect, String message, String permission, List<String> commands) {
        this.name = name;
        this.material = material;
        this.triggerType = triggerType;
        this.potionEffect = potionEffect;
        this.message = message;
        this.permission = permission;
        this.commands = commands;
    }

    /**
     * Creates a new EBlock.
     *
     * @param name  Name
     * @param material  Material
     */
    public EBlock(String name, Material material, TriggerType triggerType) {
        this(name, material, triggerType, null, null, null, null);
    }

    /**
     * Activates the block (potion effect, message, commands).
     *
     * @param player  Player
     */
    public void activate(Player player) {
        // Add the potion effect
        if (potionEffect != null) {
            player.removePotionEffect(potionEffect.getType());
            player.addPotionEffect(potionEffect);
        }
        // Send the message
        if (message != null) {
            player.sendMessage(StringManager.color(message));
        }
        // Execute the commands
        if (commands != null && commands.size() != 0) {
            for (String command : commands) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), StringManager.playerFormat(command, player));
        }
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public TriggerType getTriggerType() {
        return triggerType;
    }

    public PotionEffect getPotionEffect() {
        return potionEffect;
    }

    public void setPotionEffect(PotionEffect potionEffect) {
        this.potionEffect = potionEffect;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public void removeCommand(String command) {
        commands.remove(command);
    }

    /**
     * Returns the info about the EBlock.
     *
     * @return  String[] information
     */
    public String[] info() {
        return new String[] {
                CC.NORMAL + "Name: " + CC.VALUE + name,
                CC.NORMAL + "Block: " + CC.VALUE + material.toString(),
                CC.NORMAL + "Trigger: " + CC.VALUE + (triggerType == null ? "none" : triggerType),
                CC.NORMAL + "Effect: " + CC.VALUE + (potionEffect == null ? "none" : potionEffect.getType().getName() + CC.NORMAL + " (dur: " + CC.VALUE + potionEffect.getDuration() + CC.NORMAL + ", amp: " + CC.VALUE + potionEffect.getAmplifier() + CC.NORMAL + ")"),
                CC.NORMAL + "Message: " + CC.VALUE + (message == null ? "none" : message),
                CC.NORMAL + "Permission: " + CC.VALUE + (permission == null ? "none" : permission),
        };
    }

    @Override
    public String toString() {
        return "EBlock{" +
                "name='" + name + '\'' +
                ", material=" + material +
                ", triggerType=" + triggerType +
                ", potionEffect=" + potionEffect +
                ", message='" + message + '\'' +
                ", permission='" + permission + '\'' +
                ", commands=" + commands +
                '}';
    }
}
