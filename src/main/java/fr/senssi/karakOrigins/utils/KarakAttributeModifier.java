package fr.senssi.karakOrigins.utils;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBTCompoundList;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class KarakAttributeModifier {
    /**
     * Supprime TOUS les attributs personnalisés d'un objet (redevient un item de base)
     */
    public static void clearAllAttributes(ItemStack item) {
        NBT.modify(item, nbt -> {
            nbt.removeKey("AttributeModifiers");
            // On peut aussi enlever le flag de cache des stats si on veut
            int flags = nbt.getInteger("HideFlags");
            nbt.setInteger("HideFlags", flags & ~64);
        });
    }


    /**
     * Applique un attribut de manière simple
     */
    public static void addAttribute(ItemStack item, Attribute attr, double amount, AttributeModifier.Operation op, EquipmentSlot slot) {
        NBT.modify(item, nbt -> {
            ReadWriteNBTCompoundList attrList = nbt.getCompoundList("AttributeModifiers");
            ReadWriteNBT modifier = attrList.addCompound();

            modifier.setString("AttributeName", attr.getKey().toString());
            modifier.setDouble("Amount", amount);
            modifier.setInteger("Operation", op.ordinal());
            modifier.setUUID("UUID", UUID.randomUUID());
            modifier.setString("Slot", slot.name().toLowerCase().replace("_", ""));
            modifier.setString("Name", "KarakTrait"); // Pas nécessaire.
        });
    }
}
