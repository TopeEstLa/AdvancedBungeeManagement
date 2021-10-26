package me.topeestla.advancedbungeemanagement.managers;

import me.topeestla.advancedbungeemanagement.AdvancedBungeeManagement;
import me.topeestla.advancedbungeemanagement.data.Maintenance;
import me.topeestla.advancedbungeemanagement.utils.IDataSerialisables;

import java.io.File;

/**
 * @author TopeEstLa
 */
public class MaintenanceManager implements IDataSerialisables<Maintenance> {

    private final AdvancedBungeeManagement advancedBungeeManagement;

    private File maintenanceFile;
    private Maintenance maintenance;

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
     * @return Maintenance
     */
    public Maintenance getMaintenance() {
        return maintenance;
    }
}
