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


    public Maintenance() {}

    public Maintenance(String owner) {
        this.enabled = false;
        this.playersWhitelisted = Lists.newArrayList();
        this.playersWhitelisted.add(owner);
    }

    /**
     * @param status Update the maintenance status
     */
    public void setStatus(boolean status) {
        this.enabled = status;
    }

    /**
     * @param playerName Add player to the maintenance
     */
    public void addPlayer(String playerName) {
        this.playersWhitelisted.add(playerName);
    }

    /**
     * @param playerName Remove player form the maintenance
     */
    public void removePlayer(String playerName) {
        if (this.playersWhitelisted.contains(playerName)) {
            this.playersWhitelisted.remove(playerName);
        }
    }

    /**
     * @return maitenance isEnabled
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * @return playersWhitelisted
     */
    public List<String> getPlayersWhitelisted() {
        return playersWhitelisted;
    }
}
