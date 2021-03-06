package me.codingcookie8.modtools.commands.subcommands.chatsubcommands;

import me.codingcookie8.modtools.ModTools;
import me.codingcookie8.modtools.files.messages.GetMsgsFile;
import me.codingcookie8.modtools.permissions.PermissionHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatSubCommandLock {

    private GetMsgsFile messagesFile;
    private PermissionHandler pH;

    private final ModTools plugin;

    public ChatSubCommandLock(ModTools plugin){
        this.plugin = plugin;
    }

    public void lockChat(Player p) {
        pH = new PermissionHandler();
        messagesFile = new GetMsgsFile();
        if (!(pH.checkPermission(p, "modtools.chat.lock"))) {
            messagesFile.checkAndGetPermissionsMsg(p);
            return;
        }

        if(ModTools.getLockUtil().isLockEnabledConfig()){
            ModTools.getLockUtil().setLockEnabledConfig(false);
            ModTools.getLockUtil().setLockEnabledHashMap(false);

            if (messagesFile.getEnabled("lock-mode-off", true)) {
                String message = ChatColor.translateAlternateColorCodes('&', messagesFile.getMessage("lock-mode-off", "&cChat has been unlocked."));
                Bukkit.broadcastMessage(message.replace("%sender%", p.getName()));
            }
        } else if(!ModTools.getLockUtil().isLockEnabledConfig()) {
            ModTools.getLockUtil().setLockEnabledConfig(true);
            ModTools.getLockUtil().setLockEnabledHashMap(true);

            if (messagesFile.getEnabled("lock-mode-on", true)) {
                String message = ChatColor.translateAlternateColorCodes('&', messagesFile.getMessage("lock-mode-on", "&cChat has been locked!"));
                Bukkit.broadcastMessage(message.replace("%sender%", p.getName()));
            }
        }
    }
}
