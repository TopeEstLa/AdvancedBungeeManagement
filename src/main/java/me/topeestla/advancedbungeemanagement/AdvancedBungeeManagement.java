package me.topeestla.advancedbungeemanagement;

import me.topeestla.advancedbungeemanagement.listeners.BungeeListeners;
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

        getProxy().getPluginManager().registerListener(this, new BungeeListeners(this));
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
