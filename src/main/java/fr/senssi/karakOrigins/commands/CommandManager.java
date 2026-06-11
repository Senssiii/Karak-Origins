package fr.senssi.karakOrigins.commands;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandManager {

    private final JavaPlugin plugin;
    private final Map<String, SimpleCommand> commands = new HashMap<>();

    public CommandManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /** Enregistre une commande déclarée dans plugin.yml */
    public void register(SimpleCommand cmd) {
        PluginCommand bukkit = plugin.getCommand(cmd.getName());
        if (bukkit == null) {
            plugin.getLogger().warning("Commande '<" + cmd.getName() + ">' absente du plugin.yml !");
            return;
        }
        bukkit.setExecutor(cmd);
        bukkit.setTabCompleter(cmd);
        commands.put(cmd.getName().toLowerCase(), cmd);
        plugin.getLogger().info("[CMD] /" + cmd.getName() + " enregistrée.");
    }

    public Optional<SimpleCommand> getCommand(String name) {
        return Optional.ofNullable(commands.get(name.toLowerCase()));
    }

    public Collection<SimpleCommand> getAll() { return commands.values(); }
}