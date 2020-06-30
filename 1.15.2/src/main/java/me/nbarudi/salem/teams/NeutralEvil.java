package me.nbarudi.salem.teams;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Team;
import me.nbarudi.util.managers.game.TeamManager;
import org.bukkit.ChatColor;

public class NeutralEvil extends Team {

    TeamManager tm = SalemBlockade.tm;

    public NeutralEvil(String name) {
        super(name);
        this.nightChat = false;
        this.color = ChatColor.GRAY;
    }
}
