package fr.senssi.karakOrigins.mechanic;

import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class HandgonneListener implements Listener {
    private final HandgonneMechanicFactory factory;

    public HandgonneListener(HandgonneMechanicFactory factory) {
        this.factory = factory;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!OraxenItems.exists(item)) return;

        HandgonneMechanic mechanic = (HandgonneMechanic) factory.getMechanic(item);
        if (mechanic == null) return;

        event.setDamage(event.getDamage() * mechanic.getMaxMun());
    }
}
