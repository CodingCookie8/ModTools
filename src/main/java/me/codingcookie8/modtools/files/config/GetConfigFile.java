package me.codingcookie8.modtools.files.config;

import me.codingcookie8.modtools.files.messages.CreateMsgsFile;

public class GetConfigFile {

    private CreateConfigFile createConfigFile;

    public String getString(String file, String defaultString){
        createConfigFile = new CreateConfigFile();
        if(!(createConfigFile.getConfigFileConfig().contains(file))) {
            createConfigFile.getConfigFileConfig().set(file, defaultString);
            createConfigFile.saveConfigFile();
        }
        return createConfigFile.getConfigFileConfig().getString(file);
    }

    public boolean getBoolean(String file, boolean defaultBool){
        createConfigFile = new CreateConfigFile();
        if(!(createConfigFile.getConfigFileConfig().contains(file))) {
            createConfigFile.getConfigFileConfig().set(file, defaultBool);
            createConfigFile.saveConfigFile();
        }
        return createConfigFile.getConfigFileConfig().getBoolean(file);
    }

    public int getInt(String file, int defaultInt){
        createConfigFile = new CreateConfigFile();
        if(!(createConfigFile.getConfigFileConfig().contains(file))) {
            createConfigFile.getConfigFileConfig().set(file, defaultInt);
            createConfigFile.saveConfigFile();
        }
        return createConfigFile.getConfigFileConfig().getInt(file);
    }

    public void setBoolean(String file, boolean bool){
        createConfigFile = new CreateConfigFile();
        if(!(createConfigFile.getConfigFileConfig().contains(file))) {
            createConfigFile.getConfigFileConfig().set(file, bool);
            createConfigFile.saveConfigFile();
        }
        createConfigFile.getConfigFileConfig().set(file, bool);
        createConfigFile.saveConfigFile();
    }

    public void setInt(String file, int int1){
        createConfigFile = new CreateConfigFile();
        if(!(createConfigFile.getConfigFileConfig().contains(file))) {
            createConfigFile.getConfigFileConfig().set(file, int1);
            createConfigFile.saveConfigFile();
        }
        createConfigFile.getConfigFileConfig().set(file, int1);
        createConfigFile.saveConfigFile();
    }

}
