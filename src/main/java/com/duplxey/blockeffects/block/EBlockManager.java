package com.duplxey.blockeffects.block;

import com.duplxey.blockeffects.config.ConfigManager;
import com.duplxey.blockeffects.constants.O;
import com.duplxey.blockeffects.constants.P;
import com.duplxey.blockeffects.BlockEffects;
import com.duplxey.blockeffects.util.Cooldown;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.HashMap;

public class EBlockManager {

    private BlockEffects blockEffects;
    private ConfigManager configManager;

    private HashMap<Material, EBlock> eBlocks = new HashMap<>();
    private Cooldown<Player> cooldown = new Cooldown<>();
    private HashMap<Player, Collection<PotionEffect>> playerPotionStorage = new HashMap<>();

    public EBlockManager(BlockEffects blockEffects, ConfigManager configManager) {
        this.blockEffects = blockEffects;
        this.configManager = configManager;

        load();

        cooldown.setCooldownTime(configManager.getSecurityCooldownTime());
    }

    private void load() {
        // Loads all the EBlocks
        for (String blockName : configManager.getBlockSection().getKeys(false)) {
            registerEBlock(configManager.getEBlock(blockName));
        }
    }

    /**
     * Activates the EBlock if the block is an EBlock + player has no cooldown + right trigger is used.
     *
     * @param player  Player
     * @param type  Type (Material of the block)
     * @param triggerType  TriggerType
     * @return  Returns true if the block has been activated.
     */
    public boolean tryToActivate(Player player, Material type, TriggerType triggerType) {
        if (cooldown.hasCooldown(player)) return false;
        if (configManager.isSecurityEnabled()) {
            cooldown.addCooldown(player);
        }
        EBlock eblock = getEBlock(type);
        if (eblock.getTriggerType() != triggerType) return false;
        if (eblock.getPermission() != null && !player.hasPermission(eblock.getPermission())) {
            if (configManager.isNoPermissionMessageEnabled()) {
                player.sendMessage(P.PREFIX + ChatColor.RED + O.NO_PERMISSION.getText());
            }
            return false;
        }
        // Store the potion effects and add them back to the player when EBlock effect is over
        if (eblock.getPotionEffect() != null) {
            playerPotionStorage.put(player, player.getActivePotionEffects());

            new BukkitRunnable() {

                @Override
                public void run() {
                    for (PotionEffect potionEffect : playerPotionStorage.get(player)) {
                        player.addPotionEffect(potionEffect);
                    }
                }

            }.runTaskLater(blockEffects, eblock.getPotionEffect().getDuration());
        }
        eblock.activate(player);
        return true;
    }

    public void registerEBlock(EBlock eBlock) {
        eBlocks.put(eBlock.getMaterial(), eBlock);
    }

    public void unregisterEBlock(Material material) {
        eBlocks.remove(material);
    }

    public boolean isEBlock(Material material) {
        return eBlocks.containsKey(material);
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
