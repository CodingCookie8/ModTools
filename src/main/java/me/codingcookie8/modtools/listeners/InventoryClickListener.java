package me.codingcookie8.modtools.listeners;

import me.codingcookie8.modtools.ModTools;
import me.codingcookie8.modtools.commands.subcommands.chatsubcommands.ChatSubCommandClear;
import me.codingcookie8.modtools.commands.subcommands.chatsubcommands.ChatSubCommandLock;
import me.codingcookie8.modtools.commands.subcommands.chatsubcommands.ChatSubCommandSlow;
import me.codingcookie8.modtools.gui.chat.ChatGUIUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class InventoryClickListener implements Listener {

    private ModTools plugin;

    public InventoryClickListener(ModTools plugin){
        this.plugin = plugin;
    }

    private ChatSubCommandClear subCommandClear;
    private ChatSubCommandLock subCommandLock;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        InventoryView view = p.getOpenInventory();

        subCommandClear = new ChatSubCommandClear();
        subCommandLock = new ChatSubCommandLock();

        if (view == null) {
            return;
        }

        if (clicked == null || clicked.getType() == Material.AIR) {
            e.setCancelled(true);
            return;
        }

        if(e.getView().getTitle().contains("Chat Settings")){
            if(clicked.getType() == Material.PAPER){
                e.setCancelled(true);
            }
            if(clicked.getType() == Material.BUCKET){
                e.setCancelled(true);
                new BukkitRunnable(){
                    public void run(){
                        subCommandClear.clearChat(p);
                        p.closeInventory();
                    }
                }.runTaskLater(plugin, 1L);
            }
            if(clicked.getType() == Material.YELLOW_WOOL){
                e.setCancelled(true);
                new BukkitRunnable(){
                    public void run(){
                        ModTools.getChatUtil().sendSlowAlert(p);
                        p.closeInventory();
                    }
                }.runTaskLater(plugin, 1L);
            }
            if(clicked.getType() == Material.BARRIER){
                e.setCancelled(true);
                new BukkitRunnable(){
                    public void run(){
                        subCommandLock.lockChat(p);
                        p.closeInventory();
                    }
                }.runTaskLater(plugin, 1L);
            }
        }
    }
}
