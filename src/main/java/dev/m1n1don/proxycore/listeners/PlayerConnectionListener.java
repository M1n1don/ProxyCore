package dev.m1n1don.proxycore.listeners;

import dev.m1n1don.proxycore.ProxyCore;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerConnectionListener implements Listener
{

    @EventHandler
    public void onPlayerConnect(PostLoginEvent e)
    {
        if (ProxyCore.getPlugin().getConfig().getString("connect_message").isEmpty()) return;

        final ProxiedPlayer p = e.getPlayer();
        ProxyCore.getPlugin().getProxy().broadcast(new TextComponent(ProxyCore.getPlugin().getConfig().getString("connect_message").replaceAll("%PLAYER%", p.getName())));
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent e)
    {
        ProxiedPlayer p = e.getPlayer();
        ProxyCore.getPlugin().getProxy().broadcast(new TextComponent(ProxyCore.getPlugin().getConfig().getString("disconnect_message").replaceAll("%PLAYER%", p.getName())));
    }
}