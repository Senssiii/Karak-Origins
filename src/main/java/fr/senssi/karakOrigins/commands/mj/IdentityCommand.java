package fr.senssi.karakOrigins.commands.mj;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import fr.senssi.karakOrigins.commands.SimpleCommand;
import fr.senssi.karakOrigins.identity.Identity;
import fr.senssi.karakOrigins.identity.IdentityManager;
import fr.senssi.karakOrigins.utils.CommandUtils;
import fr.senssi.karakOrigins.utils.items.ItemFormatter;
import fr.senssi.karakOrigins.utils.items.ItemUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NonNull;

import java.util.Arrays;
import java.util.List;

import static fr.senssi.karakOrigins.utils.keys.NBTKeys.*;

public class IdentityCommand extends SimpleCommand {

    public IdentityCommand() {
        super("identity", false);
    }

    private static void setNewIdentityValue(String[] args, Identity id, String s) {
        if (args.length < 2) return;
        switch (args[2].toLowerCase()) {
            case "nom":
                id.nom = s;
                break;
            case "prenom":
                id.prenom = s;
                break;
            case "age":
                id.age = s;
                break;
            case "origine":
                id.origine = s;
                break;
            default:
                break;
        }
    }

    private static @NonNull ItemStack getInfoHead(Identity id) {
        ItemStack head = ItemUtils.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmVhNDY4NWQ1NWFlYzk5NTVkZDg2OWI0OTViMmY1YjJmYjc0MzU4NzYxM2JhNTg3OTYwYzNhM2Q2NWYyNGE4YiJ9fX0=");
        ItemFormatter.setName(head, "Informations de " + id.getPlayer().getDisplayName());
        ItemFormatter.setDescription(head, "Nom : " + id.nom + "\\nPrénom : " + id.prenom + "\\nÂge : " + id.age + "\\nOrigine : " + id.origine);
        ItemFormatter.updateItemFormatting(head);
        return head;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) return;

        if (args[0].equalsIgnoreCase("get")) {
            Player target = sender.getServer().getPlayer(args[1]);
            Identity id = IdentityManager.getIdentity(target);
            Gui identityGUI = createIdentityGUI(id);
            if (sender instanceof HumanEntity)
                identityGUI.open((HumanEntity) sender);
        } else if (args[0].equalsIgnoreCase("set")) {
            String s = CommandUtils.argsToString(args, 3);

            Player changing = sender.getServer().getPlayer(args[1]); // Le joueur qui va prendre les modifications
            Identity identity = IdentityManager.getIdentity(changing);
            setNewIdentityValue(args, identity, s);
            identity.save();
        }
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        if (args.length == 2 && args[0].equalsIgnoreCase("get")) {
            return CommandUtils.getPlayers(sender.getServer());
        } else if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
            return CommandUtils.getPlayers(sender.getServer());
        } else if (args.length == 3 && args[0].equalsIgnoreCase("set")) {
            return Arrays.asList(NOM, PRENOM, AGE, ORIGINE);
        }
        if (args.length == 1) {
            return Arrays.asList("set", "get");
        } else {
            return List.of("");
        }
    }

    public Gui createIdentityGUI(Identity id) {
        Gui gui = Gui.gui().title(Component.text(id.getNomPrenom())).rows(3).create();
        ItemStack head = getInfoHead(id);

        GuiItem infos = ItemBuilder
                .from(head)
                .asGuiItem();

        gui.getFiller().fill(ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).asGuiItem());
        gui.setItem(2 + 9, infos);
        gui.disableAllInteractions(); // Pas sûr que ça ne se fasse pas par défaut
        return gui;
    }
}
