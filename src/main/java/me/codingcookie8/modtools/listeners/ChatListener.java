package me.codingcookie8.modtools.listeners;

import me.codingcookie8.modtools.ModTools;
import me.codingcookie8.modtools.commands.subcommands.chatsubcommands.ChatSubCommandSlow;
import me.codingcookie8.modtools.commands.subcommands.chatsubcommands.LockUtil;
import me.codingcookie8.modtools.commands.subcommands.chatsubcommands.SlowUtil;
import me.codingcookie8.modtools.files.messages.GetMsgsFile;
import me.codingcookie8.modtools.gui.chat.ChatGUI;
import me.codingcookie8.modtools.gui.chat.ChatGUIUtil;
import me.codingcookie8.modtools.permissions.PermissionHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

import static org.bukkit.ChatColor.RED;

public class ChatListener implements Listener{

    private PermissionHandler pH;
    private ChatSubCommandSlow subCommandSlow;
    private GetMsgsFile messagesFile;
    private int seconds;
    private boolean slowModeEnabled;
    private boolean lockEnabled;

    private final ModTools plugin;

    public ChatListener(ModTools plugin){
        this.plugin = plugin;
    }

    private ArrayList<UUID> slowModePlayers = new ArrayList<UUID>();

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        pH = new PermissionHandler();
        subCommandSlow = new ChatSubCommandSlow();
        messagesFile = new GetMsgsFile();

        SlowUtil slowUtil = new SlowUtil();
        seconds = slowUtil.getLengthHashMap();
        slowModeEnabled = slowUtil.getEnabledHashMap();

        LockUtil lockUtil = new LockUtil();
        lockEnabled = lockUtil.getLockEnabledHashMap();

        if(ModTools.getChatUtil().getWaitingForSlowInput().contains(p.getUniqueId())){
            int seconds1 = 0;
            try {
                seconds1 = Integer.parseInt(e.getMessage());
            } catch (NumberFormatException ex) {
                p.sendMessage(RED + "Couldn't recognize number submitted. Process cancelled.");
                ModTools.getChatUtil().getWaitingForSlowInput().remove(p.getUniqueId());
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);
            ModTools.getChatUtil().getWaitingForSlowInput().remove(p.getUniqueId());
            subCommandSlow.slowModeOn(p, seconds1);
            return;
        }
        if(pH.checkPermission(p, "modtools.chat.slow.exempt") || pH.checkPermission(p, "modtools.chat.lock.exempt")) {
            return;
        }
        if(lockEnabled){
            e.setCancelled(true);

            if(messagesFile.getEnabled("lock-mode-denied", true)){
                String message = ChatColor.translateAlternateColorCodes('&', messagesFile.getMessage("lock-mode-denied", "&cYou can't send messages right now."));
                p.sendMessage(message);
            }
        }
        if(!(slowModeEnabled)){
            return;
        }
        if(!(slowModePlayers.contains(p.getUniqueId()))){
            slowModePlayers.add(p.getUniqueId());
            new BukkitRunnable(){
                public void run(){
                    slowModePlayers.remove(p.getUniqueId());
                }
            }.runTaskLater(plugin, seconds * 20L);
            return;
        }
        e.setCancelled(true);

        if(messagesFile.getEnabled("slow-mode-denied", true)){
            String message = ChatColor.translateAlternateColorCodes('&', messagesFile.getMessage("slow-mode-denied", "&cYou're sending messages too fast!"));
            p.sendMessage(message);
        }
    }
}
