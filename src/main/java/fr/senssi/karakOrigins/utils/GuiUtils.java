package fr.senssi.karakOrigins.utils;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

public class GuiUtils {
    public static void fillWhiteGlassPaneGUI(Gui gui) {
        gui.getFiller().fill(ItemBuilder.from(Material.WHITE_STAINED_GLASS_PANE).name(Component.text(""))
                .asGuiItem(event -> event.setCancelled(true)));
    }
}
