package me.codingcookie8.modtools.commands.subcommands.chatsubcommands;

import me.codingcookie8.modtools.files.messages.GetMsgsFile;
import me.codingcookie8.modtools.permissions.PermissionHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatSubCommandSlow {

    private GetMsgsFile messagesFile;
    private PermissionHandler pH;

    public void slowModeOn(Player p, int seconds) {
        pH = new PermissionHandler();
        messagesFile = new GetMsgsFile();
        if (!(pH.checkPermission(p, "modtools.chat.slow"))) {
            messagesFile.checkAndGetPermissionsMsg(p);
            return;
        }

        SlowVariables sV = new SlowVariables();
        sV.setSlowModeLengthConfig(seconds);
        sV.setSlowModeLengthHashMap(seconds);
        sV.setEnabledConfig(true);
        sV.setSlowModeEnabledHashMap(true);

        if(messagesFile.getEnabled("slow-mode-on", true)){
            String message = ChatColor.translateAlternateColorCodes('&', messagesFile.getMessage("slow-mode-on", "&cChat has been put into slow mode (%int% seconds)"));
            Bukkit.broadcastMessage(message.replace("%int%", String.valueOf(seconds)).replace("%sender%", p.getName()));
        }
    }

    public void slowModeOff(Player p){
        pH = new PermissionHandler();
        messagesFile = new GetMsgsFile();
        if (!(pH.checkPermission(p, "modtools.chat.slow"))) {
            messagesFile.checkAndGetPermissionsMsg(p);
            return;
        }

        SlowVariables sV = new SlowVariables();
        sV.setSlowModeLengthConfig(0);
        sV.setSlowModeLengthHashMap(0);
        sV.setEnabledConfig(false);
        sV.setSlowModeEnabledHashMap(false);

        messagesFile = new GetMsgsFile();
        if(messagesFile.getEnabled("slow-mode-off", true)){
            String message = ChatColor.translateAlternateColorCodes('&', messagesFile.getMessage("slow-mode-off", "&cChat has been taken out of slow mode."));
            Bukkit.broadcastMessage(message);
        }
    }

}
