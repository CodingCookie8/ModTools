package me.codingcookie8.modtools.gui.chat;

import me.codingcookie8.modtools.ModTools;
import me.codingcookie8.modtools.permissions.PermissionHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static org.bukkit.ChatColor.*;

public class ChatGUI {

    private ModTools plugin;

    public ChatGUI(ModTools plugin) {
        this.plugin = plugin;
        invChat = Bukkit.createInventory(null, 18, "Chat Settings");
    }

    private Inventory invChat;
    private ChatItems chatItems;
    private PermissionHandler pH;

    public void openChatGUI(final HumanEntity ent) {
        ent.openInventory(getChatGUI());
    }

    public Inventory getChatGUI() {
        return invChat;
    }

    public void setChatGUI(Player p){
        chatItems = new ChatItems(plugin);
        pH = new PermissionHandler();

        getChatGUI().setItem(4, chatItems.makeChatSettingsItem());
        if(pH.checkPermission(p, "modtools.chat.clear"))
            getChatGUI().setItem(11, chatItems.makeClearItem(GREEN));
        else
            getChatGUI().setItem(11, chatItems.makeClearItem(RED));

        if(pH.checkPermission(p, "modtools.chat.slow"))
            getChatGUI().setItem(13, chatItems.makeSlowItem(GREEN));
        else
            getChatGUI().setItem(13, chatItems.makeSlowItem(RED));

        if(pH.checkPermission(p, "modtools.chat.lock"))
            getChatGUI().setItem(15, chatItems.makeLockItem(GREEN));
        else
            getChatGUI().setItem(15, chatItems.makeLockItem(RED));

        openChatGUI(p);
    }
}
