package fr.senssi.karakOrigins.mechanic.sealitem;

import fr.senssi.karakOrigins.KarakOrigins;
import fr.senssi.karakOrigins.mechanic.sealeditem.SealedItemMechanic;
import fr.senssi.karakOrigins.utils.items.ItemFormatter;
import fr.senssi.karakOrigins.utils.items.ItemUtils;
import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import io.th0rgal.oraxen.items.ItemBuilder;
import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import static fr.senssi.karakOrigins.mechanic.sealeditem.SealedItemMechanic.setSealText;
import static fr.senssi.karakOrigins.mechanic.sealeditem.SealedItemMechanic.setSealed;

public class SealItemMechanic extends Mechanic {
    public static final NamespacedKey SEAL_TEXT_KEY = new NamespacedKey(KarakOrigins.plugin, NBTKeys.SEAL_TEXT);

    protected SealItemMechanic(MechanicFactory mechanicFactory, ConfigurationSection section) {
        super(mechanicFactory, section, (ItemBuilder item) ->
                item.setCustomTag(SEAL_TEXT_KEY, PersistentDataType.STRING, section.getString(NBTKeys.SEAL_TEXT, ""))
        );
    }

    public static boolean isSealItem(ItemStack item) {
        SealItemMechanic mechanic = (SealItemMechanic) SealItemMechanicFactory.instance.getMechanic(item);
        return mechanic != null;
    }

    public static String getSealText(ItemStack itemStack) {
        return ItemUtils.getString(itemStack, NBTKeys.SEAL_TEXT);
    }

    public static void setSeal(ItemStack itemToSeal, String sealText) {
        if (!SealedItemMechanic.isSealedItem(itemToSeal)) return;

        setSealed(itemToSeal, true);
        setSealText(itemToSeal, sealText);

        ItemFormatter.updateItemFormatting(itemToSeal);
    }
}