package dev.m1n1don.proxycore;

import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class AbstractProxyCore extends Plugin
{
    protected void registerListeners(Listener... listeners)
    {
        for (Listener listener : listeners) getProxy().getPluginManager().registerListener(this, listener);
    }

    protected void registerCommands(Command... commands)
    {
        for (Command command : commands) getProxy().getPluginManager().registerCommand(this, command);
    }
}