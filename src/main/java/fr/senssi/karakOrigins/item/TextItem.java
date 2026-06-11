package fr.senssi.karakOrigins.item;

import de.tr7zw.changeme.nbtapi.NBTItem;
import fr.senssi.karakOrigins.utils.Messenger;
import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TextItem {
    private final ItemStack itemStack;

    public TextItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public String getMessage(){
        NBTItem nbtItem = new NBTItem(itemStack);
        return nbtItem.getString(NBTKeys.MESSAGE);
    }

    public void onUse(Player player) {
        Messenger.agirMessage(getMessage(), player);
    }
}
