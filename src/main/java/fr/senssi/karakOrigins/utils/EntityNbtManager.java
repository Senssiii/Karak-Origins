package fr.senssi.karakOrigins.utils;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class EntityNbtManager {

    public static void setStringData(Entity entity, String key, String value) {
        NBT.modifyPersistentData(entity, (Consumer<ReadWriteNBT>) nbt -> nbt.setString(key, value));
    }

    public static String getStringData(Player p, String key) {
        return NBT.getPersistentData(p, nbt -> nbt.getString(key));
    }

    public static String getStringData(Entity entity, String key) {
        return NBT.getPersistentData(entity, nbt -> nbt.getString(key));
    }

    public static boolean getBooleanData(Player p, String key) {
        return NBT.getPersistentData(p, nbt -> nbt.getBoolean(key));
    }
}
