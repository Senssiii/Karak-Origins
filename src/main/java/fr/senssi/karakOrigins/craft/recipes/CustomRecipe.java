package fr.senssi.karakOrigins.craft.recipes;

import fr.senssi.karakOrigins.craft.CraftContext;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class CustomRecipe {
    private final String id;
    private final List<ItemStack> inputs;
    private final ItemStack result;
    private final int duration; // 0 = instantané

    public CustomRecipe(String id, List<ItemStack> inputs, ItemStack result, int duration) {
        this.id = id;
        this.inputs = inputs;
        this.result = result;
        this.duration = duration;
    }

    public abstract boolean canCraft(Player player, CraftContext context);
}
