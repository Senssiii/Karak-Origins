package fr.senssi.karakOrigins.mechanic.sealeditem;

import fr.senssi.karakOrigins.mechanic.textitem.TextItemMechanic;
import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import io.th0rgal.oraxen.OraxenPlugin;
import io.th0rgal.oraxen.items.ItemBuilder;
import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class SealedItemMechanic extends Mechanic {
    public static final NamespacedKey KEY = new NamespacedKey(OraxenPlugin.get(), "sealed");
    private boolean sealed;

    /**
     * @param factory L'usine qu'on utilise en rapport avec cette mécanique
     * @param section La section dans les fichiers de config
     */
    public SealedItemMechanic(MechanicFactory factory, ConfigurationSection section) {
        super(factory, section, (ItemBuilder item) ->
                item.setCustomTag(KEY, PersistentDataType.BOOLEAN, section.getBoolean(NBTKeys.SEALED))
        );
        this.sealed = section.getBoolean(NBTKeys.SEALED);
    }

    public boolean getSealed() {
        return this.sealed;
    }

    public ItemStack setSealed(ItemStack item, boolean sealed) {
        this.sealed = sealed;
        ItemBuilder itemBuilder = new ItemBuilder(item);
        itemBuilder.setCustomTag(TextItemMechanic.MESSAGE_KEY, PersistentDataType.BOOLEAN, sealed); // Je crois que ça ne fait pas ce que je pense que ça fait

        return itemBuilder.build();
    }
}
