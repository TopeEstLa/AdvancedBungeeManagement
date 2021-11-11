package me.topeestla.advancedbungeemanagement.listeners;

import me.topeestla.advancedbungeemanagement.AdvancedBungeeManagement;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * @author TopeEstLa
 */
public class JoinListener implements Listener {

    private final AdvancedBungeeManagement advancedBungeeManagement;

    public JoinListener(AdvancedBungeeManagement advancedBungeeManagement) {
        this.advancedBungeeManagement = advancedBungeeManagement;
    }

    @EventHandler
    public void onJoin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();

        if (this.advancedBungeeManagement.getMaintenanceManager().isMaintenanced()) {
            if (!this.advancedBungeeManagement.getMaintenanceManager().playerCanJoin(player.getName())) {
                player.disconnect(new TextComponent(this.advancedBungeeManagement.getConfigurationManager().getFormattedString("MAINTENANCE.MESSAGE", true, null)));
            }
        }
    }
}
