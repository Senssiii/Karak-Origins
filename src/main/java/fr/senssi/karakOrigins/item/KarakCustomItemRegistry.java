package fr.senssi.karakOrigins.item;

import java.util.HashMap;
import java.util.Map;

public class KarakCustomItemRegistry {
    public static Map<String, KarakCustomItem> items = new HashMap<>();

    public static void register(KarakCustomItem item) {
        items.put(item.id, item);
    }

    public static void onEnable() {
        register(new Handgonne());
    }
}
