package me.codingcookie8.modtools.commands.subcommands.chatsubcommands;

import me.codingcookie8.modtools.ModTools;
import me.codingcookie8.modtools.files.messages.GetMsgsFile;
import me.codingcookie8.modtools.permissions.PermissionHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatSubCommandSlow {

    private GetMsgsFile messagesFile;
    private PermissionHandler pH;

    private final ModTools plugin;

    public ChatSubCommandSlow(ModTools plugin){
        this.plugin = plugin;
    }

    public void slowModeOn(Player p, int seconds) {
        pH = new PermissionHandler();
        messagesFile = new GetMsgsFile();
        if (!(pH.checkPermission(p, "modtools.chat.slow"))) {
            messagesFile.checkAndGetPermissionsMsg(p);
            return;
        }

        ModTools.getSlowUtil().setSlowModeLengthHashMap(seconds);
        ModTools.getSlowUtil().setSlowModeLengthConfig(seconds);
        ModTools.getSlowUtil().setEnabledConfig(true);
        ModTools.getSlowUtil().setSlowModeEnabledHashMap(true);

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

        ModTools.getSlowUtil().setSlowModeLengthConfig(0);
        ModTools.getSlowUtil().setSlowModeLengthHashMap(0);
        ModTools.getSlowUtil().setEnabledConfig(false);
        ModTools.getSlowUtil().setSlowModeEnabledHashMap(false);

        messagesFile = new GetMsgsFile();
        if(messagesFile.getEnabled("slow-mode-off", true)){
            String message = ChatColor.translateAlternateColorCodes('&', messagesFile.getMessage("slow-mode-off", "&cChat has been taken out of slow mode."));
            Bukkit.broadcastMessage(message);
        }
    }

}
