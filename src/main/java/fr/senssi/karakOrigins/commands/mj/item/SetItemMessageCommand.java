package fr.senssi.karakOrigins.commands.mj.item;

import fr.senssi.karakOrigins.commands.SimpleCommand;
import fr.senssi.karakOrigins.utils.Messenger;
import fr.senssi.karakOrigins.utils.items.ItemFormatter;
import fr.senssi.karakOrigins.utils.items.ItemUtils;
import fr.senssi.karakOrigins.utils.keys.NBTKeys;
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

        ItemUtils.setItemNbt(itemInHand, NBTKeys.MESSAGE, message);
        ItemFormatter.updateItemFormatting(itemInHand);

        Messenger.sendAdminMessage("Nouveau message : " + message, player);
    }

}
