package fr.senssi.karakOrigins.mechanic;

import fr.senssi.karakOrigins.KarakOrigins;
import fr.senssi.karakOrigins.mechanic.handgonne.HandgonneMechanicFactory;
import fr.senssi.karakOrigins.mechanic.sealeditem.SealedItemMechanicFactory;
import fr.senssi.karakOrigins.mechanic.sealitem.SealItemMechanicFactory;
import fr.senssi.karakOrigins.mechanic.textitem.TextItemMechanicFactory;
import io.th0rgal.oraxen.OraxenPlugin;
import io.th0rgal.oraxen.api.OraxenItems;
import io.th0rgal.oraxen.api.events.OraxenNativeMechanicsRegisteredEvent;
import io.th0rgal.oraxen.mechanics.MechanicsManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class KarakOraxenMechanics implements Listener {

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, KarakOrigins.plugin);
    }

    @EventHandler
    public void onMechanicsRegistered(OraxenNativeMechanicsRegisteredEvent event) {
        YamlConfiguration mechanics = OraxenPlugin.get().getResourceManager().getMechanics();

        ConfigurationSection section = mechanics.getConfigurationSection("handgonne");
        if (section == null) return;

        boolean enabled = section.getBoolean("enabled", true);
        MechanicsManager.registerMechanicFactory(
                "handgonne",
                new HandgonneMechanicFactory(KarakOrigins.plugin, section),
                enabled
        );

        section = mechanics.getConfigurationSection("textitem");
        if (section == null) return;
        enabled = section.getBoolean("enabled", true);
        MechanicsManager.registerMechanicFactory("textitem",
                new TextItemMechanicFactory(KarakOrigins.plugin, section),
                enabled);

        section = mechanics.getConfigurationSection("sealed");
        if (section == null) return;
        enabled = section.getBoolean("enabled", true);
        MechanicsManager.registerMechanicFactory("sealed",
                new SealedItemMechanicFactory(KarakOrigins.plugin, section),
                enabled);

        section = mechanics.getConfigurationSection("seal");
        if (section == null) return;
        enabled = section.getBoolean("enabled", true);
        MechanicsManager.registerMechanicFactory("seal",
                new SealItemMechanicFactory(KarakOrigins.plugin, section),
                enabled);

        // Re-parse items so the new mechanic is applied.
        OraxenItems.loadItems();
    }
}