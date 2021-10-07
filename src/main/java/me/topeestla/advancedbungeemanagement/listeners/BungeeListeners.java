package me.topeestla.advancedbungeemanagement.listeners;

import me.topeestla.advancedbungeemanagement.AdvancedBungeeManagement;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * @author TopeEstLa
 */
public class BungeeListeners implements Listener {

    private final AdvancedBungeeManagement advancedBungeeManagement;

    public BungeeListeners(AdvancedBungeeManagement advancedBungeeManagement) {
        this.advancedBungeeManagement = advancedBungeeManagement;
    }

    @EventHandler
    public void onPing(ProxyPingEvent event) {
        ServerPing serverPing = event.getResponse();
        serverPing.setDescriptionComponent(new TextComponent(this.advancedBungeeManagement.getMaintenanceManager().isMaintenanced() ?
                this.advancedBungeeManagement.getConfigurationManager().getFormattedString("MAINTENANCE.MOTD", true, null) :
                this.advancedBungeeManagement.getConfigurationManager().getFormattedString("GLOBAL.MOTD", true, null)));

        if(this.advancedBungeeManagement.getMaintenanceManager().isMaintenanced()) {
            serverPing.setVersion(new ServerPing.Protocol(this.advancedBungeeManagement.getConfigurationManager().getFormattedString("MAINTENANCE.VERSION", true, null), 2));
        } else {
            serverPing.setVersion(new ServerPing.Protocol(this.advancedBungeeManagement.getConfigurationManager().getFormattedString("GLOBAL.VERSION", true, null), serverPing.getVersion().getProtocol()));
        }
        
        event.setResponse(serverPing);
    }
}
