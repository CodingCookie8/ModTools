package me.codingcookie8.modtools.commands.subcommands;

import javafx.util.StringConverter;
import me.codingcookie8.modtools.ModTools;
import me.codingcookie8.modtools.files.messages.GetMsgsFile;
import me.codingcookie8.modtools.permissions.PermissionHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class SubCommandInvsee {

    private GetMsgsFile messagesFile;
    private PermissionHandler pH;

    private final ModTools plugin;

    public SubCommandInvsee(ModTools plugin){
        this.plugin = plugin;
    }

    public void invSee(Player p, String args) {
        pH = new PermissionHandler();
        messagesFile = new GetMsgsFile();
        if (!(pH.checkPermission(p, "modtools.inventory.main"))) {
            messagesFile.checkAndGetPermissionsMsg(p);
            return;
        }

        Player target = Bukkit.getPlayerExact(args);

        if((target == null)){
            if(messagesFile.getEnabled("no-player", true)){
                String message = ChatColor.translateAlternateColorCodes('&', messagesFile.getMessage("no-player", "&cCan't find player."));
                Bukkit.broadcastMessage(message.replace("%sender%", p.getName()).replace("%target%", args));
                return;
            }
            return;
        }
        if(pH.checkPermission(target, "modtools.inventory.exempt")){
            messagesFile.checkAndGetPermissionsMsg(p);
            return;
        }
        Inventory inv = target.getInventory();
        p.openInventory(inv);
    }
}
