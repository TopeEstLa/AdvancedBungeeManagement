package me.topeestla.abm.managers;

import me.topeestla.abm.AdvancedBungeeManagement;
import me.topeestla.abm.data.Maintenance;
import me.topeestla.abm.utils.IDataSerialisables;

import java.io.File;

/**
 * @author TopeEstLa
 */
public class MaintenanceManager implements IDataSerialisables<Maintenance> {

    private final AdvancedBungeeManagement advancedBungeeManagement;

    private File maintenanceFile;
    private Maintenance maintenance;

    private String motd;
    private String version;

    /**
     * @param advancedBungeeManagement
     */
    public MaintenanceManager(AdvancedBungeeManagement advancedBungeeManagement) {
        this.advancedBungeeManagement = advancedBungeeManagement;
        this.maintenanceFile = new File(this.advancedBungeeManagement.getDataFolder(), "maintenance.json");
        this.loadMaintenance();
    }

    /**
     * Load the maintenance configuration
     */
    private void loadMaintenance() {
        if (this.maintenanceFile.exists()) {
            this.maintenance = this.load(this.maintenanceFile, Maintenance.class);
        } else {
            this.maintenance = new Maintenance(this.advancedBungeeManagement.getConfigurationManager().getFormattedString("MAINTENANCE.ADMIN", false, null));
            this.save(this.maintenanceFile, this.maintenance);
        }

        if(this.maintenance.isEnabled()) {
            this.motd = this.advancedBungeeManagement.getConfigurationManager().getFormattedString("GLOBAL.MOTD", true, null);
            this.version = this.advancedBungeeManagement.getConfigurationManager().getFormattedString("GLOBAL.VERSION", true, null);
        } else {
            this.motd = this.advancedBungeeManagement.getConfigurationManager().getFormattedString("MAINTENANCE.MOTD", true, null);
            this.version = this.advancedBungeeManagement.getConfigurationManager().getFormattedString("MAINTENANCE.VERSION", true, null);
        }
    }

    /**
     * @param playerName
     * @return boolean playerCanJoin
     */
    public boolean playerCanJoin(String playerName) {
        return this.maintenance.getPlayersWhitelisted().contains(playerName);
    }

    /**
     * @return maintenance status
     */
    public boolean isMaintenanced() {
        return this.maintenance.isEnabled();
    }

    /**
     * Save the maintenance configuration
     */
    public void saveMaintenance() {
        this.save(this.maintenanceFile, this.maintenance);
    }

    /**
     * Set the new string motd
     * @param motd
     */
    public void setMotd(String motd) {
        this.motd = motd;
    }

    /**
     * Set the new string version
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return String motd
     */
    public String getMotd() {
        return motd;
    }

    /**
     *
     * @return String version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return Maintenance
     */
    public Maintenance getMaintenance() {
        return maintenance;
    }
}
