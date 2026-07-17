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
    public static final NamespacedKey KEY = new NamespacedKey(KarakOrigins.instance, NBTKeys.SEALED);

    /**
     * @param factory L'usine qu'on utilise en rapport avec cette mécanique
     * @param section La section dans les fichiers de config
     */
    public SealedItemMechanic(MechanicFactory factory, ConfigurationSection section) {
        super(factory, section, (ItemBuilder item) ->
                item.setCustomTag(KEY, PersistentDataType.BOOLEAN, section.getBoolean(NBTKeys.SEALED))
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

}
