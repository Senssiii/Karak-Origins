package fr.senssi.karakOrigins.item;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public abstract class KarakCustomItem {
    String id;

    public KarakCustomItem(String id) {
        this.id = id;
    }

    public abstract void onRightClick(PlayerInteractEvent event);

    public abstract void onLeftClick(PlayerInteractEvent event);

    public abstract void onEquip(PlayerInteractEvent event);

    public abstract void onHit(EntityDamageByEntityEvent event);

    public abstract void updateCustomFormatting(ItemStack self);
}
