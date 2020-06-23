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
import me.codingcookie8.modtools.files.messages.CreateMsgsFile;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModTools extends JavaPlugin {

    private ModTools plugin;
    private File folder;
    private File msgsFile;

    public void onEnable() {
        plugin = this;

        folder = new File("plugins" + File.separator + "ModTools");
        if(!folder.exists()) {
            plugin.getLogger().warning("Config files missing, creating them now...");
            msgsFile = new File("plugins" + File.separator + "ModTools" + File.separator + "messages" + File.separator  + ".yml");
            saveResource("messages.yml", false);
        }else{
            plugin.getLogger().info("Config files loaded.");
        }

        CommandExecutor mtExecutor = new CommandModTools(this);
        getCommand("modtools").setExecutor(mtExecutor);

        plugin.getLogger().info("Thank you for using ModTools version " + plugin.getDescription().getVersion());
    }

    public void onDisable(){
        plugin = null;
    }

}