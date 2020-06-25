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
import me.codingcookie8.modtools.commands.subcommands.chatsubcommands.SlowUtil;
import me.codingcookie8.modtools.gui.chat.ChatGUI;
import me.codingcookie8.modtools.gui.chat.ChatGUIUtil;
import me.codingcookie8.modtools.listeners.ChatListener;
import me.codingcookie8.modtools.listeners.InventoryClickListener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ModTools extends JavaPlugin {

    private ModTools plugin;
    private File folder;
    private File msgsFile;
    private File configFile;
    private SlowUtil sV;

    private static ChatGUIUtil CHAT_UTIL;

    public ChatListener chatListener;
    public InventoryClickListener inventoryClickListener;

    public void onEnable() {
        plugin = this;

        CHAT_UTIL = new ChatGUIUtil();

        chatListener = new ChatListener(this);
        inventoryClickListener = new InventoryClickListener(this);
        sV = new SlowUtil();

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

        if(sV.getEnabledHashMap()){
            plugin.getLogger().warning("Slow mode is enabled! (" + sV.getLengthHashMap() + " seconds)");
        }

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(chatListener, this);
        pm.registerEvents(inventoryClickListener, this);

        CommandExecutor mtExecutor = new CommandModTools(this);
        getCommand("modtools").setExecutor(mtExecutor);

        plugin.getLogger().info("Thank you for using ModTools version " + plugin.getDescription().getVersion());
    }

    public void onDisable(){
        plugin = null;
    }

    public static ChatGUIUtil getChatUtil(){
        return CHAT_UTIL;
    }

}
