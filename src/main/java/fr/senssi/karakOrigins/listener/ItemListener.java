package fr.senssi.karakOrigins.listener;

import org.bukkit.event.Listener;

public class ItemListener implements Listener {

//    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = false)
//    public void onClick(PlayerInteractEvent event) {
//        if (event.getHand().equals(EquipmentSlot.OFF_HAND)) return; // Pour éviter d'avoir des doubles appels
//
//        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
//        if (item.getType().isAir()) return;
//        String craftId = ItemUtils.getString(item, NBTKeys.CRAFT_ID);
//
//        KarakCustomItem karakCustomItem = KarakCustomItemRegistry.items.get(craftId);
//        if (karakCustomItem == null) return;
//        event.setCancelled(true);
//
//        Action action = event.getAction();
//
//        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
//            karakCustomItem.onLeftClick(event);
//        } else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
//            karakCustomItem.onRightClick(event);
//        }
//    }
}
