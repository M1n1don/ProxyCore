package dev.m1n1don.proxycore.commands;

import dev.m1n1don.proxycore.ProxyCore;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class GlobalTell extends Command implements TabExecutor
{
    public GlobalTell(String command)
    {
        super(command);
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        if (args.length >= 2)
        {
            final ProxiedPlayer p = (ProxiedPlayer) sender;
            final ProxiedPlayer target = ProxyCore.getPlugin().getProxy().getPlayer(args[0]) == null ? null : ProxyCore.getPlugin().getProxy().getPlayer(args[0]);

            if (target == null)
            {
                sender.sendMessage(ChatColor.RED + "そのプレイヤーは存在しません。");
                return;
            }

            if (target == p)
            {
                sender.sendMessage(ChatColor.RED + "自分自身には送信できません。");
                return;
            }

            StringBuilder message = new StringBuilder();
            for (int i = 1; i < args.length; i++) message.append(args[i]).append(" ");

            target.sendMessage(
                    new TextComponent(
                            ChatColor.GRAY + "[" + sender.getName() + " (" + getServerName(p) + ") -> " + target.getName() + " (" + getServerName(target) + ") ] " + message.toString().trim().replaceAll(args[0], "")
                    )
            );

            p.sendMessage(
                    new TextComponent(
                            ChatColor.GRAY + "[" + sender.getName() + " (" + getServerName(p) + ") -> " + target.getName() + " (" + getServerName(target) + ") ] " + message.toString().trim().replaceAll(args[0], "")
                    )
            );
        }
        else sender.sendMessage(ChatColor.RED + "使い方: /gtell <player> <message>");
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args)
    {
        List<String> players = new ArrayList<>();

        if (args.length == 1) for (ProxiedPlayer player : ProxyCore.getPlugin().getProxy().getPlayers()) players.add(player.getName());
        return players;
    }

    public String getServerName(ProxiedPlayer player)
    {
        return player.getServer().getInfo().getName();
    }
}