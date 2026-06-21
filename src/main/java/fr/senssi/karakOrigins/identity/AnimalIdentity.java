package fr.senssi.karakOrigins.identity;

import fr.senssi.karakOrigins.utils.EntityNbtManager;
import fr.senssi.karakOrigins.utils.keys.AnimalKeys;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class AnimalIdentity {
    private final UUID entityId;
    private String name;
    private String breed; // Race (ex: "Berger Allemand", "Siamois")
    private Gender gender;

    public AnimalIdentity(Entity entity, String name, String breed, Gender gender) {
        if (entity == null) {
            throw new IllegalArgumentException("L'entité ne peut pas être nulle !");
        }
        this.entityId = entity.getUniqueId();
        this.name = name;
        this.breed = breed;
        this.gender = gender;
    }

    // Constructeur si l'animal à déjà des données
    public AnimalIdentity(Entity entity) {
        this(
                entity,
                EntityNbtManager.getStringData(entity, AnimalKeys.NOM),
                EntityNbtManager.getStringData(entity, AnimalKeys.RACE),
                AnimalManager.stringToGender(EntityNbtManager.getStringData(entity, AnimalKeys.SEXE))
        );
    }

    public void saveAnimal() {
        Entity e = Bukkit.getEntity(this.entityId);
        if (e == null) return;

        EntityNbtManager.setStringData(e, AnimalKeys.NOM, name);
        EntityNbtManager.setStringData(e, AnimalKeys.RACE, breed);
        EntityNbtManager.setStringData(e, AnimalKeys.SEXE, AnimalManager.genderToString(gender));
    }

    public UUID getEntityId() {
        return entityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
