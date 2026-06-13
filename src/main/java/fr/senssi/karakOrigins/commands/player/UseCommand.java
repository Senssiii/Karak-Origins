package fr.senssi.karakOrigins.commands.player;

import fr.senssi.karakOrigins.commands.SimpleCommand;
import fr.senssi.karakOrigins.item.TextItem;
import fr.senssi.karakOrigins.utils.items.ItemUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UseCommand extends SimpleCommand {
    public UseCommand() {
        super("use", true);
    }

    /**
     * Commande d'utilisation d'un objet. L'objet utilisé doit être tenu dans la main droite.
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();
        if (ItemUtils.isTextItem(item)) {
            TextItem textItem = new TextItem(item);
            textItem.onUse(player);
        }
    }
}
