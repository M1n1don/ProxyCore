package dev.m1n1don.proxycore.listeners;

import dev.m1n1don.proxycore.ProxyCore;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class ServerKickListener implements Listener
{

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onServerKick(ServerKickEvent e)
    {
        if (ProxyCore.getPlugin().getConfig().getString("kick_reason").isEmpty()) return;

        final ProxiedPlayer p = e.getPlayer();

        if (!ProxyCore.getPlugin().getConfig().contains("fallback") || !ProxyCore.getPlugin().getProxy().getServers().containsKey(ProxyCore.getPlugin().getConfig().getString("fallback"))) return;

        e.setCancelServer(ProxyCore.getPlugin().getProxy().getServerInfo(ProxyCore.getPlugin().getConfig().getString("fallback")));
        p.sendMessage(new TextComponent(ProxyCore.getPlugin().getConfig().getString("kick_reason").replaceAll("%MESSAGE%", e.getKickReason())));
        e.setCancelled(true);
    }
}