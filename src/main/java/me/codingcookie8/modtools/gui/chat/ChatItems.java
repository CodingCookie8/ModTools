package me.codingcookie8.modtools.gui.chat;

import me.codingcookie8.modtools.ModTools;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static org.bukkit.ChatColor.*;

public class ChatItems {

    private final ModTools plugin;

    private ItemStack chatSettingsItem;
    private ItemStack clearItem;
    private ItemStack slowItem;
    private ItemStack lockItem;

    public ChatItems(ModTools plugin) {
        this.plugin = plugin;
        makeChatSettingsItem();
        makeClearItem(GREEN);
        makeSlowItem(GREEN);
        makeLockItem(GREEN);
    }

    public ItemStack makeChatSettingsItem() {
        chatSettingsItem = new ItemStack(Material.PAPER);
        ItemMeta settingsMeta = chatSettingsItem.getItemMeta();
        settingsMeta.setDisplayName(WHITE + "" + BOLD + "Chat Settings");
        chatSettingsItem.setItemMeta(settingsMeta);
        return chatSettingsItem;
    }

    public ItemStack makeClearItem(ChatColor permissionColor) {
        clearItem = new ItemStack(Material.BUCKET);
        ItemMeta clearMeta = clearItem.getItemMeta();
        clearMeta.setDisplayName(permissionColor + "" + BOLD + "Clear Chat");
        clearMeta.setLore(Arrays.asList("",
                WHITE + "Clicking this will clear chat",
                WHITE + "for all players on the",
                WHITE + "server.",
                ""));
        clearItem.setItemMeta(clearMeta);
        return clearItem;
    }

    public ItemStack makeSlowItem(ChatColor permissionColor) {
        slowItem = new ItemStack(Material.YELLOW_WOOL);
        ItemMeta slowMeta = slowItem.getItemMeta();
        slowMeta.setDisplayName(permissionColor + "" + BOLD + "Slow Chat");
        if(ModTools.getSlowUtil().getEnabledHashMap()){
            slowMeta.setLore(Arrays.asList("",
                    GRAY + "Chat is currently in slow mode.",
                    GRAY + "(" + ModTools.getSlowUtil().getLengthHashMap() + " seconds)",
                    "",
                    WHITE + "Click to toggle slow mode",
                    WHITE + "for all players on the",
                    WHITE + "server.",
                    ""));
        }else {
            slowMeta.setLore(Arrays.asList("",
                    WHITE + "Click to toggle slow mode",
                    WHITE + "for all players on the",
                    WHITE + "server.",
                    ""));
        }
        slowItem.setItemMeta(slowMeta);
        return slowItem;
    }

    public ItemStack makeLockItem(ChatColor permissionColor) {
        lockItem = new ItemStack(Material.BARRIER);
        ItemMeta lockMeta = lockItem.getItemMeta();
        lockMeta.setDisplayName(permissionColor + "" + BOLD + "Lock Chat");
        if(ModTools.getLockUtil().getLockEnabledHashMap()){
            lockMeta.setLore(Arrays.asList("",
                    GRAY + "Chat is currently " + RED + "locked.",
                    "",
                    WHITE + "Clicking this will unlock chat",
                    WHITE + "for all players on the",
                    WHITE + "server.",
                    ""));
        }else {
            lockMeta.setLore(Arrays.asList("",
                    WHITE + "Clicking this will lock chat",
                    WHITE + "for all players on the",
                    WHITE + "server.",
                    ""));
        }
        lockItem.setItemMeta(lockMeta);
        return lockItem;
    }
}
