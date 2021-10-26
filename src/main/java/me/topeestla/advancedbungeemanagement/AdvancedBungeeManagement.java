package me.topeestla.advancedbungeemanagement;

import me.topeestla.advancedbungeemanagement.commands.MaintenanceCommand;
import me.topeestla.advancedbungeemanagement.listeners.JoinListeners;
import me.topeestla.advancedbungeemanagement.listeners.PingListeners;
import me.topeestla.advancedbungeemanagement.managers.MaintenanceManager;
import me.topeestla.advancedbungeemanagement.utils.ConfigurationManager;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author TopeEstLa
 */
public class AdvancedBungeeManagement extends Plugin {

    private ConfigurationManager configurationManager;
    private MaintenanceManager maintenanceManager;

    @Override
    public void onEnable() {
        this.configurationManager = new ConfigurationManager(this);
        this.maintenanceManager = new MaintenanceManager(this);

        this.getProxy().getPluginManager().registerListener(this, new PingListeners(this));
        this.getProxy().getPluginManager().registerListener(this, new JoinListeners(this));
        this.getProxy().getPluginManager().registerCommand(this, new MaintenanceCommand(this));
    }


    /**
     * @return ConfigurationManager
     */
    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    /**
     * @return MaintenanceManager
     */
    public MaintenanceManager getMaintenanceManager() {
        return maintenanceManager;
    }
}
