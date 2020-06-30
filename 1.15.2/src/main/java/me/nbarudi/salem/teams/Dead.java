package me.nbarudi.salem.teams;

import me.nbarudi.salem.Team;
import org.bukkit.ChatColor;

public class Dead extends Team {


    public Dead(String name) {
        super(name);
        this.color = ChatColor.DARK_RED;
    }

    @Override
    public boolean hasWon() {
        return false;
    }
}
