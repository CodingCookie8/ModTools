package me.codingcookie8.modtools.commands;

import me.codingcookie8.modtools.ModTools;
import me.codingcookie8.modtools.files.messages.GetMsgsFile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandModTools implements CommandExecutor {

    private final ModTools plugin;
    private GetMsgsFile messagesFile;

    public CommandModTools(ModTools plugin){
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        messagesFile = new GetMsgsFile();
        if(!(sender.hasPermission("modtools"))){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagesFile.getMessage("no.permission", "&4&lYou do not have permission to perform that command.")));
            return true;
        }
        return false;
    }
}
