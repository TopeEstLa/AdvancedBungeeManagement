package me.topeestla.abm;

import me.topeestla.abm.commands.MaintenanceCommand;
import me.topeestla.abm.listeners.JoinListener;
import me.topeestla.abm.listeners.PingListener;
import me.topeestla.abm.managers.MaintenanceManager;
import me.topeestla.abm.utils.ConfigurationManager;
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

        this.getProxy().getPluginManager().registerListener(this, new PingListener(this));
        this.getProxy().getPluginManager().registerListener(this, new JoinListener(this));
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
