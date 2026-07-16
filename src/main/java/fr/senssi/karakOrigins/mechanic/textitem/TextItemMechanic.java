package fr.senssi.karakOrigins.mechanic.textitem;

import fr.senssi.karakOrigins.KarakOrigins;
import fr.senssi.karakOrigins.utils.Messenger;
import fr.senssi.karakOrigins.utils.items.ItemUtils;
import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import io.th0rgal.oraxen.items.ItemBuilder;
import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class TextItemMechanic extends Mechanic {
    public static final NamespacedKey MESSAGE_KEY = new NamespacedKey(KarakOrigins.instance, NBTKeys.MESSAGE);

//    A MechanicFactory is created once per mechanic type during plugin load or reload and stores shared state, registers listeners,
//    and tracks which items use that mechanic. A Mechanic instance is created separately for each item that defines the mechanic in its
//    Mechanics: section and stores that item’s parsed settings.
//
//    At runtime, the listener uses the factory to retrieve the Mechanic belonging to the current item and then reads its item-specific configuration.
//    In short, the flow is Mechanics: in the item config → one shared MechanicFactory for that mechanic ID → one Mechanic instance per configured item.

    //setCustomTag records the tag in the builder's persistentDataMap, which regen()/build() then writes into every generated item's
    // PersistentDataContainer. If you need to modify an already-built ItemStack at runtime (outside parse time), use ItemUtils.editItemMeta(...)
    // and set the tag on the meta's PDC there instead.

    /**
     * Une mécanique est unique par id d'item, non par itemStack
     *
     * @param factory L'usine qu'on utilise en rapport avec cette mécanique
     * @param section La section dans les fichiers de config
     */
    public TextItemMechanic(MechanicFactory factory, ConfigurationSection section) {
        super(factory, section, (ItemBuilder item) ->
                item.setCustomTag(MESSAGE_KEY, PersistentDataType.STRING,
                        section.getString(NBTKeys.MESSAGE))
        );
    }

    private String getMessage(ItemStack item) {
        return ItemUtils.getString(item, NBTKeys.MESSAGE);
    }

    /**
     * @param item    L'item d'origine
     * @param message Message à mettre dans l'item
     * @return Un nouvel item avec le message incorporé à l'intérieur
     */
    public void setMessage(ItemStack item, String message) {
        ItemMeta meta = item.getItemMeta(); // Si ça fonctionne il faut changer toutes les utilisations des fonctions dans le utils
        meta.getPersistentDataContainer().set(MESSAGE_KEY, PersistentDataType.STRING, message);
        item.setItemMeta(meta);
        
    }

    public void onUse(Player player, ItemStack item) {
        Messenger.sendPersonnalNarrationMessage("«" + getMessage(item) + "»", player);
    }
}
