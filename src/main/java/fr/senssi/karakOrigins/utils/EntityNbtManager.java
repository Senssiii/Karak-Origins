package fr.senssi.karakOrigins.utils;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.entity.Player;

public class EntityNbtManager {

    public static String getStringData(Player p, String key) {
        return NBT.getPersistentData(p, nbt -> nbt.getString(key));
    }

    public static boolean getBooleanData(Player p, String key) {
        return NBT.getPersistentData(p, nbt -> nbt.getBoolean(key));
    }
}
