package fr.senssi.karakOrigins;

import fr.senssi.karakOrigins.commands.CommandManager;
import fr.senssi.karakOrigins.commands.mj.*;
import fr.senssi.karakOrigins.listener.ChatListener;
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
        commandManager.register(new TestCommand());

        getServer().getPluginManager().registerEvents(new ChatListener(instance), instance);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
