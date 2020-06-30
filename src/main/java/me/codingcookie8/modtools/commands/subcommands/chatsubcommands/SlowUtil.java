package me.codingcookie8.modtools.commands.subcommands.chatsubcommands;

import me.codingcookie8.modtools.ModTools;

import java.util.HashMap;

public class SlowUtil {

    private ModTools plugin;

    private HashMap<String, Integer> slowModeLength;
    private HashMap<String, Boolean> slowModeEnabled;

    public SlowUtil(ModTools plugin){
        this.plugin = plugin;
        slowModeLength = new HashMap<String, Integer>();
        slowModeEnabled = new HashMap<String, Boolean>();
    }

    public int getSlowModeLengthConfig(){
        return plugin.getConfig().getInt("chat.slow.length", 0);
    }

    public void setSlowModeLengthConfig(int length1){
        plugin.getConfig().set("chat.slow.length", length1);
    }

    public boolean isEnabledConfig(){
        return plugin.getConfig().getBoolean("chat.slow.enabled", false);
    }

    public void setEnabledConfig(boolean enabled1){
        plugin.getConfig().set("chat.slow.enabled", enabled1);
    }

    // HashMaps so we aren't referencing the config every time the player chats

    public HashMap<String, Integer> getIntHashMap(){
        return slowModeLength;
    }

    public HashMap<String, Boolean> getBooleanHashMap(){
        return slowModeEnabled;
    }

    public int getLengthHashMap(){
        return getIntHashMap().get("slow");
    }

    public boolean getEnabledHashMap(){
        return getBooleanHashMap().get("slow");
    }

    public void setSlowModeLengthHashMap(int seconds){
        getIntHashMap().put("slow", seconds);
    }

    public void setSlowModeEnabledHashMap(boolean enabled){
        getBooleanHashMap().put("slow", enabled);
    }
}
