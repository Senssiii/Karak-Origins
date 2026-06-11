package fr.senssi.karakOrigins.utils;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandUtils {
    public static List<String> getPlayers(Server s){
        List<String> players = new ArrayList<>();
        for (Player onlinePlayer : s.getOnlinePlayers()) {
            players.add(onlinePlayer.getName());
        }
        return players;
    }
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
