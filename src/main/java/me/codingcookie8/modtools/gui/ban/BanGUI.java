package me.codingcookie8.modtools.gui.ban;

import me.codingcookie8.modtools.ModTools;
import me.codingcookie8.modtools.gui.chat.ChatItems;
import me.codingcookie8.modtools.permissions.PermissionHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BanGUI {

    private ModTools plugin;

    public BanGUI(ModTools plugin) {
        this.plugin = plugin;
        invBan = Bukkit.createInventory(null, 54, "Ban Settings");
    }

    private Inventory invBan;
    private BanItems banItems;
    private PermissionHandler pH;

    public void openBanGUI(final HumanEntity ent) {
        ent.openInventory(getBanGUI());
    }

    public Inventory getBanGUI() {
        return invBan;
    }

    public void setBanGUI(Player p, OfflinePlayer offlinePlayer) {
        banItems = new BanItems(plugin);
        pH = new PermissionHandler();

        for (int slot = 0; slot < 54; slot++) {
            if ((slot >= 0 && slot <= 8) || (slot >= 45 && slot <= 53)) {
                getBanGUI().setItem(slot, banItems.makeGreenPane());
                if(slot == 4){
                    getBanGUI().setItem(slot, banItems.makePlayerHead(offlinePlayer));
                }
            }
            if((slot == 9) ||
                    (slot == 18) ||
                    (slot == 27) ||
                    (slot == 36) ||
                    (slot == 17) ||
                    (slot == 26) ||
                    (slot == 35) ||
                    (slot == 44)){
                getBanGUI().setItem(slot, banItems.makeGreenPane());
            }

            if(slot == 20){
                if(plugin.getConfig().getString("ban.option1.duration") != null){
                    getBanGUI().setItem(slot, banItems.makeDuration(1));
                }
            }
            if(slot == 21){
                if(plugin.getConfig().getString("ban.option2.duration") != null){
                    getBanGUI().setItem(slot, banItems.makeDuration(2));
                }
            }
            if(slot == 22){
                if(plugin.getConfig().getString("ban.option3.duration") != null){
                    getBanGUI().setItem(slot, banItems.makeDuration(3));
                }
            }
            if(slot == 23){
                if(plugin.getConfig().getString("ban.option4.duration") != null){
                    getBanGUI().setItem(slot, banItems.makeDuration(4));
                }else{
                    getBanGUI().setItem(slot, banItems.makePermanent());
                }
            }
            if(slot == 24){
                if(plugin.getConfig().getString("ban.option4.duration") != null){
                    getBanGUI().setItem(slot, banItems.makePermanent());
                }
            }
            if(slot == 31){
                getBanGUI().setItem(slot, banItems.makeCustomDuration());
            }
            if(slot == 49){
                getBanGUI().setItem(slot, banItems.makeCancel());
            }
        }
        openBanGUI(p);
    }
}
