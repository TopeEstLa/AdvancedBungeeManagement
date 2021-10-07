package me.topeestla.advancedbungeemanagement.data;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * @author TopeEstLa
 */
public class Maintenance implements Serializable {

    private boolean enabled;
    public List<String> playersWhitelisted;

    public Maintenance() { }

    public Maintenance(String owner) {
        this.enabled = false;
        this.playersWhitelisted = Lists.newArrayList();
        this.playersWhitelisted.add(owner);
    }

    public void setStatus(boolean enabled) {
        this.enabled = enabled;
    }

    public void addPlayer(String playerName) {
        this.playersWhitelisted.add(playerName);
    }

    public void removePlayer(String playerName) {
        if (this.playersWhitelisted.contains(playerName)) {
            this.playersWhitelisted.remove(playerName);
        }
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public List<String> getPlayersWhitelisted() {
        return playersWhitelisted;
    }
}
