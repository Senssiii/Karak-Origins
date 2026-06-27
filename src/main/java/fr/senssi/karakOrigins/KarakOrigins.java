package fr.senssi.karakOrigins;

import fr.senssi.karakOrigins.commands.CommandManager;
import fr.senssi.karakOrigins.commands.mj.*;
import fr.senssi.karakOrigins.commands.mj.item.CraftIdCommand;
import fr.senssi.karakOrigins.commands.mj.item.ItemFormatCommand;
import fr.senssi.karakOrigins.commands.mj.item.SetItemMessageCommand;
import fr.senssi.karakOrigins.commands.player.UseCommand;
import fr.senssi.karakOrigins.item.KarakCustomItemRegistry;
import fr.senssi.karakOrigins.listener.ChatListener;
import fr.senssi.karakOrigins.listener.GUIOpener;
import fr.senssi.karakOrigins.listener.ItemListener;
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
        commandManager.register(new CraftIdCommand());
        commandManager.register(new SetItemMessageCommand());
        commandManager.register(new UseCommand());
        commandManager.register(new ItemAttrCommand());

        commandManager.register(new TestCommand());
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ChatListener(instance), instance);
        pluginManager.registerEvents(new GUIOpener(), instance);
        pluginManager.registerEvents(new ItemListener(), instance);

        KarakCustomItemRegistry.onEnable();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
