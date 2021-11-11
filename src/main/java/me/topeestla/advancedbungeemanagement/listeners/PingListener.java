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
public class PingListener implements Listener {

    private final AdvancedBungeeManagement advancedBungeeManagement;

    public PingListener(AdvancedBungeeManagement advancedBungeeManagement) {
        this.advancedBungeeManagement = advancedBungeeManagement;
    }

    @EventHandler
    public void onPing(ProxyPingEvent event) {
        ServerPing serverPing = event.getResponse();
        serverPing.setDescriptionComponent(new TextComponent(this.advancedBungeeManagement.getMaintenanceManager().getMotd()));

        if (this.advancedBungeeManagement.getMaintenanceManager().isMaintenanced()) {
            serverPing.setVersion(new ServerPing.Protocol(this.advancedBungeeManagement.getMaintenanceManager().getVersion(), 2));
        } else {
            serverPing.setVersion(new ServerPing.Protocol(this.advancedBungeeManagement.getMaintenanceManager().getVersion(), serverPing.getVersion().getProtocol()));
        }

        event.setResponse(serverPing);
    }
}
