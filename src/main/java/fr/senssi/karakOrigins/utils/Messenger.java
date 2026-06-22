package fr.senssi.karakOrigins.utils;

import fr.senssi.karakOrigins.identity.Identity;
import fr.senssi.karakOrigins.identity.IdentityManager;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class Messenger {

    public static void sendGlobalMessage(String prefix, String str, Server s) {
        String message = prefix + " " + ChatColor.GRAY + str;
        for (Player onlinePlayer : s.getOnlinePlayers()) {
            onlinePlayer.sendMessage(message);
        }
    }

    public static void sendAdminMessage(String str, Player p) {
        String message = ChatColor.RED + "[ADMIN] " + ChatColor.GRAY + str;
        p.sendMessage(message);
    }

    public static void log(String str, Server server) {
        server.getConsoleSender().sendMessage("[KARAK] " + str);
    }

    public static void sendGlobalNarrationMessage(String str, Server server) {
        String message = ChatColor.GOLD + "[NARRATION] " + ChatColor.GRAY + str;
        for (Player player : server.getOnlinePlayers()) {
            player.sendMessage(message);
        }
        log(message, server);
    }

    public static void sendLocalNarrrationMessage(String str, Player startPosition, int distance) {
        String message = ChatColor.GOLD + "[NARRATION] " + ChatColor.GRAY + str;
        sendLocalMessage(message, startPosition, distance);
        log(message, startPosition.getServer());
    }

    public static void sendPersonnalNarrationMessage(String str, Player player) {
        String message = ChatColor.GOLD + "[NARRATION] " + ChatColor.GRAY + str;
        player.sendMessage(message);
        log(message, player.getServer());
    }

    /**
     * Envoie un message sans formattage aux joueurs autour.
     */
    public static void sendLocalMessage(String str, Player start, int distance) {
        List<Entity> nearbyEntities = start.getNearbyEntities(distance, distance, distance);
        start.sendMessage(str);
        for (Entity entity : nearbyEntities) {
            entity.sendMessage(str);
        }
    }

    /**
     * @param prefix   Le préfixe placé devant le message, après le nom du joueur
     * @param msg      Le message envoyé
     * @param p        Le joueur depuis qui on envoie le message (son nom est affiché).
     * @param distance Distance max pour recevoir le message
     */
    public static void sendLocalPlayerMessage(String prefix, String msg, Player p, int distance) {
        Identity identity = IdentityManager.getIdentity(p);
        String message = String.format("%s%s %s %s%s", ChatColor.GRAY, identity.getNomPrenom(), prefix, ChatColor.GRAY, msg);
        sendLocalMessage(message, p, distance);

    }

    public static void agirMessage(String message, Player player) {
        Messenger.sendLocalPlayerMessage(ChatColor.DARK_GREEN + "[AGIT]", message, player, 10);
    }

}
