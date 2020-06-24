package me.codingcookie8.modtools.commands.subcommands.chatsubcommands;

import me.codingcookie8.modtools.files.config.GetConfigFile;

import java.util.HashMap;

public class SlowVariables {

    private GetConfigFile configFile;
    private HashMap<String, Integer> slowModeLength = new HashMap<String, Integer>();
    private HashMap<String, Boolean> slowModeEnabled = new HashMap<String, Boolean>();

    public int getSlowModeLengthConfig(){
        configFile = new GetConfigFile();
        return configFile.getInt("chat.slow.length", 0);
    }

    public void setSlowModeLengthConfig(int length1){
        configFile = new GetConfigFile();
        configFile.setInt("chat.slow.length", length1);
    }

    public boolean isEnabledConfig(){
        configFile = new GetConfigFile();
        return configFile.getBoolean("chat.slow.enabled", false);
    }

    public void setEnabledConfig(boolean enabled1){
        configFile = new GetConfigFile();
        configFile.setBoolean("chat.slow.enabled", enabled1);
    }

    // HashMaps so we aren't referencing the config everytime the player chats

    public HashMap<String, Integer> getIntHashMap(){
        return slowModeLength;
    }

    public HashMap<String, Boolean> getBooleanHashMap(){
        return slowModeEnabled;
    }

    public int getLengthHashMap(){
        if(getIntHashMap().get("chat") == null){
            getIntHashMap().put("chat", getSlowModeLengthConfig());
        }
        return getIntHashMap().get("chat");
    }

    public boolean getEnabledHashMap(){
        if(getBooleanHashMap().get("chat") == null){
            getBooleanHashMap().put("chat", isEnabledConfig());
        }
        return getBooleanHashMap().get("chat");
    }

    public void setSlowModeLengthHashMap(int seconds){
        getIntHashMap().put("chat", seconds);
    }

    public void setSlowModeEnabledHashMap(boolean enabled){
        getBooleanHashMap().put("chat", enabled);
    }
}
