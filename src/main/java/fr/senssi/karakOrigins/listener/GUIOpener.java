package fr.senssi.karakOrigins.listener;

import fr.senssi.karakOrigins.animal.AnimalGUI;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class GUIOpener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onRightClickEntity(PlayerInteractEntityEvent e) {
        if (e.getHand().equals(EquipmentSlot.OFF_HAND)) return; // Pour éviter d'avoir des doubles appels

        Entity rightClicked = e.getRightClicked();
        if (rightClicked.isDead()) return;

        Player player = e.getPlayer();

        if (!player.isSneaking()) return;
        if (rightClicked instanceof org.bukkit.entity.Wolf || rightClicked instanceof org.bukkit.entity.Cat) {
            org.bukkit.entity.Tameable tameable = (org.bukkit.entity.Tameable) rightClicked;
            if (tameable.isTamed() && tameable.getOwner() != null && tameable.getOwner().getUniqueId().equals(player.getUniqueId())) {

                e.setCancelled(true);
                AnimalGUI.openAnimalGUI(player, rightClicked);
            }
        } else if (rightClicked instanceof org.bukkit.entity.Horse || rightClicked instanceof org.bukkit.entity.Donkey) {

            org.bukkit.entity.AbstractHorse horse = (org.bukkit.entity.AbstractHorse) rightClicked;
            if (horse.isTamed()) {
                e.setCancelled(true);
                AnimalGUI.openAnimalGUI(player, rightClicked);
            }
        }
    }

}
