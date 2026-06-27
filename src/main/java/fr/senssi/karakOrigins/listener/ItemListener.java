package fr.senssi.karakOrigins.listener;

import fr.senssi.karakOrigins.item.KarakCustomItem;
import fr.senssi.karakOrigins.item.KarakCustomItemRegistry;
import fr.senssi.karakOrigins.utils.items.ItemUtils;
import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = false)
    public void onClick(PlayerInteractEvent event) {
        if (event.getHand().equals(EquipmentSlot.OFF_HAND)) return; // Pour éviter d'avoir des doubles appels

        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        if (item.getType().isAir()) return;
        String craftId = ItemUtils.getString(item, NBTKeys.CRAFT_ID);

        KarakCustomItem karakCustomItem = KarakCustomItemRegistry.items.get(craftId);
        if (karakCustomItem == null) return;
        event.setCancelled(true);

        Action action = event.getAction();

        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            karakCustomItem.onLeftClick(event);
        } else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            karakCustomItem.onRightClick(event);
        }
    }
}
