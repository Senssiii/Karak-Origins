package fr.senssi.karakOrigins.mechanic.sealeditem;

import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicConfigProperty;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SealedItemMechanicFactory extends MechanicFactory {
    public static SealedItemMechanicFactory instance;

    public SealedItemMechanicFactory(JavaPlugin plugin, ConfigurationSection section) {
        super(section);
        instance = this;
    }

    @Override
    public Mechanic parse(ConfigurationSection itemMechanicConfiguration) {
        Mechanic mechanic = new SealedItemMechanic(this, itemMechanicConfiguration);
        addToImplemented(mechanic);
        return mechanic;
    }

    @Override
    public @NotNull List<MechanicConfigProperty> getConfigSchema() {
        return List.of(
                MechanicConfigProperty.bool(NBTKeys.SEALED, "Si l'objet est scellé ou non.")
        );
    }
}
