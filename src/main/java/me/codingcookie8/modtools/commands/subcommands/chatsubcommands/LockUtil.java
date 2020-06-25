package me.codingcookie8.modtools.commands.subcommands.chatsubcommands;

import me.codingcookie8.modtools.files.config.GetConfigFile;

import java.util.HashMap;

public class LockUtil {

    private GetConfigFile configFile;
    private HashMap<String, Boolean> lockEnabled = new HashMap<String, Boolean>();

    public boolean isLockEnabledConfig(){
        configFile = new GetConfigFile();
        return configFile.getBoolean("chat.lock.enabled", false);
    }

    public void setLockEnabledConfig(boolean enabled1){
        configFile = new GetConfigFile();
        configFile.setBoolean("chat.lock.enabled", enabled1);
    }

    // HashMaps so we aren't referencing the config everytime the player chats

    public HashMap<String, Boolean> getBooleanHashMap(){
        return lockEnabled;
    }

    public boolean getLockEnabledHashMap(){
        if(getBooleanHashMap().get("lock") == null){
            getBooleanHashMap().put("lock", isLockEnabledConfig());
        }
        return getBooleanHashMap().get("lock");
    }

    public void setLockEnabledHashMap(boolean enabled){
        getBooleanHashMap().put("lock", enabled);
    }
}
