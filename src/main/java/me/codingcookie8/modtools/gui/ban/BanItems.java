package me.codingcookie8.modtools.gui.ban;

import me.codingcookie8.modtools.ModTools;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.bukkit.ChatColor.*;

public class BanItems {

    private final ModTools plugin;

    private ItemStack greenPane;
    private ItemStack playerHead;
    private ItemStack duration;
    private ItemStack permanent;
    private ItemStack customDuration;
    private ItemStack cancel;


    public BanItems(ModTools plugin){
        this.plugin = plugin;
    }

    public ItemStack makeGreenPane() {
        greenPane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta meta = greenPane.getItemMeta();
        meta.setDisplayName(WHITE + "");
        greenPane.setItemMeta(meta);
        return greenPane;
    }

    public ItemStack makePlayerHead(OfflinePlayer targetedOfflinePlayer) {
        playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sm = (SkullMeta) playerHead.getItemMeta();
        sm.setOwningPlayer(targetedOfflinePlayer);
        ItemMeta meta = playerHead.getItemMeta();
        meta.setDisplayName(GREEN + "" + BOLD + targetedOfflinePlayer.getName());
        meta.setLore(Arrays.asList("",
                WHITE + "Click an option below for",
                WHITE + "more information.",
                ""));
        playerHead.setItemMeta(sm);
        playerHead.setItemMeta(meta);
        return playerHead;
    }

    public ItemStack makeDuration(int option) {
        duration = new ItemStack(Material.YELLOW_WOOL);
        ItemMeta meta = duration.getItemMeta();
        String name = (String) plugin.getConfig().get("ban.option" + option + ".duration", "&f&l24 Hours");
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> lore = new ArrayList<>();
        lore.add("");
        for (String all : plugin.getConfig().getStringList("ban.option" + option + ".reasons")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', all));
        }
        lore.add("");
        meta.setLore(lore);
        duration.setItemMeta(meta);
        return duration;
    }

    public ItemStack makePermanent() {
        permanent = new ItemStack(Material.RED_WOOL);
        ItemMeta meta = permanent.getItemMeta();
        String name = (String) plugin.getConfig().get("ban.permanent.duration", "&c&lPermanent");
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> lore = new ArrayList<>();
        lore.add("");
        for (String all : plugin.getConfig().getStringList("ban.permanent.reasons")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', all));
        }
        lore.add("");
        meta.setLore(lore);
        permanent.setItemMeta(meta);
        return permanent;
    }

    public ItemStack makeCustomDuration() {
        customDuration = new ItemStack(Material.OAK_SIGN);
        ItemMeta meta = customDuration.getItemMeta();
        meta.setDisplayName(WHITE + "Custom duration...");
        customDuration.setItemMeta(meta);
        return customDuration;
    }

    public ItemStack makeCancel() {
        cancel = new ItemStack(Material.BARRIER);
        ItemMeta meta = cancel.getItemMeta();
        meta.setDisplayName(WHITE + "" + BOLD + "Cancel Process");
        meta.setLore(Arrays.asList("",
                GRAY + "This option cannot be",
                GRAY + "undone.",
                ""));
        cancel.setItemMeta(meta);
        return cancel;
    }
}
