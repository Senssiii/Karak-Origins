package fr.senssi.karakOrigins.skill;

import de.tr7zw.changeme.nbtapi.NBT;
import fr.senssi.karakOrigins.utils.EntityNbtManager;
import org.bukkit.entity.Player;

public class SkillManager {

    public boolean hasSkill(Player player, Skill skill) {
        return EntityNbtManager.getBooleanData(player, skill.getId());
    }

    public void setSkill(Player player, Skill skill,boolean value) {
        NBT.modifyPersistentData(player, e -> {
            e.setBoolean(skill.getId(), value);
        });
    }
}
