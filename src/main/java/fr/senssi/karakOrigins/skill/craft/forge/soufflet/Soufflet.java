package fr.senssi.karakOrigins.skill.craft.forge.soufflet;

import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Soufflet {
    public ItemStack item;
    private int temperature;
    private int startTime;

    public Soufflet(int temperature, ItemStack item, int startTime) {
        this.temperature = temperature;
        this.item = item;
        this.startTime = startTime;
    }

    public void souffle() {
        // se modifie en fonction du skill du joueur ?
        temperature += new Random().nextInt(5, 10);
    }

    public int getTemperature() {
        return temperature;
    }
}
