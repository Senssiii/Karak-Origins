package fr.senssi.karakOrigins.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public abstract class SimpleCommand implements CommandExecutor, TabCompleter {

    private final String name;
    private final boolean playerOnly;

    public SimpleCommand(String name, boolean playerOnly) {
        this.name = name;
        this.playerOnly = playerOnly;
    }

    @Override
    public final boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (playerOnly && !(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Cette commande est réservée aux joueurs.");
            return true;
        }
        execute(sender, args);
        return true;
    }

    @Override
    public final List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = tab(sender, args);
        return completions != null ? completions : Collections.emptyList();
    }

    public abstract void execute(CommandSender sender, String[] args);

    // Optionnel : retourner null = pas de tab-complete
    public List<String> tab(CommandSender sender, String[] args) { return null; }

    public String getName() { return name; }
}
