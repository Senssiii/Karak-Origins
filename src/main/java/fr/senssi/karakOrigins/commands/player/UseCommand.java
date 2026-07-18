package fr.senssi.karakOrigins.commands.player;

import fr.senssi.karakOrigins.commands.SimpleCommand;
import fr.senssi.karakOrigins.mechanic.sealeditem.SealedItemMechanic;
import fr.senssi.karakOrigins.mechanic.textitem.TextItemMechanic;
import fr.senssi.karakOrigins.utils.Messenger;
import fr.senssi.karakOrigins.utils.items.ItemFormatter;
import io.th0rgal.oraxen.api.OraxenItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

import static fr.senssi.karakOrigins.mechanic.textitem.TextItemMechanicFactory.textItemMechanicFactory;

public class UseCommand extends SimpleCommand {
    private final HashMap<UUID, ItemStack> confirmationsEnAttente = new HashMap<>();

    public UseCommand() {
        super("use", true);
    }

    /**
     * Commande d'utilisation d'un objet. L'objet utilisé doit être tenu dans la main droite.
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) return;

        ItemStack itemUsed = player.getInventory().getItemInMainHand();
        if (itemUsed.getType().isAir()) return;
        ItemFormatter.updateItemFormatting(itemUsed); // On met à jour le format de l'item de base

        if (!OraxenItems.exists(itemUsed)) return;
        TextItemMechanic mechanic = (TextItemMechanic) textItemMechanicFactory.getMechanic(itemUsed);
        if (mechanic == null) return;


        if (SealedItemMechanic.isSealedItem(itemUsed) &&
                SealedItemMechanic.isSealed(itemUsed)) {
            demanderConfirmation(player, itemUsed);
            return;
        }

        mechanic.onUse(player, itemUsed);
        ItemFormatter.updateItemFormatting(itemUsed);
    }

    /**
     * Envoie le message interactif au joueur pour lui demander de confirmer
     */
    private void demanderConfirmation(Player player, ItemStack item) {
        confirmationsEnAttente.put(player.getUniqueId(), item);

        // Création du bouton [Confirmer] cliquable
        Component boutonConfirmer = Component.text("[BRISER LE SCEAU]")
                .color(NamedTextColor.GREEN)
                .clickEvent(ClickEvent.callback(audience -> {
                    if (audience instanceof Player clicker) {
                        gererConfirmation(clicker);
                    }
                })).hoverEvent(HoverEvent.showText(Component.text("Briser le sceau.").color(NamedTextColor.GRAY)));

        Messenger.sendPersonnalNarrationMessage("L'objet est scellé ! Voulez-vous briser le sceau ?", player);
        player.sendMessage(boutonConfirmer);
    }

    /**
     * Traite l'action une fois que le joueur a cliqué sur le bouton de confirmation
     */
    private void gererConfirmation(Player player) {
        UUID uuid = player.getUniqueId();

        // On vérifie si le joueur avait bien une action en attente
        if (!confirmationsEnAttente.containsKey(uuid)) {
            player.sendMessage(Component.text("Vous avez déjà brisé le sceau.").color(NamedTextColor.RED));
            return;
        }

        ItemStack itemUsed = confirmationsEnAttente.remove(uuid);

        // On vérifie que le joueur tient toujours le même objet en main
        ItemStack itemEnMain = player.getInventory().getItemInMainHand();
        if (!itemUsed.isSimilar(itemEnMain))
            return;


        // Récupération du mechanic et exécution
        TextItemMechanic mechanic = (TextItemMechanic) textItemMechanicFactory.getMechanic(itemUsed);
        if (mechanic != null) {
            mechanic.onUse(player, itemUsed);
        }

        SealedItemMechanic.setSealed(itemEnMain, false);
        ItemFormatter.updateItemFormatting(itemEnMain);
    }
}
