package me.codingcookie8.modtools.commands.subcommands;

import me.codingcookie8.modtools.ModTools;
import me.codingcookie8.modtools.files.messages.GetMsgsFile;
import me.codingcookie8.modtools.gui.ban.BanGUI;
import me.codingcookie8.modtools.gui.chat.ChatGUI;
import me.codingcookie8.modtools.permissions.PermissionHandler;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class SubCommandBan {

    private final ModTools plugin;

    private GetMsgsFile messagesFile;
    private PermissionHandler pH;
    private BanGUI banGUI;

    public SubCommandBan(ModTools plugin){
        this.plugin = plugin;
    }

    public void commandBanGUI(Player p, OfflinePlayer targetedOfflinePlayer) {
        pH = new PermissionHandler();
        messagesFile = new GetMsgsFile();
        if((pH.checkPermission(p, "modtools.punish.ban")) ||
                (pH.checkPermission(p, "modtools.punish.tempban"))) {
            banGUI = new BanGUI(plugin);
            banGUI.setBanGUI(p, targetedOfflinePlayer);
        }else{
            messagesFile.checkAndGetPermissionsMsg(p);
            return;
        }
    }
}
