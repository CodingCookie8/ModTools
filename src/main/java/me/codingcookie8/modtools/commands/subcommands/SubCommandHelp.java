package me.codingcookie8.modtools.commands.subcommands;

import me.codingcookie8.modtools.files.messages.GetMsgsFile;
import me.codingcookie8.modtools.permissions.PermissionHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class SubCommandHelp {

    private PermissionHandler pH;
    private GetMsgsFile messagesFile;

    public void help(Player p){
        pH = new PermissionHandler();
        messagesFile = new GetMsgsFile();
        if(!(pH.checkPermission(p, "modtools.help"))){
            messagesFile.checkAndGetPermissionsMsg(p);
            return;
        }
        p.sendMessage("");
        p.sendMessage(GOLD + "" + BOLD + "      ModTool Commands");
        sendHelpMessage(p, "modtools.punish.tempban", "<ban/tempban> <player name>");
        sendHelpMessage(p, "modtools.punish.tempmute", "<mute/tempmute> <player name>");;
        sendHelpMessage(p, "modtools.chat.*", "chat <clear/lock/slow>");
        sendHelpMessage(p, "modtools.punish.freeze", "<freeze> <player name>");
        sendHelpMessage(p, "modtools.punish.kickall", "kickall");
        sendHelpMessage(p, "modtools.inventory.*", "<invsee/echest>");
        sendHelpMessage(p, "modtools.chat.staff", "staffchat");
        sendHelpMessage(p, "modtools.disabledrops", "disabledrops");
        sendHelpMessage(p, "modtools.shutdown", "shutdown");
        sendHelpMessage(p, "modtools.vanish", "<vanish/spectate>");
        sendHelpMessage(p, "modtools.ticket.*", "ticket <read/respond>");
        p.sendMessage("");
    }

    void sendHelpMessage(Player p, String permission, String commandArgs){
        if(!(pH.checkPermission(p, permission)))
            p.sendMessage(GRAY + "/mt " + RED + commandArgs);
        else
            p.sendMessage(GRAY + "/mt " + GREEN + commandArgs);
    }

}
