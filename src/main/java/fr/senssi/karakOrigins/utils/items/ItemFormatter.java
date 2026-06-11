package fr.senssi.karakOrigins.utils.items;

import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class ItemFormatter {
    /**
     * Il n'est pas nécessaire d'appeler updateItemFormatting pour afficher le changement
     * @param s
     * @param string
     */
    public static void setName(ItemStack s, String string){
        String name = ChatColor.GRAY + string;
        ItemMeta itemMeta = s.getItemMeta();
        itemMeta.setDisplayName(name);

        s.setItemMeta(itemMeta);
    }

    /**
     * Il est nécessaire d'appeler updateItemFormatting pour afficher le changement
     * @param s
     * @param str
     */
    public static void setDescription(ItemStack s, String str){
        ItemUtils.setItemValue(s, NBTKeys.DESCRIPTION,str);
    }
    private static void hideEveryInfos(ItemMeta itemMeta) {
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
    }
    public static void updateItemFormatting(ItemStack itemStack){
        String description = ItemUtils.getItemValue(itemStack, NBTKeys.DESCRIPTION);
        updateLore(itemStack,description);
    }

    private static void updateLore(ItemStack s, String str){
        ItemMeta itemMeta = s.getItemMeta();
        String lore = ChatColor.DARK_GRAY + str;
        itemMeta.setLore(Collections.singletonList(lore));

        hideEveryInfos(itemMeta);

        s.setItemMeta(itemMeta);
    }
}
