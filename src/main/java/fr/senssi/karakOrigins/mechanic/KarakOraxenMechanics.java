package fr.senssi.karakOrigins.mechanic;

import fr.senssi.karakOrigins.KarakOrigins;
import fr.senssi.karakOrigins.mechanic.handgonne.HandgonneMechanicFactory;
import io.th0rgal.oraxen.OraxenPlugin;
import io.th0rgal.oraxen.api.OraxenItems;
import io.th0rgal.oraxen.api.events.OraxenNativeMechanicsRegisteredEvent;
import io.th0rgal.oraxen.mechanics.MechanicsManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class KarakOraxenMechanics implements Listener {

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, KarakOrigins.instance);
    }

    @EventHandler
    public void onMechanicsRegistered(OraxenNativeMechanicsRegisteredEvent event) {
        ConfigurationSection section = OraxenPlugin.get().getResourceManager()
                .getMechanics().getConfigurationSection("handgonne");
        if (section == null) return;

        boolean enabled = section.getBoolean("enabled", true);
        MechanicsManager.registerMechanicFactory(
                "handgonne",
                new HandgonneMechanicFactory(KarakOrigins.instance, section),
                enabled
        );

        // Re-parse items so the new mechanic is applied.
        OraxenItems.loadItems();
    }
}