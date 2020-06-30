package me.nbarudi.util.managers.game;

import me.nbarudi.salem.Role;
import me.nbarudi.salem.roles.Mafia.Framer;
import me.nbarudi.salem.roles.Mafia.Godfather;
import me.nbarudi.salem.roles.Mafia.Mafioso;
import me.nbarudi.salem.roles.Neutral.Executioner;
import me.nbarudi.salem.roles.Neutral.Jester;
import me.nbarudi.salem.roles.Neutral.SerialKiller;
import me.nbarudi.salem.roles.Town.*;
import me.nbarudi.salem.roles.Town.Random.RandomTown;
import me.nbarudi.salem.roles.Town.Random.TownKilling;
import me.nbarudi.util.managers.MessageHandler;

import java.util.ArrayList;

public class RoleManager {

    private ArrayList<Role> roles = new ArrayList<Role>();

    public RoleManager(){
        MessageHandler.globalAnnounce("Registering Roles..");
        //Registering Roles:
        //Town
        roles.add(new Sheriff("Sheriff"));
        roles.add(new Lookout("Lookout"));
        roles.add(new Investigator("Investigator"));
        roles.add(new Jailor("Jailor"));
        roles.add(new Doctor("Doctor"));
        roles.add(new Escort("Escort"));
        roles.add(new Medium("Medium"));
        roles.add(new TownKilling("TownKilling"));
        roles.add(new RandomTown("RandomTown"));

        //Mafia
        roles.add(new Godfather("GodFather"));
        roles.add(new Mafioso("Mafioso"));
        roles.add(new Framer("Framer"));

        //Neutral Killing
        roles.add(new SerialKiller("SerialKiller"));

        //Neutral Evil
        roles.add(new Executioner("Executioner"));
        roles.add(new Jester("Jester"));

        MessageHandler.globalAnnounce("Roles Registered!");
    }

    public Role getRoleFromName(String name){
        Role r = null;
        for(Role role : roles){
            if(role.name == name)
                r = role;
        }
        return r;
    }

}
