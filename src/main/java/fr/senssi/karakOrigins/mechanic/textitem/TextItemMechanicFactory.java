package fr.senssi.karakOrigins.mechanic.textitem;

import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicConfigProperty;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TextItemMechanicFactory extends MechanicFactory {
    public static TextItemMechanicFactory textItemMechanicFactory;

    public TextItemMechanicFactory(JavaPlugin plugin, ConfigurationSection section) {
        super(section);
        // Pas besoin de listener, ça se fait avec la commande /use
        textItemMechanicFactory = this;
    }

    @Override
    public Mechanic parse(ConfigurationSection itemMechanicConfiguration) {
        Mechanic mechanic = new TextItemMechanic(this, itemMechanicConfiguration);
        addToImplemented(mechanic);
        return mechanic;
    }

    @Override
    public @NotNull List<MechanicConfigProperty> getConfigSchema() {
        return List.of(
                MechanicConfigProperty.string(NBTKeys.MESSAGE, "Le texte lu avec la commande /use")
        );
    }
}
