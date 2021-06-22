package dev.m1n1don.proxycore;

import dev.m1n1don.proxycore.commands.GlobalTell;
import dev.m1n1don.proxycore.listeners.PlayerConnectionListener;
import dev.m1n1don.proxycore.listeners.ServerKickListener;
import dev.m1n1don.proxycore.utils.CustomConfig;
import net.md_5.bungee.config.Configuration;

public class ProxyCore extends AbstractProxyCore
{
    private static ProxyCore plugin;

    private CustomConfig config;

    @Override
    public void onEnable()
    {
        plugin = this;

        config = new CustomConfig(this);
        config.saveDefault();

        registerListeners(
                new PlayerConnectionListener(),
                new ServerKickListener()
        );

        registerCommands(
                new GlobalTell("gtell")
        );
    }

    @Override
    public void onDisable()
    {
    }

    public static ProxyCore getPlugin()
    {
        return plugin;
    }

    public CustomConfig getCustomConfig()
    {
        return config;
    }

    public Configuration getConfig()
    {
        return config.getConfiguration();
    }
}