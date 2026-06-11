package fr.senssi.karakOrigins.commands.mj;

import fr.senssi.karakOrigins.commands.SimpleCommand;
import fr.senssi.karakOrigins.utils.Messenger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static fr.senssi.karakOrigins.utils.CommandUtils.argsToString;

public class NarrationCommand extends SimpleCommand {
    public NarrationCommand() {
        super("narration", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) return;
        if (args[0].equalsIgnoreCase("global")) {
            Messenger.sendGlobalNarrationMessage(
                    argsToString(args, 1),
                    sender.getServer());
        } else if (args[0].equalsIgnoreCase("local")) {
            String txt;
            int distance;

            if (args.length >= 2 && isInt(args[1])) {
                distance = Integer.parseInt(args[1]);
                txt = argsToString(args, 2);
            } else {
                distance = 30;
                txt = argsToString(args, 1);
            }
            Messenger.sendLocalNarrrationMessage(txt, (Player) sender, distance);
        }
    }

    public List<String> tab(CommandSender sender, String[] args) {
        if (args.length == 1) {
            List<String> types = new ArrayList<>();
            types.add("global");
            types.add("local");

            return types.stream()
                    .filter(s -> s.startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("local")) {
            List<String> distances = Arrays.asList("7", "30", "50", "100"); // propositions par défaut.

            return distances.stream()
                    .filter(s -> s.startsWith(args[1]))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
