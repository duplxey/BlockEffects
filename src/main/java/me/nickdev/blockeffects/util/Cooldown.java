package me.nickdev.blockeffects.util;

import java.util.HashMap;

public class Cooldown<T> {

    private HashMap<T, Long> cooldown = new HashMap<>();
    private int cooldownTime = 0;

    public void addCooldown(T object, int seconds) {
        if (seconds == 0) return;
        cooldown.remove(object);
        cooldown.put(object, System.currentTimeMillis() + seconds*1000);
    }

    public void addCooldown(T object) {
        if (cooldownTime == 0) return;
        cooldown.remove(object);
        cooldown.put(object, System.currentTimeMillis() + cooldownTime*1000);
    }

    public void removeCooldown(T object) {
        cooldown.remove(object);
    }

    public boolean hasCooldown(T object) {
        if (cooldown.containsKey(object)) {
            if (System.currentTimeMillis() >= cooldown.get(object)) {
                cooldown.remove(object);
            }
        }
        return cooldown.containsKey(object);
    }

    public int getTimeRemaining(T object) {
        if (cooldown.containsKey(object)) {
            if (System.currentTimeMillis() >= cooldown.get(object)) {
                cooldown.remove(object);
            }
            return (int) ((cooldown.get(object) - System.currentTimeMillis())/1000);
        }
        return 0;
    }

    public void setCooldownTime(int cooldownTimeInSeconds) {
        cooldownTime = cooldownTimeInSeconds;
    }
}
