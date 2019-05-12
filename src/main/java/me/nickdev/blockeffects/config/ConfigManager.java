package me.nickdev.blockeffects.config;

import me.nickdev.blockeffects.BlockEffects;
import me.nickdev.blockeffects.block.EBlock;
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
        securityCooldown = config.getInt("security.cooldown") * 20;
    }

    public EBlock getEBlock( String name) {
         return new EBlock(
                 name,
                 Material.valueOf(blockSection.getString(name + ".material")),
                 new PotionEffect(
                         PotionEffectType.getByName(blockSection.getString(name + ".effect.type")),
                         blockSection.getInt(name + ".effect.duration"),
                         blockSection.getInt(name + ".effect.amplifier")
                 ),
                 blockSection.getString(name + ".message"),
                 blockSection.getString(name + ".permission"),
                 blockSection.getStringList(name + ".commands"));
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
