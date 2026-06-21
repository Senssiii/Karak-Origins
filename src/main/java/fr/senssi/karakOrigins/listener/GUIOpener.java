package fr.senssi.karakOrigins.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class GUIOpener {

    // TODO
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getHand().equals(EquipmentSlot.OFF_HAND)) return; // Pour éviter d'avoir des double appels

    }

    public void onRightClickEntity(PlayerInteractEntityEvent e) {
        if (e.getHand().equals(EquipmentSlot.OFF_HAND)) return; // Pour éviter d'avoir des doubles appels

        Entity rightClicked = e.getRightClicked();
        if (rightClicked.isDead()) return;

        Player player = e.getPlayer();

        if (rightClicked instanceof org.bukkit.entity.Wolf ||
                rightClicked instanceof org.bukkit.entity.Cat) {

            org.bukkit.entity.Tameable tameable = (org.bukkit.entity.Tameable) rightClicked;
            if (tameable.isTamed() &&
                    tameable.getOwner() != null &&
                    tameable.getOwner().getUniqueId().equals(player.getUniqueId())) {

                // Appelle ta fonction d'ouverture d'inventaire ici
                ouvrirInventaireAnimal(player, rightClicked);
            }
        } else if (rightClicked instanceof org.bukkit.entity.Horse ||
                rightClicked instanceof org.bukkit.entity.Donkey) {

            // Optionnel : Vérifier si l'équidé est apprivoisé
            org.bukkit.entity.AbstractHorse horse = (org.bukkit.entity.AbstractHorse) rightClicked;
            if (horse.isTamed()) {
                ouvrirInventaireAnimal(player, rightClicked);
            }
        }
    }

}
