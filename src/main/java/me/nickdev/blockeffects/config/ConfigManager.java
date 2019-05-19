package me.nickdev.blockeffects.config;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlock;
import me.nickdev.blockeffects.block.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class ConfigManager  {
    
    private ConfigurationSection blockSection;
    
    private ArrayList<World> enabledWorlds = new ArrayList<>();
    private boolean security;
    private boolean permission;
    private int securityCooldown;

    public ConfigManager(BlockEffects blockEffects) {
        FileConfiguration config = blockEffects.getConfig();
        blockSection = config.getConfigurationSection("blocks");
        for (String worldName : config.getStringList("enabled-worlds")) {
            enabledWorlds.add(Bukkit.getWorld(worldName));
        }
        security = config.getBoolean("security.enabled");
        permission = config.getBoolean("no-permission.send");
        securityCooldown = config.getInt("security.cooldown");
    }

    public EBlock getEBlock(String name) {
        return new EBlock(
                name,
                Material.valueOf(blockSection.getString(name + ".material")),
                TriggerType.valueOf(blockSection.getString(name + ".trigger", "WALK")),
                getPotionEffect(name),
                blockSection.getString(name + ".message"),
                blockSection.getString(name + ".permission"),
                blockSection.getStringList(name + ".commands"));
    }

    private PotionEffect getPotionEffect(String name) {
        String potionEffectTypeString = blockSection.getString(name + ".effect.type");
        if (potionEffectTypeString == null) return null;
        PotionEffectType potionEffectType =  PotionEffectType.getByName(potionEffectTypeString);
        if (potionEffectType == null) return null;
        return new PotionEffect(
                potionEffectType,
                blockSection.getInt(name + ".effect.duration")*20,
                blockSection.getInt(name + ".effect.amplifier")
        );
    }

    public ConfigurationSection getBlockSection() {
        return blockSection;
    }

    public ArrayList<World> getEnabledWorlds() {
        return enabledWorlds;
    }

    public boolean isSecurityEnabled() {
        return security;
    }

    public boolean isNoPermissionEnabled() {
        return permission;
    }

    public int getSecurityCooldown() {
        return securityCooldown;
    }
}
