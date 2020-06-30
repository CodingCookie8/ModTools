/*
 * ModTools, a comprehensive Moderation Toolbox
 *
 * Created by CodingCookie8
 *
 * This software is free to use. You may redistribute and/or modify it
 * at your discretion.
 *
 * Please reference the Spigot Resource page for ModTools to learn
 * how to modify it to fit your server needs.
 */

package me.codingcookie8.modtools;

import me.codingcookie8.modtools.commands.CommandModTools;
import me.codingcookie8.modtools.commands.subcommands.chatsubcommands.LockUtil;
import me.codingcookie8.modtools.commands.subcommands.chatsubcommands.SlowUtil;
import me.codingcookie8.modtools.commands.tabcompleter.ModToolsTab;
import me.codingcookie8.modtools.gui.chat.ChatGUIUtil;
import me.codingcookie8.modtools.listeners.ChatListener;
import me.codingcookie8.modtools.listeners.InventoryClickListener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ModTools extends JavaPlugin {

    private ModTools plugin;
    private File folder;
    private File msgsFile;
    private File configFile;

    private static ChatGUIUtil CHAT_UTIL;
    private static LockUtil LOCK_UTIL;
    private static SlowUtil SLOW_UTIL;

    public ChatListener chatListener;
    public InventoryClickListener inventoryClickListener;

    public void onEnable() {
        plugin = this;

        CHAT_UTIL = new ChatGUIUtil();
        LOCK_UTIL = new LockUtil(this);
        SLOW_UTIL = new SlowUtil(this);

        chatListener = new ChatListener(this);
        inventoryClickListener = new InventoryClickListener(this);

        folder = new File("plugins" + File.separator + "ModTools");
        if(!folder.exists()) {
            plugin.getLogger().warning("Config files missing, creating them now...");
            msgsFile = new File("plugins" + File.separator + "ModTools" + File.separator + "messages" + File.separator  + ".yml");
            saveResource("messages.yml", false);
            configFile = new File("plugins" + File.separator + "ModTools" + File.separator + "config" + File.separator  + ".yml");
            saveResource("config.yml", false);
        }else{
            plugin.getLogger().info("Config files loaded.");
        }

        if(getSlowUtil().isEnabledConfig()){
            plugin.getLogger().warning("Slow mode is enabled! (" + getSlowUtil().getSlowModeLengthConfig() + " seconds)");
        }
        if(getLockUtil().isLockEnabledConfig()) {
            plugin.getLogger().warning("Chat is locked!");
        }

        getSlowUtil().getIntHashMap().put("slow", getSlowUtil().getSlowModeLengthConfig());
        getSlowUtil().getBooleanHashMap().put("slow", getSlowUtil().isEnabledConfig());
        getLockUtil().getBooleanHashMap().put("lock", getLockUtil().isLockEnabledConfig());

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(chatListener, this);
        pm.registerEvents(inventoryClickListener, this);

        CommandExecutor mtExecutor = new CommandModTools(this);
        getCommand("modtools").setExecutor(mtExecutor);

        TabCompleter modToolsTab = new ModToolsTab();
        getCommand("modtools").setTabCompleter(modToolsTab);

        plugin.getLogger().info("Thank you for using ModTools version " + plugin.getDescription().getVersion());
    }

    public void onDisable(){
        plugin = null;
    }

    public static ChatGUIUtil getChatUtil(){
        return CHAT_UTIL;
    }

    public static LockUtil getLockUtil(){
        return LOCK_UTIL;
    }

    public static SlowUtil getSlowUtil(){
        return SLOW_UTIL;
    }

}
