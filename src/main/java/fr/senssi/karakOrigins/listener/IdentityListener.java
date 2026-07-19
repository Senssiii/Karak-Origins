package fr.senssi.karakOrigins.listener;

import fr.senssi.karakOrigins.identity.Identity;
import fr.senssi.karakOrigins.identity.IdentityManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IdentityListener implements Listener {

    private final Map<UUID, Identity> deadPlayersCache = new HashMap<>();

    // On redonne l'identité au joueur qui meurt.
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Identity identity = IdentityManager.getOrCreateIdentity(player);

        deadPlayersCache.put(player.getUniqueId(), identity);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player newPlayer = event.getPlayer();
        UUID uuid = newPlayer.getUniqueId();


        if (deadPlayersCache.containsKey(uuid)) {
            Identity identity = deadPlayersCache.get(uuid);
            identity.setPlayer(newPlayer);
            identity.save();
        }
    }
}
