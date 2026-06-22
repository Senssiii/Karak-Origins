package fr.senssi.karakOrigins.commands.mj;

import dev.triumphteam.gui.guis.Gui;
import fr.senssi.karakOrigins.commands.SimpleCommand;
import fr.senssi.karakOrigins.skill.craft.forge.soufflet.SouffletGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;

public class TestCommand extends SimpleCommand {
    public TestCommand() {
        super("karaktest", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        // On fait ce qu'on veut ici
        Gui gui = SouffletGUI.getGui();
        gui.open((HumanEntity) sender);
    }
}
