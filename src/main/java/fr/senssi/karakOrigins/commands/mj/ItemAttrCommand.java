package fr.senssi.karakOrigins.commands.mj;

import fr.senssi.karakOrigins.commands.SimpleCommand;
import fr.senssi.karakOrigins.utils.KarakAttributeModifier;
import fr.senssi.karakOrigins.utils.Messenger;
import fr.senssi.karakOrigins.utils.items.ItemFormatter;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ItemAttrCommand extends SimpleCommand {
    // Utilisation d'une liste propre aux enums Bukkit pour faciliter la conversion plus tard
    public List<String> attributes = Arrays.asList(
            "generic.attack_damage",
            "generic.attack_speed",
            "generic.attack_knockback",
            "generic.armor",
            "generic.armor_toughness",
            "generic.knockback_resistance");

    public ItemAttrCommand() {
        super("itemattr", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) return;

        if (Objects.equals(args[0], "add")) {
            Player player = (Player) sender;
            ItemStack item = player.getInventory().getItemInMainHand();

            if (item.getType().isAir()) return;

            Attribute foundAttribute = null;
            String attrInput = args[1].toLowerCase();
            for (Attribute attr : Attribute.values()) {
                if (attr.getKey().getKey().equalsIgnoreCase(attrInput) ||
                        attr.name().equalsIgnoreCase(attrInput.replace("generic.", ""))) {
                    foundAttribute = attr;
                    break;
                }
            }

            if (foundAttribute == null) {
                player.sendMessage("§cAttribut invalide.");
                return;
            }

            double parsedAmount;
            try {
                parsedAmount = Double.parseDouble(args[2]);
            } catch (NumberFormatException e) {
                player.sendMessage("§cLa valeur doit être un nombre.");
                return;
            }
            
            AttributeModifier.Operation parsedOperation;
            try {
                parsedOperation = AttributeModifier.Operation.valueOf(args[3].toUpperCase());
            } catch (IllegalArgumentException e) {
                player.sendMessage("§cOpération invalide (ADD_NUMBER, ADD_SCALAR, MULTIPLY_SCALAR_1).");
                return;
            }

            final Attribute finalAttr = foundAttribute;
            final double finalAmount = parsedAmount;
            final AttributeModifier.Operation finalOp = parsedOperation;
            final EquipmentSlot finalSlot = EquipmentSlot.HAND;


            KarakAttributeModifier.addAttribute(item, finalAttr, finalAmount, finalOp, finalSlot);
            ItemFormatter.hideEveryInfos(item.getItemMeta());

            ItemFormatter.updateItemFormatting(item);
            Messenger.sendAdminMessage("La structure physique de l'objet a été modifiée.", player);
        } else if (Objects.equals(args[0], "reset")) { // On supprime tous les attributs sur l'item.
            ItemStack itemStack = ((Player) sender).getInventory().getItemInMainHand();
            KarakAttributeModifier.clearAllAttributes(itemStack);
            Messenger.sendAdminMessage("L'item à bien été vidé de ses attributs.", ((Player) sender));
        } else {
            Messenger.sendAdminMessage("Le premier argument n'a pas été reconu.", (Player) sender);
        }
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        if (args.length == 1) return Arrays.asList("add", "reset");

        if (args.length == 2 && args[0].equalsIgnoreCase("add")) {
            return attributes.stream()
                    .filter(attr -> attr.startsWith(args[1].toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (args.length == 3 && args[0].equalsIgnoreCase("add")) {
            return List.of("0.0");
        }

        if (args.length == 4 && args[0].equalsIgnoreCase("add")) {
            return Arrays.asList("ADD_NUMBER", "ADD_SCALAR").stream()
                    .filter(op -> op.startsWith(args[3].toUpperCase()))
                    .collect(Collectors.toList());
        }

        return super.tab(sender, args);
    }
}
