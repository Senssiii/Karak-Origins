package fr.senssi.karakOrigins.identity;

import fr.senssi.karakOrigins.utils.EntityNbtManager;
import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import org.bukkit.entity.Player;

public class IdentityManager {
    // For reading/storing custom data on (block-)entities, you should use methods that end with PersistentData.
    // https://github.com/tr7zw/Item-NBT-API/wiki/Using-the-NBT-API#working-with-entities-and-block-entities
    public static Identity setDefaultIdentity(Player p) {
        Identity identity = new Identity("Irmanov", "Jean", "40", "Léos", p);
        identity.save();
        return identity;
    }

    public static Identity getIdentity(Player p) {
        String age = EntityNbtManager.getString(p, NBTKeys.AGE);
        String nom = EntityNbtManager.getString(p, NBTKeys.NOM);
        String prenom = EntityNbtManager.getString(p, NBTKeys.PRENOM);
        String origine = EntityNbtManager.getString(p, NBTKeys.ORIGINE);
        if (age == null || nom == null || prenom == null || origine == null || age.isEmpty() || nom.isEmpty() || prenom.isEmpty()) {
            return setDefaultIdentity(p);
        }
        return new Identity(nom, prenom, age, origine, p);
    }
}
