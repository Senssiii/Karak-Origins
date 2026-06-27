package fr.senssi.karakOrigins.item;

import fr.senssi.karakOrigins.utils.Messenger;
import fr.senssi.karakOrigins.utils.items.ItemUtils;
import fr.senssi.karakOrigins.utils.keys.ArmeFeuKeys;
import fr.senssi.karakOrigins.utils.keys.HandgonneKeys;
import fr.senssi.karakOrigins.utils.keys.NBTKeys;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jspecify.annotations.NonNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Handgonne extends KarakCustomItem {
    public int maxRecharge = 5;
    public int maxMunition = 1;
    int damage = 3;
    String rechargeId = "poudre_canon";
    String munitionId = "balle";

    public Handgonne() {
        super("handgonne");
    }

    private static @NonNull String getRechargeText(ItemStack self) {
        String recharge_text = ChatColor.DARK_GRAY + "";

        int poudre = ItemUtils.getInt(self, HandgonneKeys.RECHARGE);
        switch (poudre) {
            case 0:
                recharge_text += "Canon vide.";
                break;
            case 1:
            case 2:
                recharge_text += "Fond de poudre noirci.";
                break;
            case 3:
            case 4:
                recharge_text += "Canon copieusement chargé.";
                break;
            case 5:
                recharge_text += "Chargé à ras bord !";
                break;
            default:
                recharge_text += "Quantité de poudre inconnue.";
                break;
        }
        return recharge_text;
    }

    private static @NonNull String getMunitionText(ItemStack self) {
        String munition_text = ChatColor.DARK_GRAY + "";
        int munition = ItemUtils.getInt(self, HandgonneKeys.MUNITION);
        switch (munition) {
            case 0:
                munition_text += "Aucune balle insérée.";
                break;
            case 1:
                munition_text += "Balle calée au fond du canon.";
                break;
            default:
                munition_text += "État de la munition inconnu.";
                break;
        }
        return munition_text;
    }

    private static void setCustomDescrition(ItemStack self) {
        String recharge_text = getRechargeText(self);
        String munition_text = getMunitionText(self);

        ItemUtils.setItemNbt(self, ArmeFeuKeys.RECHARGE_DESCRIPTION, recharge_text);
        ItemUtils.setItemNbt(self, ArmeFeuKeys.MUNITION_DESCRIPTION, munition_text);
    }

    @Override
    public void onRightClick(PlayerInteractEvent event) {
        // On recharge en supposant que l'item soit dans la main principale
        event.setCancelled(true);
        Player player = event.getPlayer();
        ItemStack handgonne = player.getInventory().getItemInMainHand();
        int poudre = ItemUtils.getInt(handgonne, HandgonneKeys.RECHARGE);
        int munition = ItemUtils.getInt(handgonne, HandgonneKeys.MUNITION);

        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
        if (itemInOffHand.getAmount() == 0) return;
        String itemOffHandId = ItemUtils.getString(itemInOffHand, NBTKeys.CRAFT_ID);

        if (Objects.equals(itemOffHandId, rechargeId)) {
            int amount = itemInOffHand.getAmount();
            if (poudre + 1 > maxRecharge) return;
            Messenger.sendPersonnalNarrationMessage("Vous rechargez l'arme...", player);

            itemInOffHand.setAmount(amount - 1);

            ItemUtils.setItemNbt(handgonne, HandgonneKeys.RECHARGE, poudre + 1);
            updateCustomFormatting(handgonne);
        } else if (Objects.equals(itemOffHandId, munitionId)) {
            int amount = itemInOffHand.getAmount();
            if (munition + 1 > maxMunition) return;
            itemInOffHand.setAmount(amount - 1);
            Messenger.sendPersonnalNarrationMessage("Vous rechargez l'arme...", player);

            ItemUtils.setItemNbt(handgonne, HandgonneKeys.MUNITION, munition + 1);
            updateCustomFormatting(handgonne);
        }
    }

    @Override
    public void onLeftClick(PlayerInteractEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        ItemStack handgonne = player.getInventory().getItemInMainHand();

        // On utilise les recharges
        int poudre = ItemUtils.getInt(handgonne, HandgonneKeys.RECHARGE);
        int munition = ItemUtils.getInt(handgonne, HandgonneKeys.MUNITION);
        if (poudre <= 0 || munition <= 0) return;
        ItemUtils.setItemNbt(handgonne, HandgonneKeys.MUNITION, 0);
        ItemUtils.setItemNbt(handgonne, HandgonneKeys.RECHARGE, 0);

        // On tire pour de vrai
        Entity entityTouched = getShotEntity(player);
        shootAt(player, entityTouched, poudre);
        updateCustomFormatting(handgonne);
    }

    private void shootAt(Player player, Entity entityTouched, int poudre) {
        if (entityTouched instanceof LivingEntity) {

            LivingEntity livingEntity = (LivingEntity) entityTouched;

            hitWithBullet(player, livingEntity, poudre);

            if (livingEntity instanceof Player) {
                Player touchedPlayer = (Player) livingEntity;

                Messenger.sendPersonnalNarrationMessage("Vous avez été touché par balle.", touchedPlayer);

                setEffect(touchedPlayer);
            }
        }
        spawnShotParticles(player);
        playShotSound(player);
    }

    private void hitWithBullet(Player shooter, LivingEntity livingEntity, int poudre) {
        giveDamage(livingEntity, poudre);
        float damageYaw = calculateDamageYaw(shooter.getLocation(), livingEntity.getLocation());
        livingEntity.playHurtAnimation(damageYaw);
        World world = livingEntity.getLocation().getWorld();
        if (world == null) return;
        world.playSound(livingEntity.getLocation(), Sound.BLOCK_ANVIL_HIT, 1f, 1.5f);
    }

    /**
     * Calcule l'angle directionnel du dégât par rapport à l'orientation de la victime.
     */
    private float calculateDamageYaw(Location shooterLoc, Location victimLoc) {
        double dx = shooterLoc.getX() - victimLoc.getX();
        double dz = shooterLoc.getZ() - victimLoc.getZ();

        double angleTowardsShooter = Math.toDegrees(Math.atan2(dz, dx)) - 90.0;

        double relativeAngle = angleTowardsShooter - victimLoc.getYaw();

        return (float) ((relativeAngle % 360 + 360) % 360);
    }

    private void giveDamage(LivingEntity livingEntity, int poudre) {
        double health = livingEntity.getHealth();
        Random random = new Random();
        livingEntity.setHealth(Math.max(health - (damage * poudre) - random.nextInt(damage), 0.0));
    }

    private Entity getShotEntity(Player player) {
        World world = player.getWorld();
        Location eyeLocation = player.getEyeLocation();
        Vector direction = eyeLocation.getDirection();
        double maxDistance = 150.0; // 150m max
        double entityRayRadius = 0.5; // Marge d'erreur autour du rayon pour toucher une entité
        RayTraceResult result = world.rayTrace(
                eyeLocation,
                direction,
                maxDistance,
                FluidCollisionMode.NEVER,
                true,
                entityRayRadius,
                entity -> !entity.getUniqueId().equals(player.getUniqueId())
        );
        return result != null ? result.getHitEntity() : null; // Pour éviter les null Pointer Exception
    }

    private void setEffect(Player player) {
        PotionEffect slowness = new PotionEffect(
                PotionEffectType.SLOW,
                15 * 20, // 15 secondes
                0                // Lenteur I
        );

        PotionEffect blindness = new PotionEffect(
                PotionEffectType.BLINDNESS,
                5 * 20,  // 5 secondes
                0                // Blindness
        );

        player.addPotionEffect(slowness);
        player.addPotionEffect(blindness);
    }

    private void playShotSound(Player player) {
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
    }

    private void spawnShotParticles(Player player) {
        World world = player.getWorld();

        Location eyeLocation = player.getEyeLocation();

        Vector direction = eyeLocation.getDirection();
        Location spawnLocation = eyeLocation.add(direction.multiply(1.2));

        world.spawnParticle(
                Particle.CAMPFIRE_COSY_SMOKE,
                spawnLocation,
                22,
                0.1, 0.1, 0.1,
                0.05
        );

        world.spawnParticle(
                Particle.SMOKE_LARGE,
                spawnLocation,
                10,
                0.05, 0.05, 0.05,
                0.01
        );
    }

    @Override
    public void onEquip(PlayerInteractEvent event) {

    }

    @Override
    public void onHit(EntityDamageByEntityEvent event) {
        // Juste un coup de bâton pas besoin de faire plus
    }

    @Override
    public void updateCustomFormatting(ItemStack self) {
        setCustomDescrition(self);

        String string = ItemUtils.getString(self, NBTKeys.DESCRIPTION);
        String string1 = ItemUtils.getString(self, ArmeFeuKeys.RECHARGE_DESCRIPTION);
        String string2 = ItemUtils.getString(self, ArmeFeuKeys.MUNITION_DESCRIPTION);

        ItemMeta itemMeta = self.getItemMeta();
        if (itemMeta == null) return;
        itemMeta.setLore(Arrays.asList(string, string1, string2));
        self.setItemMeta(itemMeta);
    }
}
