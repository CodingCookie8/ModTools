package me.codingcookie8.modtools.listeners;

import me.codingcookie8.modtools.ModTools;
import me.codingcookie8.modtools.commands.subcommands.chatsubcommands.ChatSubCommandSlow;
import me.codingcookie8.modtools.commands.subcommands.chatsubcommands.SlowVariables;
import me.codingcookie8.modtools.files.messages.GetMsgsFile;
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
import java.util.HashMap;
import java.util.UUID;

public class ChatListener implements Listener {

    private PermissionHandler pH;
    private ChatSubCommandSlow subCommandSlow;
    private GetMsgsFile messagesFile;
    private int seconds;
    private boolean enabled;

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

        SlowVariables sV = new SlowVariables();
        seconds = sV.getLengthHashMap();
        enabled = sV.getEnabledHashMap();

        if(pH.checkPermission(p, "modtools.chat.slow.exempt")){
            return;
        }
        if(!(enabled)){
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
