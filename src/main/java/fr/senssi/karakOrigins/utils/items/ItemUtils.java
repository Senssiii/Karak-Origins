package fr.senssi.karakOrigins.utils.items;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import fr.senssi.karakOrigins.KarakOrigins;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

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

    public static void setItemNbt(ItemStack i, String key, String value) {
        i.editPersistentDataContainer((persistentDataContainer -> {
            persistentDataContainer.set(new NamespacedKey(KarakOrigins.instance, key), PersistentDataType.STRING, value);
        }));
    }

    public static void setItemNbt(ItemStack i, String key, int value) {
        i.editPersistentDataContainer((persistentDataContainer -> {
            persistentDataContainer.set(new NamespacedKey(KarakOrigins.instance, key), PersistentDataType.INTEGER, value);
        }));
    }

    /**
     * Récupère le String attaché.
     *
     * @return La valeur associée à la clé.
     */
    public static String getString(ItemStack item, String key) {
        return item.getPersistentDataContainer().get(new NamespacedKey(KarakOrigins.instance, key), PersistentDataType.STRING);
    }

    public static int getInt(ItemStack item, String key) {
        return item.getPersistentDataContainer().get(new NamespacedKey(KarakOrigins.instance, key), PersistentDataType.INTEGER);
    }

}
