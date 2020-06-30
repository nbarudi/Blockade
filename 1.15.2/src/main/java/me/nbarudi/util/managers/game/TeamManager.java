package me.nbarudi.util.managers.game;

import me.nbarudi.salem.Team;
import me.nbarudi.salem.teams.Mafia;
import me.nbarudi.salem.teams.NeutralEvil;
import me.nbarudi.salem.teams.SerialKiller;
import me.nbarudi.salem.teams.Town;
import me.nbarudi.util.managers.MessageHandler;

import java.util.ArrayList;

public class TeamManager {

    private ArrayList<Team> teams = new ArrayList<Team>();

    public TeamManager(){
        MessageHandler.globalAnnounce("Registering Teams");

        //Registering Teams
        teams.add(new Town("Town"));
        teams.add(new Mafia("Mafia"));
        teams.add(new SerialKiller("SerialKiller"));
        teams.add(new NeutralEvil("NeutralEvil"));

        setupWinConditions();

        MessageHandler.globalAnnounce("Teams Registered!");
    }

    public Team getTeamFromName(String name){
        Team t = null;
        for(Team team : teams){
            if(team.name == name){
                t = team;
            }
        }
        return t;
    }

    private void setupWinConditions(){

        //Town Win Conditions
        ArrayList<Team> twin = new ArrayList<Team>();
        twin.add(getTeamFromName("Town"));
        twin.add(getTeamFromName("NeutralEvil"));

        getTeamFromName("Town").winconditions = twin;

        //Mafia Win Conditions
        ArrayList<Team> mwin = new ArrayList<Team>();
        mwin.add(getTeamFromName("Mafia"));
        mwin.add(getTeamFromName("NeutralEvil"));

        getTeamFromName("Mafia").winconditions = mwin;

        //Neutral Evil Win Conditions
        ArrayList<Team> newin = new ArrayList<Team>();
        newin.add(getTeamFromName("NeutralEvil"));
        newin.add(getTeamFromName("SerialKiller"));
        newin.add(getTeamFromName("Town"));
        newin.add(getTeamFromName("Mafia"));

        getTeamFromName("NeutralEvil").winconditions = newin;

        //Serial Killer Win Conditions
        ArrayList<Team> skwin = new ArrayList<Team>();

        skwin.add(getTeamFromName("SerialKiller"));
        skwin.add(getTeamFromName("NeutralEvil"));

        getTeamFromName("SerialKiller").winconditions = skwin;
    }

}
