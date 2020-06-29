package me.codingcookie8.modtools.commands.tabcompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ModToolsTab implements TabCompleter {

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (cmd.getName().equalsIgnoreCase("modtools")) {
            if(args.length == 1) {
                if (sender instanceof Player) {
                    List<String> list = new ArrayList<>();
                    list.add("ban");
                    list.add("chat");
                    list.add("disabledrops");
                    list.add("echest");
                    list.add("help");
                    list.add("invsee");
                    list.add("mute");
                    list.add("spectate");
                    list.add("tempban");
                    list.add("tempmute");
                    list.add("vanish");
                    list.add("unmute");
                    list.add("unban");
                    list.add("staffchat");
                    return list;
                }
            }
            if(args.length == 2 && args[0].equalsIgnoreCase("chat")) {
                if (sender instanceof Player) {
                    List<String> list = new ArrayList<>();
                    list.add("clear");
                    list.add("lock");
                    list.add("slow");
                    return list;
                }
            }
        }

        return null;
    }

}
