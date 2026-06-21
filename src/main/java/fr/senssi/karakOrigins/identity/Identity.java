package fr.senssi.karakOrigins.identity;

import de.tr7zw.changeme.nbtapi.NBT;
import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import org.bukkit.entity.Player;

public class Identity {
    private final Player player;
    public String nom;
    public String prenom;
    public String age;
    public String origine;

    public Identity(String nom, String prenom, String age, String origine, Player player) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.origine = origine;
        this.player = player;
    }

    public String getNomPrenom() {
        return String.format("%s %s", nom, prenom);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return getNomPrenom() + " " + age + " ans, de " + origine + ".";
    }

    public void save() {
        NBT.modifyPersistentData(player, nbt -> {
            nbt.setString(NBTKeys.NOM, nom);
            nbt.setString(NBTKeys.PRENOM, prenom);
            nbt.setString(NBTKeys.AGE, age);
            nbt.setString(NBTKeys.ORIGINE, origine);
        });
    }
}
