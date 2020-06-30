package me.nbarudi.salem.teams;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Team;
import me.nbarudi.util.managers.game.TeamManager;
import org.bukkit.ChatColor;

public class SerialKiller extends Team {
    TeamManager tm = SalemBlockade.tm;
    public SerialKiller(String name) {
        super(name);
        this.nightChat = false;
        this.color = ChatColor.DARK_BLUE;

    }
}
