package me.codingcookie8.modtools.commands.subcommands;

import me.codingcookie8.modtools.ModTools;
import me.codingcookie8.modtools.files.messages.GetMsgsFile;
import me.codingcookie8.modtools.gui.chat.ChatGUI;
import me.codingcookie8.modtools.permissions.PermissionHandler;
import org.bukkit.entity.Player;

public class SubCommandChat {

    private final ModTools plugin;

    private GetMsgsFile messagesFile;
    private PermissionHandler pH;
    private ChatGUI chatGUI;

    public SubCommandChat(ModTools plugin){
        this.plugin = plugin;
    }

    public void commandChatGUI(Player p) {
        pH = new PermissionHandler();
        messagesFile = new GetMsgsFile();
        if((pH.checkPermission(p, "modtools.chat.clear")) ||
                (pH.checkPermission(p, "modtools.chat.slow")) ||
                        (pH.checkPermission(p, "modtools.chat.lock"))) {
            chatGUI = new ChatGUI(plugin);
            chatGUI.setChatGUI(p);
        }else{
            messagesFile.checkAndGetPermissionsMsg(p);
            return;
        }
    }
}
