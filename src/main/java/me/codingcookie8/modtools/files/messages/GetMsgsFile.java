package me.codingcookie8.modtools.files.messages;

public class GetMsgsFile {

    private CreateMsgsFile messagesFile;

    public String getMessage(String file, String defaultString){
        messagesFile = new CreateMsgsFile();
        if(!(messagesFile.getMessagesFileConfig().contains(file))) {
            messagesFile.getMessagesFileConfig().set(file, defaultString);
            messagesFile.saveMessagesFile();
        }
        return messagesFile.getMessagesFileConfig().getString(file);
    }
}
