package fr.senssi.karakOrigins.craft.recipes;

import fr.senssi.karakOrigins.craft.CraftContext;
import fr.senssi.karakOrigins.craft.TemperatureRequired;
import fr.senssi.karakOrigins.craft.ToolRequired;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ForgeRecipe extends CustomRecipe implements TemperatureRequired, ToolRequired {
    private final int temperature;
    private final ItemStack tool;

    public ForgeRecipe(String id, List<ItemStack> inputs, ItemStack result, int duration, int temperature, ItemStack tool) {
        super(id, inputs, result, duration);
        this.temperature = temperature;
        this.tool = tool;
    }

    @Override
    public boolean canCraft(Player player, CraftContext context) {
        return false;
    }

    @Override
    public int getRequiredTemperature() {
        return temperature;
    }

    @Override
    public ItemStack getToolRequired() {
        return tool;
    }
}
