package fr.senssi.karakOrigins.listener;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class GUIOpener {

    // TODO
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getHand().equals(EquipmentSlot.OFF_HAND)) return; // Pour éviter d'avoir des double appels
        if (e.getClickedBlock() == null) return;
        if (e.getClickedBlock().equals(Material.BEDROCK)) {
            // On verra plus tard
        }
    }
}
