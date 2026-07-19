package fr.senssi.karakOrigins.commands.mj;

import fr.senssi.karakOrigins.commands.SimpleCommand;
import org.bukkit.command.CommandSender;

public class TestCommand extends SimpleCommand {
    public TestCommand() {
        super("karaktest", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        // On fait ce qu'on veut ici
        // Gui gui = SouffletGUI.getGui();
        // gui.open((HumanEntity) sender);
    }
}
