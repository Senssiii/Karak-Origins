package fr.senssi.karakOrigins.utils.items;

import fr.senssi.karakOrigins.mechanic.sealeditem.SealedItemMechanic;
import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ItemFormatter {
    /**
     * Désactive les tags qui permettent d'avoir des informations sur l'objet.
     */
    public static void hideEveryInfos(ItemMeta itemMeta) {
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
    }

    /**
     * Il est nécessaire d'appeler updateItemFormatting pour afficher le changement
     *
     * @param s      L'objet modifié.
     * @param string Le nom de l'item
     */
    public static void setName(ItemStack s, String string) {
        ItemUtils.setItemNbt(s, NBTKeys.NOM_ITEM, string);
    }

    /**
     * Il est nécessaire d'appeler updateItemFormatting pour afficher le changement.
     * Cette description sera affiché sur le tooltip de l'item directement.
     *
     * @param s   L'objet modifié.
     * @param str La nouvelle description de l'item
     */
    public static void setDescription(ItemStack s, String str) {
        ItemUtils.setItemNbt(s, NBTKeys.DESCRIPTION, str);
    }

    /**
     * Met à jour la mise en forme de l'item visuellement (description & nom).
     *
     * @param itemStack L'objet que l'on veut update
     */
    public static void updateItemFormatting(ItemStack itemStack) {
        updateDescription(itemStack);
        updateNom(itemStack);
    }

    /// @param itemStack L'objet dont le tooltip sera modifié en fonction de sa description dans les NBT
    private static void updateDescription(ItemStack itemStack) {
        List<String> loreList = new ArrayList<>();
        String description = ChatColor.DARK_GRAY + getOrCreateDescription(itemStack);
        loreList.add(description);

        if (SealedItemMechanic.isSealedItem(itemStack)) {
            boolean isSealed = SealedItemMechanic.isSealed(itemStack);
            String seal = SealedItemMechanic.getSealText(itemStack);
            loreList.add(ChatColor.DARK_RED + SealedItemFormatter.getSealedText(isSealed, seal));
        }

        updateLore(itemStack, loreList);
    }

    /// @param itemStack L'item dont on cherche la description
    /// Si aucune valeur de description n'avait été donné, on en set une par rapport à ce qui est déjà dans le lore.
    private static @NonNull String getOrCreateDescription(ItemStack itemStack) {
        String description = ItemUtils.getString(itemStack, NBTKeys.DESCRIPTION);

        if (description.isEmpty()) {
            ItemMeta meta = itemStack.getItemMeta();
            if (meta != null && meta.hasLore()) {
                List<Component> lore = meta.lore();
                if (lore != null && !lore.isEmpty()) {
                    description = PlainTextComponentSerializer.plainText().serialize(lore.getFirst());
                }
            }

            if (!description.isEmpty()) {
                ItemUtils.setItemNbt(itemStack, NBTKeys.DESCRIPTION, description);
            } else {
                description = "";
            }
        }

        return description;
    }

    /// Change l'affichage du nom sur l'item
    private static void updateNom(ItemStack itemStack) {
        String nom = ItemUtils.getString(itemStack, NBTKeys.NOM_ITEM);
        if (nom.isEmpty())
            nom = getItemDisplayName(itemStack);
        updateNom(itemStack, nom);
    }

    private static String getItemDisplayName(ItemStack item) {
        if (item == null || item.getType().isAir()) return null;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;

        String rawName;
        if (meta.hasDisplayName() && meta.displayName() != null) {
            rawName = PlainTextComponentSerializer.plainText().serialize(meta.displayName());
        } else {
            rawName = PlainTextComponentSerializer.plainText().serialize(item.displayName());
        }
        return rawName.replaceAll("[\\[\\]]", "");
    }

    /**
     * Met le string en première ligne de la tooltip en effaçant le reste
     *
     * @param s   L'objet à modifier
     * @param str Le nouveau texte affiché
     */
    private static void updateLore(ItemStack s, List<String> str) {
        ItemMeta itemMeta = s.getItemMeta();
        itemMeta.setLore(str);

        hideEveryInfos(itemMeta);

        s.setItemMeta(itemMeta);
    }

    private static void updateNom(ItemStack s, String str) {
        ItemMeta itemMeta = s.getItemMeta();
        String name = ChatColor.GRAY + str;

        itemMeta.displayName(Component.text(name));

        s.setItemMeta(itemMeta);
    }
}
