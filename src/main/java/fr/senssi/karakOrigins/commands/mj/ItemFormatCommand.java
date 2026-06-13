package fr.senssi.karakOrigins.commands.mj;

import fr.senssi.karakOrigins.commands.SimpleCommand;
import fr.senssi.karakOrigins.utils.CommandUtils;
import fr.senssi.karakOrigins.utils.items.ItemFormatter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemFormatCommand extends SimpleCommand {
    public ItemFormatCommand() {
        super("itemformat", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) return;
        ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
        if (args[0].equalsIgnoreCase("lore")) {
            String lore = CommandUtils.argsToString(args, 1);
            ItemFormatter.setDescription(item, lore);
        } else if (args[0].equalsIgnoreCase("nom")) {
            String nom = CommandUtils.argsToString(args, 1);
            ItemFormatter.setName(item, nom);
        } else if (args[0].equalsIgnoreCase("quick")) {
            // Version plus rapide qui permet de mettre le nom et la description de l'item en même temps.
            String[] str = CommandUtils.argsToString(args, 1).split("#");
            ItemFormatter.setName(item, str[0]);
            ItemFormatter.setDescription(item, str[1]);
        }
        ItemFormatter.updateItemFormatting(item);
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("lore", "nom", "quick");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("quick")) {
            return List.of("#");
        } else {
            return null;
        }
    }
}
