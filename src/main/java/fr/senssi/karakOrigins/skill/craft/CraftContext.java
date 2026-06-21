package fr.senssi.karakOrigins.skill.craft;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CraftContext {
    private final Player player;
    private final Block block;
    private final List<ItemStack> inputs;

    private final Map<String, Object> metadata = new HashMap<>();

    public CraftContext(Player player, Block block, List<ItemStack> inputs) {
        this.player = player;
        this.block = block;
        this.inputs = inputs;
    }

    public void addData(String key, Object value) {
        metadata.put(key, value);
    }

    // Récupère une donnée avec une valeur par défaut si elle n'existe pas
    public <T> T getData(String key, Class<T> clazz, T defaultValue) {
        Object val = metadata.get(key);
        if (clazz.isInstance(val)) {
            return clazz.cast(val);
        }
        return defaultValue;
    }
    //     int currentTemp = context.getData("temperature", Integer.class, 0);

    // Getters standards
    public Player getPlayer() {
        return player;
    }

    public Block getBlock() {
        return block;
    }

    public List<ItemStack> getInputs() {
        return inputs;
    }
}
