package me.topeestla.advancedbungeemanagement.commands;

import me.topeestla.advancedbungeemanagement.AdvancedBungeeManagement;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * @author TopeEstLa
 */
public class MaintenanceCommand extends Command {

    private final AdvancedBungeeManagement advancedBungeeManagement;

    public MaintenanceCommand(AdvancedBungeeManagement advancedBungeeManagement) {
        super("maintenance");
        this.advancedBungeeManagement = advancedBungeeManagement;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 0) {
            if (sender.hasPermission("advancedbungeemanagement.admin")) {
                switch (args[0]) {
                    case "status":
                        sender.sendMessage(new TextComponent("§eMaintenance is currently " + (this.advancedBungeeManagement.getMaintenanceManager().isMaintenanced() ? "§a§lON" : "§4§lOFF") + "§e."));
                        break;
                    case "on":
                        this.advancedBungeeManagement.getMaintenanceManager().getMaintenance().setStatus(true);
                        this.advancedBungeeManagement.getMaintenanceManager().saveMaintenance();

                        sender.sendMessage(new TextComponent("§eMaintenance is now §a§lON."));

                        this.advancedBungeeManagement.getProxy().getPlayers().forEach(players -> {
                            if (!this.advancedBungeeManagement.getMaintenanceManager().playerCanJoin(players.getName())) {
                                players.disconnect(new TextComponent(this.advancedBungeeManagement.getConfigurationManager().getFormattedString("MAINTENANCE.MESSAGE", true, null)));
                            }
                        });
                        break;
                    case "off":
                        this.advancedBungeeManagement.getMaintenanceManager().getMaintenance().setStatus(false);
                        this.advancedBungeeManagement.getMaintenanceManager().saveMaintenance();

                        sender.sendMessage(new TextComponent("§eMaintenance is now §4§lOFF."));
                        break;
                    case "add":
                        if (args.length == 2) {
                            this.advancedBungeeManagement.getMaintenanceManager().getMaintenance().addPlayer(args[1]);
                            this.advancedBungeeManagement.getMaintenanceManager().saveMaintenance();

                            sender.sendMessage(new TextComponent("§eThe player §6§l" + args[1] + " §ehas been added from maintenance."));
                        } else {
                            sender.sendMessage(new TextComponent("§e/maintenance add <Player>"));
                        }
                        break;
                    case "remove":
                        if (args.length == 2) {
                            if (!this.advancedBungeeManagement.getMaintenanceManager().getMaintenance().getPlayersWhitelisted().contains(args[1])) {
                                this.advancedBungeeManagement.getMaintenanceManager().getMaintenance().removePlayer(args[1]);
                                this.advancedBungeeManagement.getMaintenanceManager().saveMaintenance();

                                sender.sendMessage(new TextComponent("§eThe player §6§l" + args[1] + " §ehas been removed from maintenance."));
                            } else {
                                sender.sendMessage(new TextComponent("§eThe player §6§l" + args[1] + " §eisnt in the maintenance."));
                            }
                        } else {
                            sender.sendMessage(new TextComponent("§e/maintenance remove <Player>"));
                        }
                        break;
                    case "help":
                    default:
                        this.advancedBungeeManagement.getConfigurationManager().getFormattedStringList("GLOBAL.HELP", true, null).forEach(s -> sender.sendMessage(new TextComponent(s)));
                        break;
                }
            }
        }
    }
}
