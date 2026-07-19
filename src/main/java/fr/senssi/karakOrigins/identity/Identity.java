package fr.senssi.karakOrigins.identity;

import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import org.bukkit.entity.Player;

import static fr.senssi.karakOrigins.utils.EntityNbtManager.setStringData;

public class Identity {
    public String nom;
    public String prenom;
    public String age;
    public String origine;
    private Player player;

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

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return getNomPrenom() + " " + age + " ans, de " + origine + ".";
    }

    public void save() {
        setStringData(player, NBTKeys.NOM, nom);
        setStringData(player, NBTKeys.PRENOM, prenom);
        setStringData(player, NBTKeys.AGE, age);
        setStringData(player, NBTKeys.ORIGINE, origine);
    }
}
