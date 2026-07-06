package fr.senssi.karakOrigins.mechanic.handgonne;

import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class HandgonneListener implements Listener {
    private final HandgonneMechanicFactory factory;

    public HandgonneListener(HandgonneMechanicFactory factory) {
        this.factory = factory;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onDamage(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType().isAir()) return;

        if (!OraxenItems.exists(item)) return;

        HandgonneMechanic mechanic = (HandgonneMechanic) factory.getMechanic(item);
        if (mechanic == null) return;
        Action action = event.getAction();
        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            mechanic.onLeftClick(event);
        } else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            mechanic.onRightClick(event);
        }
    }
}
