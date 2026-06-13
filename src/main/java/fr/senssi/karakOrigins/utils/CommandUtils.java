package fr.senssi.karakOrigins.utils;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandUtils {
    /**
     * @param s Le serveur ou les joueurs sont connectés
     * @return La liste des pseudos des joueurs en ligne.
     */
    public static List<String> getPlayers(Server s) {
        List<String> players = new ArrayList<>();
        for (Player onlinePlayer : s.getOnlinePlayers()) {
            players.add(onlinePlayer.getName());
        }
        return players;
    }

    /**
     * Retire les ignore- premier éléments du tableau.
     *
     * @param args   Le tableau dont on veut une copie réduite
     * @param ignore Le nombre d'objet à supprimer de la liste
     * @return
     */
    public static String argsToString(String[] args, int ignore) {
        StringBuilder out = new StringBuilder();
        for (int i = ignore; i < args.length; i++) {
            if (out.length() > 0) {
                out.append(" ");
            }
            out.append(args[i]);
        }
        return out.toString();
    }
}
