package me.topeestla.advancedbungeemanagement.utils;

import me.topeestla.advancedbungeemanagement.AdvancedBungeeManagement;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author TopeEstLa
 */
public class ConfigurationManager {

    private final AdvancedBungeeManagement advancedBungeeManagement;

    private File configurationFile;
    private Configuration configuration;

    /**
     * @param advancedBungeeManagement
     */
    public ConfigurationManager(AdvancedBungeeManagement advancedBungeeManagement) {
        this.advancedBungeeManagement = advancedBungeeManagement;
        this.saveDefaultConfig();
    }

    /**
     * @param path         path of YML string
     * @param formatColors true if you want to replace & with §
     * @param format       format Strings to another String (ex. {name} -> KayzunYT} you can create an double Array String[][]{{"key", "value"}, {"key", "value"}}
     * @return formatted String
     */
    public String getFormattedString(String path, boolean formatColors, String[][] format) {
        String result = this.configuration.getString(path);

        if (formatColors) result = result.replace("&", "§");

        if (format != null) {
            for (String[] formatTo : format) {
                String from = formatTo[0];
                String to = formatTo[1];

                result = result.replace(from, to);
            }
        }
        return result;
    }

    /**
     * @param path         path of YML String list
     * @param formatColors true if you want to replace & with §
     * @param format       format Strings to another String (ex. {name} -> KayzunYT} you can create an double Array String[][]{{"key", "value"}, {"key", "value"}}
     * @return formatted String
     */
    public List<String> getFormattedStringList(String path, boolean formatColors, String[][] format) {
        List<String> result = this.configuration.getStringList(path);

        for (int i = 0; i < result.size(); i++) {
            if (formatColors) result.set(i, result.get(i).replace("&", "§"));

            if (format != null) {
                for (String[] formatTo : format) {
                    String from = formatTo[0];
                    String to = formatTo[1];

                    result.set(i, result.get(i).replace(from, to));
                }
            }
        }
        return result;
    }


    /**
     * Save the default configuration
     */
    private void saveDefaultConfig() {
        try {
            if (!this.advancedBungeeManagement.getDataFolder().exists())
                this.advancedBungeeManagement.getDataFolder().mkdirs();

            this.configurationFile = new File(this.advancedBungeeManagement.getDataFolder(), "config.yml");
            if (!this.configurationFile.exists()) {
                Files.copy(this.advancedBungeeManagement.getResourceAsStream("config.yml"), this.configurationFile.toPath());
            }
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.configurationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Configuration
     */
    public Configuration getConfiguration() {
        return configuration;
    }
}
