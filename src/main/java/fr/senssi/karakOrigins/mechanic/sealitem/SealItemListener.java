package fr.senssi.karakOrigins.mechanic.sealitem;

import fr.senssi.karakOrigins.utils.Messenger;
import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class SealItemListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = false)
    public void onInteract(PlayerInteractEvent event) {
        if (event.getHand() == EquipmentSlot.OFF_HAND)
            return;
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType().isAir()) return;

        if (!OraxenItems.exists(item)) return;

        if (SealItemMechanic.isSealItem(item)) {
            Action action = event.getAction();

            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                String sealText = SealItemMechanic.getSealText(item);

                ItemStack offHandItem = player.getInventory().getItemInOffHand();
                if (!offHandItem.isEmpty()) {
                    SealItemMechanic.setSeal(offHandItem, sealText);
                    Messenger.sendPersonnalNarrationMessage("§aL'objet a été scellé avec succès !", player);
                    event.setCancelled(true); // Annule l'action par défaut du clic droit
                }
            }
        }
    }
}
