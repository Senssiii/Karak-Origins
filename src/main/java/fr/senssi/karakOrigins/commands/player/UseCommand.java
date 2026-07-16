package fr.senssi.karakOrigins.commands.player;

import fr.senssi.karakOrigins.commands.SimpleCommand;
import fr.senssi.karakOrigins.mechanic.textitem.TextItemMechanic;
import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static fr.senssi.karakOrigins.mechanic.textitem.TextItemMechanicFactory.textItemMechanicFactory;

public class UseCommand extends SimpleCommand {


    public UseCommand() {
        super("use", true);
    }

    /**
     * Commande d'utilisation d'un objet. L'objet utilisé doit être tenu dans la main droite.
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        ItemStack itemUsed = ((Player) sender).getInventory().getItemInMainHand();
        if (itemUsed.getType().isAir()) return;

        if (!OraxenItems.exists(itemUsed)) return;
        TextItemMechanic mechanic = (TextItemMechanic) textItemMechanicFactory.getMechanic(itemUsed);
        if (mechanic == null) return;

        mechanic.onUse((Player) sender, itemUsed);
    }
}
