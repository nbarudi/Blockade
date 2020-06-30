package me.nbarudi.util;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.PlayerData;
import me.nbarudi.util.managers.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;

public class Wills {

    public static void setUpWills() {

        for(Player p : Bukkit.getOnlinePlayers()) {
            ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
            BookMeta bm = (BookMeta) book.getItemMeta();
            bm.setDisplayName(p.getName() + "'s Will");
            StringBuilder sb = new StringBuilder();
            sb.append(p.getName() + " -\n\n");
            sb.append("N1 - \n");
            sb.append("N2 - \n");
            sb.append("N3 - \n");
            sb.append("N4 - \n");
            sb.append("N5 - \n");
            bm.addPage(sb.toString());
            book.setItemMeta(bm);
            p.getInventory().setItem(8, book);
        }
    }

    //Give out the players will to everyone!
    public static void spreadWill(Player toSpread) {

        ArrayList<PlayerData> alive = new ArrayList<PlayerData>();

        for(PlayerData pd : SalemBlockade.playerdata){
            if(pd.isDead) continue;
            alive.add(pd);
        }

        ItemStack book = toSpread.getInventory().getItem(8);
        if(book.equals(null)) return;
        BookMeta bm = (BookMeta)book.getItemMeta();
        ItemStack spreadBook = new ItemStack(Material.WRITTEN_BOOK);
        spreadBook.setItemMeta(bm);
        for(PlayerData pd : alive) {
            Player p = Bukkit.getPlayer(pd.getName());
            p.getInventory().addItem(spreadBook);
            MessageHandler.privateMessage(p, "You have been given " + toSpread.getName() + "'s will, click it in your inventory to view it!");
        }
    }


}
