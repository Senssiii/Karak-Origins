package fr.senssi.karakOrigins.mechanic.textitem;

import io.th0rgal.oraxen.api.events.OraxenItemsLoadedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class TextItemMechanicListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = false)
    public void onItemReload(OraxenItemsLoadedEvent event) {

    }
}
