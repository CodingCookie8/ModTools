package me.codingcookie8.modtools.listeners;

import me.codingcookie8.modtools.ModTools;
import me.codingcookie8.modtools.gui.chat.ChatGUIUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    private ModTools plugin;

    public LeaveListener(ModTools plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = (Player) e.getPlayer();

        if(ModTools.getChatUtil().getWaitingForSlowInput().contains(p.getUniqueId())){
            ModTools.getChatUtil().getWaitingForSlowInput().remove(p.getUniqueId());
        }

    }
}
