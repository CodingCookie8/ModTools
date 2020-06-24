package me.codingcookie8.modtools.commands.subcommands.chatsubcommands;

import me.codingcookie8.modtools.files.messages.GetMsgsFile;
import me.codingcookie8.modtools.permissions.PermissionHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatSubCommandClear {

    private GetMsgsFile messagesFile;
    private PermissionHandler pH;

    public void clearChat(Player p){
        pH = new PermissionHandler();
        messagesFile = new GetMsgsFile();
        if(!(pH.checkPermission(p, "modtools.chat.clear"))){
            messagesFile.checkAndGetPermissionsMsg(p);
            return;
        }

        for(int i=0; i<100; i++) {
            if(i == 97){
                if(messagesFile.getEnabled("cleared-chat", true)){
                    String message = ChatColor.translateAlternateColorCodes('&', messagesFile.getMessage("cleared-chat", "&6&lChat has been cleared by %sender%").replaceAll("%sender%", p.getName()));
                    Bukkit.broadcastMessage(message);
                }
            }
            Bukkit.broadcastMessage("");
        }
    }
}
