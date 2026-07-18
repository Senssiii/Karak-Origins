package fr.senssi.karakOrigins.mechanic.sealeditem;

import fr.senssi.karakOrigins.KarakOrigins;
import fr.senssi.karakOrigins.utils.items.ItemUtils;
import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import io.th0rgal.oraxen.items.ItemBuilder;
import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class SealedItemMechanic extends Mechanic {
    public static final NamespacedKey SEALED_KEY = new NamespacedKey(KarakOrigins.plugin, NBTKeys.SEALED);
    public static final NamespacedKey SEAL_KEY = new NamespacedKey(KarakOrigins.plugin, NBTKeys.SEAL);

    /**
     * @param factory L'usine qu'on utilise en rapport avec cette mécanique
     * @param section La section dans les fichiers de config
     */
    public SealedItemMechanic(MechanicFactory factory, ConfigurationSection section) {
        super(factory, section, (ItemBuilder item) ->
                item.setCustomTag(SEALED_KEY, PersistentDataType.BOOLEAN, section.getBoolean(NBTKeys.SEALED))
                        .setCustomTag(SEAL_KEY, PersistentDataType.STRING, section.getString(NBTKeys.SEAL))
        );
    }

    public static boolean isSealedItem(ItemStack item) {
        SealedItemMechanic mechanic = (SealedItemMechanic) SealedItemMechanicFactory.instance.getMechanic(item);
        return mechanic != null;
    }

    public static boolean isSealed(ItemStack itemStack) {
        return ItemUtils.getBoolean(itemStack, NBTKeys.SEALED);
    }

    public static void setSealed(ItemStack i, boolean value) {
        ItemUtils.setItemNbt(i, NBTKeys.SEALED, value);
    }

    public static String getSealText(ItemStack i) {
        return ItemUtils.getString(i, NBTKeys.SEAL);
    }

    public static void setSealText(ItemStack i, String text) {
        ItemUtils.setItemNbt(i, NBTKeys.SEAL, text);
    }

}
