package fr.senssi.karakOrigins;

import fr.senssi.karakOrigins.commands.CommandManager;
import fr.senssi.karakOrigins.commands.mj.IdentityCommand;
import fr.senssi.karakOrigins.commands.mj.NarrationCommand;
import fr.senssi.karakOrigins.commands.mj.StopPlayerCommand;
import fr.senssi.karakOrigins.commands.mj.TestCommand;
import fr.senssi.karakOrigins.commands.mj.item.CraftIdCommand;
import fr.senssi.karakOrigins.commands.mj.item.ItemFormatCommand;
import fr.senssi.karakOrigins.commands.mj.item.SetItemMessageCommand;
import fr.senssi.karakOrigins.commands.player.UseCommand;
import fr.senssi.karakOrigins.listener.ChatListener;
import fr.senssi.karakOrigins.listener.GUIOpener;
import org.bukkit.plugin.java.JavaPlugin;

public final class KarakOrigins extends JavaPlugin {
    public static KarakOrigins instance;

    @Override
    public void onEnable() {
        instance = this;
        CommandManager commandManager = new CommandManager(instance);

        commandManager.register(new StopPlayerCommand());
        commandManager.register(new NarrationCommand());
        commandManager.register(new ItemFormatCommand());
        commandManager.register(new IdentityCommand());
        commandManager.register(new CraftIdCommand());
        commandManager.register(new SetItemMessageCommand());
        commandManager.register(new UseCommand());

        commandManager.register(new TestCommand());
        getServer().getPluginManager().registerEvents(new ChatListener(instance), instance);
        getServer().getPluginManager().registerEvents(new GUIOpener(), instance);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
