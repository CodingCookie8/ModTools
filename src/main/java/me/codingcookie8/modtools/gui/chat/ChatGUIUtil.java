package me.codingcookie8.modtools.gui.chat;

import me.codingcookie8.modtools.files.messages.GetMsgsFile;
import me.codingcookie8.modtools.permissions.PermissionHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.bukkit.ChatColor.*;

public class ChatGUIUtil {

    private PermissionHandler pH;
    private GetMsgsFile messagesFile;

    private ArrayList<UUID> waitingForSlowInput;

    public ChatGUIUtil(){
        waitingForSlowInput = new ArrayList<UUID>();
    }

    public ArrayList<UUID> getWaitingForSlowInput(){
        return waitingForSlowInput;
    }

    public void sendSlowAlert(Player p){
        pH = new PermissionHandler();
        messagesFile = new GetMsgsFile();
        if (!(pH.checkPermission(p, "modtools.chat.slow"))) {
            messagesFile.checkAndGetPermissionsMsg(p);
            return;
        }

        getWaitingForSlowInput().add(p.getUniqueId());
        p.sendTitle(RED + "Input needed in chat", GOLD + "Please indicate the amount of wait time in " + GREEN + "seconds.", 5, 120, 5);
        p.sendMessage("");
        p.sendMessage(GREEN + "" + STRIKETHROUGH + "----------------------------------");
        p.sendMessage(GOLD + "Type in the amount of seconds a player must wait before sending another message. " + RED + "Do not " + GOLD + "include any forwards-slashes or quotations, just the number.");
        p.sendMessage(GREEN + "" + STRIKETHROUGH + "----------------------------------");
    }
}
