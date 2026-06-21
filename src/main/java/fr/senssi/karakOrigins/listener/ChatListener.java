package fr.senssi.karakOrigins.listener;

import fr.senssi.karakOrigins.utils.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatListener implements Listener {

    private final JavaPlugin plugin;

    public ChatListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        event.setCancelled(true);

        Bukkit.getScheduler().runTask(plugin, () -> {
            if (message.startsWith("**")) {
                Messenger.sendLocalPlayerMessage(ChatColor.DARK_GREEN + "[AGIT]", message.substring(2).trim(), player, 20);
            } else if (message.startsWith("*")) {
                Messenger.agirMessage(message.substring(1).trim(), player);
            } else if (message.startsWith("!")) {
                Messenger.sendLocalPlayerMessage(ChatColor.DARK_GREEN + "[CRIE]", message.substring(1).trim(), player, 20);
            } else if (message.startsWith("$")) {
                Messenger.sendLocalPlayerMessage(ChatColor.DARK_GREEN + "[CHUCHOTTE]", message.substring(1).trim(), player, 1);
            } else if (message.startsWith("(")) { // HRP
                String hrp = message.substring(1);
                if (hrp.endsWith(")")) hrp = hrp.substring(0, hrp.length() - 1);
                Messenger.sendGlobalMessage(player.getName() + " [HRP]", hrp.trim(), player.getServer());
            } else { // Parle normalement
                Messenger.sendLocalPlayerMessage(ChatColor.DARK_GREEN + "[PARLE]", message, player, 5);
            }
        });
    }
}
