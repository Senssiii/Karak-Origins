package fr.senssi.karakOrigins.commands.mj.item;

import fr.senssi.karakOrigins.commands.SimpleCommand;
import fr.senssi.karakOrigins.mechanic.textitem.TextItemMechanic;
import fr.senssi.karakOrigins.mechanic.textitem.TextItemMechanicFactory;
import fr.senssi.karakOrigins.utils.Messenger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SetItemMessageCommand extends SimpleCommand {
    public SetItemMessageCommand() {
        super("setitemmessage", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) return;
        Player player = (Player) sender;
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        String message = String.join(" ", args);

        TextItemMechanic mechanic = (TextItemMechanic) TextItemMechanicFactory.textItemMechanicFactory.getMechanic(itemInHand);
        if (mechanic == null) return; // L'item n'a pas la mécanique
        mechanic.setMessage(itemInHand, message);

        Messenger.sendAdminMessage("Nouveau message : " + message, player);
    }

}
