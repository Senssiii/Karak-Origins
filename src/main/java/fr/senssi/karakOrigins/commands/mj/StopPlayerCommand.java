package fr.senssi.karakOrigins.commands.mj;

import fr.senssi.karakOrigins.commands.SimpleCommand;
import fr.senssi.karakOrigins.utils.CommandUtils;
import fr.senssi.karakOrigins.utils.Messenger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StopPlayerCommand extends SimpleCommand {
    public StopPlayerCommand() {
        super("stopplayer", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) return;
        Player target = sender.getServer().getPlayer(args[0]);

        Messenger.sendAdminMessage("Vous avez été arrêté !",target);
        Messenger.log("Joueur "+target.getDisplayName()+"arrêté par "+ sender.getName(),sender.getServer());
        target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,30*20,0));
        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,30*20,0));
    }

    public List<String> tab(CommandSender sender, String[] args) {
        if (args.length == 1){
            List<String> players = CommandUtils.getPlayers(sender.getServer());
            return players.stream()
                    .filter(s ->s.startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    }
