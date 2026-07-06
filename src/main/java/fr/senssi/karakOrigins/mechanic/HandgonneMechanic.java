package fr.senssi.karakOrigins.mechanic;

import io.th0rgal.oraxen.OraxenPlugin;
import io.th0rgal.oraxen.items.ItemBuilder;
import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.persistence.PersistentDataType;

public class HandgonneMechanic extends Mechanic {
    public static final NamespacedKey KEY = new NamespacedKey(OraxenPlugin.get(), "damage_multiplier");
    private final double maxMun;

    public HandgonneMechanic(MechanicFactory factory, ConfigurationSection section) {
        super(factory, section, (ItemBuilder item) ->
                item.setCustomTag(KEY, PersistentDataType.DOUBLE, section.getDouble("max_mun", 1.0))
        );
        this.maxMun = section.getDouble("max_mun", 1.0);
    }

    public double getMaxMun() {
        return maxMun;
    }
}
