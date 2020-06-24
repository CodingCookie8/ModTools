package me.codingcookie8.modtools.files.messages;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GetMsgsFile {

    private CreateMsgsFile createMessagesFile;

    public String getMessage(String file, String defaultString){
        createMessagesFile = new CreateMsgsFile();
        if(!(createMessagesFile.getMessagesFileConfig().contains(file + ".message"))) {
            createMessagesFile.getMessagesFileConfig().set(file + ".message", defaultString);
            createMessagesFile.saveMessagesFile();
        }
        return createMessagesFile.getMessagesFileConfig().getString(file + ".message");
    }

    public boolean getEnabled(String file, boolean defaultBoolean){
        createMessagesFile = new CreateMsgsFile();
        if(!(createMessagesFile.getMessagesFileConfig().contains(file + ".enabled"))) {
            createMessagesFile.getMessagesFileConfig().set(file + ".enabled", defaultBoolean);
            createMessagesFile.saveMessagesFile();
        }
        return createMessagesFile.getMessagesFileConfig().getBoolean(file + ".enabled");
    }

    public void checkAndGetPermissionsMsg(Player p){
        if(getEnabled("no-permissions", true)) {
            String message = ChatColor.translateAlternateColorCodes('&', getMessage("no-permission", "&4You do not have permission to perform that command."));
            p.sendMessage(message);
        }
    }
}
