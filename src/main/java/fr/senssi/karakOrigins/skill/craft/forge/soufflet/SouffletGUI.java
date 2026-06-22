package fr.senssi.karakOrigins.skill.craft.forge.soufflet;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import fr.senssi.karakOrigins.KarakOrigins;
import fr.senssi.karakOrigins.utils.GuiUtils;
import fr.senssi.karakOrigins.utils.items.ItemFormatter;
import fr.senssi.karakOrigins.utils.items.ItemUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NonNull;

public class SouffletGUI {
    public static Gui getGui() {
        Soufflet soufflet = new Soufflet(0, new ItemStack(Material.IRON_ORE), 0);

        Gui gui = Gui.gui().title(Component.text("Soufflet de forge")).rows(1)
                .create();
        GuiUtils.fillWhiteGlassPaneGUI(gui);
        
        ItemStack head = getSouffletHead();

        gui.setItem(2, ItemBuilder.from(head).asGuiItem(event -> {
            event.setCancelled(true);
            soufflet.souffle();

            ItemFormatter.setDescription(head, "Souffle à " + soufflet.getTemperature() + "°C");
            ItemFormatter.updateItemFormatting(head);
            gui.updateItem(2, head);
        }));

        gui.setItem(6, ItemBuilder.from(Material.AIR).asGuiItem(event -> {
            event.setCancelled(false);
            Bukkit.getScheduler().runTask(KarakOrigins.instance, () -> {
                ItemStack itemInSlot = event.getInventory().getItem(6);

                if (itemInSlot != null && itemInSlot.getType() != Material.AIR) {
                    soufflet.item = itemInSlot;
                } else {
                    soufflet.item = null; // On a enlevé l'item
                }
            });
        }));
        return gui;
    }

    private static @NonNull ItemStack getSouffletHead() {
        ItemStack head = ItemUtils.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODU0YjRhYWI2MWRiZTBmOTI4NGQ0MjU4NjcwODkzYjU1N2MwOGIzMjY3NmIzNGM4NTkzMTU4MjZmY2UxNTU2ZiJ9fX0=");
        ItemFormatter.setName(head, "Soufflet");
        ItemFormatter.setDescription(head, "Soufflez pour réchauffer");
        ItemFormatter.updateItemFormatting(head);
        return head;
    }
}
