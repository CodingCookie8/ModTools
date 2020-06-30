package me.codingcookie8.modtools.commands.subcommands.chatsubcommands;

import me.codingcookie8.modtools.ModTools;

import java.util.HashMap;

public class LockUtil {

    private ModTools plugin;
    private HashMap<String, Boolean> lockEnabled;

    public LockUtil(ModTools plugin){
        this.plugin = plugin;
        lockEnabled = new HashMap<String, Boolean>();
    }

    public boolean isLockEnabledConfig(){
        return plugin.getConfig().getBoolean("chat.lock.enabled", false);
    }

    public void setLockEnabledConfig(boolean enabled1){
        plugin.getConfig().set("chat.lock.enabled", enabled1);
    }

    // HashMaps so we aren't referencing the config everytime the player chats

    public HashMap<String, Boolean> getBooleanHashMap(){
        return lockEnabled;
    }

    public boolean getLockEnabledHashMap(){
        return getBooleanHashMap().get("lock");
    }

    public void setLockEnabledHashMap(boolean enabled){
        getBooleanHashMap().put("lock", enabled);
    }
}
