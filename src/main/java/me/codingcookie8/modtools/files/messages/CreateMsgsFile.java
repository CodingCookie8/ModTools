package me.codingcookie8.modtools.files.messages;

import me.codingcookie8.modtools.ModTools;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class CreateMsgsFile extends YamlConfiguration{

    private File messagesFile = new File("plugins" + File.separator + "ModTools" + File.separator + "messages.yml");
    private FileConfiguration messagesFileConfig = YamlConfiguration.loadConfiguration(messagesFile);

    public FileConfiguration getMessagesFileConfig(){
        return messagesFileConfig;
    }

    public File getMessagesFile(){
        return messagesFile;
    }

    public void saveMessagesFile(){
        try{
            getMessagesFileConfig().save(messagesFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
