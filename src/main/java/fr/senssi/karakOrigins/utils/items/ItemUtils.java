package fr.senssi.karakOrigins.utils.items;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class ItemUtils {

    public static ItemStack createCustomHead(String texture) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);

        ItemMeta meta = head.getItemMeta();
        if (meta != null) {
            head.setItemMeta(meta);
        }

        NBT.modify(head, nbt -> {
            ReadWriteNBT skullOwner = nbt.getOrCreateCompound("SkullOwner");

            skullOwner.setUUID("Id", UUID.randomUUID());

            ReadWriteNBT properties = skullOwner.getOrCreateCompound("Properties");
            properties.getCompoundList("textures").addCompound().setString("Value", texture);
        });

        return head;
    }

    public static boolean isTextItem(ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        return nbtItem.getOrDefault("message", null) == null;
    }
    
    public static void setItemValue(ItemStack i, String key, String value) {
        NBTItem item = new NBTItem(i);
        item.setString(key, value);
        i.setItemMeta(item.getItem().getItemMeta());
    }

    public static String getItemValue(ItemStack item, String key) {
        return new NBTItem(item).getString(key);
    }
}
