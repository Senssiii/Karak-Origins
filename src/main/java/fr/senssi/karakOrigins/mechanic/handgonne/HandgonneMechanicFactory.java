package fr.senssi.karakOrigins.mechanic.handgonne;

import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicConfigProperty;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import io.th0rgal.oraxen.mechanics.MechanicsManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HandgonneMechanicFactory extends MechanicFactory {
    public HandgonneMechanicFactory(JavaPlugin plugin, ConfigurationSection section) {
        super(section);
        MechanicsManager.registerListeners(plugin, getMechanicID(), new HandgonneListener(this));
    }

    @Override
    public Mechanic parse(ConfigurationSection itemMechanicConfiguration) {
        Mechanic mechanic = new HandgonneMechanic(this, itemMechanicConfiguration);
        addToImplemented(mechanic);
        return mechanic;
    }

    @Override
    public @NotNull List<MechanicConfigProperty> getConfigSchema() {
        return List.of(
                MechanicConfigProperty.decimal("multiplier", "Damage multiplier applied on hit", 1.0, 0.0)
        );
    }
}
