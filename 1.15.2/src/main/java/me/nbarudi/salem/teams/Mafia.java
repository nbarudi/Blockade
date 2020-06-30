package me.nbarudi.salem.teams;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Team;
import me.nbarudi.util.managers.game.TeamManager;
import org.bukkit.ChatColor;

public class Mafia extends Team {

    TeamManager tm = SalemBlockade.tm;

    public Mafia(String name) {
        super(name);
        this.nightChat = true;
        this.color = ChatColor.RED;
    }
}
