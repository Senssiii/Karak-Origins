package fr.senssi.karakOrigins.utils.items;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import de.tr7zw.changeme.nbtapi.iface.ReadableItemNBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;
import java.util.function.Function;

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
        return NBT.get(item, (Function<ReadableItemNBT, Boolean>) nbt -> nbt.hasTag("message"));
    }

    public static void setItemValue(ItemStack i, String key, String value) {
        NBT.modify(i, nbt -> {
            nbt.setString(key, value);
        });
    }

    public static String getItemValue(ItemStack item, String key) {
        return NBT.get(item, (Function<ReadableItemNBT, String>) nbt -> nbt.getString(key));
    }
}
