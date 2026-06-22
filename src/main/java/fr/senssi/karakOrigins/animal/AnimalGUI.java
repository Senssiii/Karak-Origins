package fr.senssi.karakOrigins.animal;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import fr.senssi.karakOrigins.utils.Messenger;
import fr.senssi.karakOrigins.utils.items.ItemFormatter;
import fr.senssi.karakOrigins.utils.items.ItemUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NonNull;

import static fr.senssi.karakOrigins.utils.GuiUtils.fillWhiteGlassPaneGUI;

public class AnimalGUI {
    public static void openAnimalGUI(Player player, Entity animal) {
        AnimalIdentity animalIdentity = new AnimalIdentity(animal);
        Gui gui = Gui.gui().title(Component.text("Animal")).rows(3)
                .create();
        fillAnimalGUI(gui, player, animalIdentity);

        gui.open(player);
    }

    private static void fillAnimalGUI(Gui gui, Player player, AnimalIdentity animalIdentity) {
        fillWhiteGlassPaneGUI(gui);
        setInfoIcon(gui, player, animalIdentity, 10);
        setButtonRename(gui, player, animalIdentity, 11);
    }

    private static void setInfoIcon(Gui gui, Player player, AnimalIdentity animalIdentity, int slot) {
        GuiItem item = getInfoIcon(animalIdentity);
        item.setAction(event -> event.setCancelled(true));
        gui.setItem(slot, item);
    }

    /**
     * Renvoie l'item avec toutes les informations sur l'animal.
     *
     * @param animalIdentity Les données de l'animal
     * @return L'item prêt à être placé dans le gui
     */
    private static GuiItem getInfoIcon(AnimalIdentity animalIdentity) {
        ItemStack head = ItemUtils.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmVhNDY4NWQ1NWFlYzk5NTVkZDg2OWI0OTViMmY1YjJmYjc0MzU4NzYxM2JhNTg3OTYwYzNhM2Q2NWYyNGE4YiJ9fX0=");
        String itemDescription = String.format("Nom : %s\\n Race : %s\\n Sexe : %s", animalIdentity.getName(), animalIdentity.getBreed(), animalIdentity.getGenderString());

        ItemFormatter.setDescription(head, itemDescription);
        ItemFormatter.updateItemFormatting(head);

        return ItemBuilder.from(head).asGuiItem();
    }

    private static void setButtonRename(Gui gui, Player player, AnimalIdentity animalIdentity, int slot) {
        GuiItem item = getButtonRename(player);
        item.setAction(event -> event.setCancelled(true));
        gui.setItem(slot, item);
    }

    private static @NonNull GuiItem getButtonRename(Player player) {
        ItemStack item = new ItemStack(Material.NAME_TAG);

        ItemFormatter.setName(item, "Renommer l'animal");
        ItemFormatter.updateItemFormatting(item);

        GuiItem guiItem = ItemBuilder.from(item)
                .asGuiItem(action -> Messenger.sendPersonnalNarrationMessage("Vous renommez l'animal", player));
        return guiItem;
    }
}
