package fr.senssi.karakOrigins.mechanic.sealitem;

import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicConfigProperty;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import io.th0rgal.oraxen.mechanics.MechanicsManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SealItemMechanicFactory extends MechanicFactory {
    public static SealItemMechanicFactory instance;

    public SealItemMechanicFactory(JavaPlugin plugin, ConfigurationSection section) {
        super(section);
        MechanicsManager.registerListeners(plugin, "sealitem", new SealItemListener());
        instance = this;
    }

    @Override
    public Mechanic parse(ConfigurationSection itemMechanicConfiguration) {
        Mechanic mechanic = new SealItemMechanic(this, itemMechanicConfiguration);
        addToImplemented(mechanic);
        return mechanic;
    }

    @Override
    public @NotNull List<MechanicConfigProperty> getConfigSchema() {
        return List.of(
                MechanicConfigProperty.string(NBTKeys.SEAL_TEXT, "Le texte appliqué à l'item.")
        );
    }
}