package fr.senssi.karakOrigins.utils.items;

import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class ItemFormatter {
    /**
     * Désactive les tags qui permettent d'avoir des informations sur l'objet.
     */
    private static void hideEveryInfos(ItemMeta itemMeta) {
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
    }

    /**
     * Il n'est pas nécessaire d'appeler updateItemFormatting pour afficher le changement
     *
     * @param s      L'objet modifié.
     * @param string Le nom de l'item
     */
    public static void setName(ItemStack s, String string) {
        String name = ChatColor.GRAY + string;
        ItemMeta itemMeta = s.getItemMeta();
        itemMeta.setDisplayName(name);

        s.setItemMeta(itemMeta);
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
     * Met à jour la mise en forme de l'item visuellement (description).
     *
     * @param itemStack L'objet que l'on veut update
     */
    public static void updateItemFormatting(ItemStack itemStack) {
        String description = ItemUtils.getItemNbt(itemStack, NBTKeys.DESCRIPTION);
        updateLore(itemStack, description);
    }

    /**
     * Met à jour le lore directement.
     *
     * @param s   L'objet à modifier
     * @param str Le nouveau texte affiché
     */
    private static void updateLore(ItemStack s, String str) {
        ItemMeta itemMeta = s.getItemMeta();
        String lore = ChatColor.DARK_GRAY + str;
        itemMeta.setLore(Collections.singletonList(lore));

        hideEveryInfos(itemMeta);

        s.setItemMeta(itemMeta);
    }
}
