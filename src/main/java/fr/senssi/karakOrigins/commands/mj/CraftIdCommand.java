package fr.senssi.karakOrigins.commands.mj;

import fr.senssi.karakOrigins.commands.SimpleCommand;
import fr.senssi.karakOrigins.utils.Messenger;
import fr.senssi.karakOrigins.utils.items.ItemUtils;
import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class CraftIdCommand extends SimpleCommand {
    public CraftIdCommand() {
        super("craftid", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) return;
        ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
        if (args[0].equalsIgnoreCase("set")) {
            ItemUtils.setItemValue(item, NBTKeys.CRAFT_ID, args[1]);
        } else if (args[0].equalsIgnoreCase("get")) {
            String id = ItemUtils.getItemValue(item, NBTKeys.CRAFT_ID);
            String name = item.getItemMeta().hasDisplayName() ? item.getItemMeta().getDisplayName() : item.getType().toString();
            String message = String.format("Id de %s : %s", name, id);

            Messenger.sendAdminMessage(message, (Player) sender);
        }
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("set", "get");
        } else {
            return List.of("");
        }
    }
}
