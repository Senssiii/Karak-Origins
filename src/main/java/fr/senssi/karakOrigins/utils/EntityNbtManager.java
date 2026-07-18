package fr.senssi.karakOrigins.utils;

import fr.senssi.karakOrigins.KarakOrigins;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class EntityNbtManager {

    public static void setStringData(Entity entity, String key, String value) {
        PersistentDataContainer container = entity.getPersistentDataContainer();
        container.set(new NamespacedKey(KarakOrigins.plugin, key), PersistentDataType.STRING, value);
    }

    public static String getString(Player p, String key) {
        return p.getPersistentDataContainer().get(new NamespacedKey(KarakOrigins.plugin, key), PersistentDataType.STRING);
    }

    public static String getString(Entity entity, String key) {
        return entity.getPersistentDataContainer().get(new NamespacedKey(KarakOrigins.plugin, key), PersistentDataType.STRING);
    }
}
