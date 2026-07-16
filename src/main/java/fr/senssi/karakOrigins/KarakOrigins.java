package fr.senssi.karakOrigins;

import fr.senssi.karakOrigins.commands.CommandManager;
import fr.senssi.karakOrigins.commands.mj.IdentityCommand;
import fr.senssi.karakOrigins.commands.mj.NarrationCommand;
import fr.senssi.karakOrigins.commands.mj.StopPlayerCommand;
import fr.senssi.karakOrigins.commands.mj.TestCommand;
import fr.senssi.karakOrigins.commands.mj.item.ItemFormatCommand;
import fr.senssi.karakOrigins.commands.mj.item.SetItemMessageCommand;
import fr.senssi.karakOrigins.commands.player.UseCommand;
import fr.senssi.karakOrigins.listener.ChatListener;
import fr.senssi.karakOrigins.mechanic.KarakOraxenMechanics;
import org.bukkit.plugin.PluginManager;
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
        commandManager.register(new SetItemMessageCommand());
        commandManager.register(new UseCommand());

        commandManager.register(new TestCommand());
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ChatListener(instance), instance);

        KarakOraxenMechanics mechanicsPlugin = new KarakOraxenMechanics();
        mechanicsPlugin.onEnable();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
