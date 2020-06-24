package me.codingcookie8.modtools.commands;

import me.codingcookie8.modtools.ModTools;
import me.codingcookie8.modtools.commands.subcommands.SubCommandHelp;
import me.codingcookie8.modtools.commands.subcommands.chatsubcommands.ChatSubCommandClear;
import me.codingcookie8.modtools.commands.subcommands.chatsubcommands.ChatSubCommandSlow;
import me.codingcookie8.modtools.files.messages.GetMsgsFile;
import me.codingcookie8.modtools.permissions.PermissionHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class CommandModTools implements CommandExecutor {

    private final ModTools plugin;
    private GetMsgsFile messagesFile;
    private PermissionHandler pH;

    private SubCommandHelp subCommandHelp;
    private ChatSubCommandClear chatSubCommandClear;
    private ChatSubCommandSlow chatSubCommandSlow;

    public CommandModTools(ModTools plugin){
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        messagesFile = new GetMsgsFile();
        if(!(sender instanceof Player)){
            sender.sendMessage(RED + "All ModTools commands must be run in game.");
        }

        subCommandHelp = new SubCommandHelp();
        chatSubCommandClear = new ChatSubCommandClear();
        chatSubCommandSlow = new ChatSubCommandSlow();

        Player p = (Player) sender;
        if((args.length == 0) || ((args.length == 1) && args[0].equalsIgnoreCase("help"))){
            subCommandHelp.help(p);
        }
        if(args.length == 2){
            if(!(args[0].equalsIgnoreCase("chat"))){
                return true;
            }
            if(args[1].equalsIgnoreCase("clear")){
                chatSubCommandClear.clearChat(p);
            }
            if(args[1].equalsIgnoreCase("slow")){
                p.sendMessage(RED + "Incorrect usage of command. Usage:");
                p.sendMessage(RED + "/mt chat slow <seconds>");
                return true;
            }
        }
        if(args.length == 3){
            if(args[1].equalsIgnoreCase("slow")){
                if(args[2].equalsIgnoreCase("off")){
                    chatSubCommandSlow.slowModeOff(p);
                    return true;
                }
                else {
                    int seconds = 0;
                    try {
                        seconds = Integer.parseInt(args[2]);
                    } catch (NumberFormatException ex) {
                        p.sendMessage(RED + "Last argument must be an integer or 'off'");
                        return true;
                    }
                    chatSubCommandSlow.slowModeOn(p, seconds);
                    return true;
                }
            }
        }
        return false;
    }
}
