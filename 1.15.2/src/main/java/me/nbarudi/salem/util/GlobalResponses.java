package me.nbarudi.salem.util;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;
import me.nbarudi.util.managers.game.RoleManager;

import java.util.ArrayList;
import java.util.HashMap;

public class GlobalResponses {

    public ArrayList<Role> susList = new ArrayList<Role>();
    public HashMap<Role, String> investList = new HashMap<Role, String>();
    public ArrayList<String> consigResponse = new ArrayList<String>();

    private RoleManager rm = SalemBlockade.rm;

    public GlobalResponses(){
        setupSusList();
        setupInvestList();
    }

    private void setupInvestList() {
        investList.put(rm.getRoleFromName("Framer"), "Framer, Vampire, Jester");
        investList.put(rm.getRoleFromName("Godfather"), "Bodyguard, Godfather, Arsonist");
        investList.put(rm.getRoleFromName("Mafioso"), "Vigilante, Veteran, Mafioso");
        investList.put(rm.getRoleFromName("Executioner"), "Sheriff Executioner, Werewolf");
        investList.put(rm.getRoleFromName("Jester"), "Framer, Vampire, Jester");
        investList.put(rm.getRoleFromName("SerialKiller"), "Doctor, Disguiser, Serialkiller");
        investList.put(rm.getRoleFromName("Bodyguard"), "Bodyguard, Godfather, Arsonist");
        investList.put(rm.getRoleFromName("Doctor"), "Doctor, Disguiser, Serialkiller");
        investList.put(rm.getRoleFromName("Escort"), "Escort, Transporter, Consort");
        investList.put(rm.getRoleFromName("Investigator"), "Investigator, Consigliere, Mayor");
        investList.put(rm.getRoleFromName("Jailor"), "Spy, Blackmailoer, Jailor");
        investList.put(rm.getRoleFromName("Lookout"), "Lookout, Forger, Witch");
        investList.put(rm.getRoleFromName("Mayor"), "Investigator, Consigliere, Mayor");
        investList.put(rm.getRoleFromName("Medium"), "Medium, Janitor, Retributionist");
        investList.put(rm.getRoleFromName("Sheriff"), "Sheriff, Executioner, Werewolf");
        investList.put(rm.getRoleFromName("Spy"), "Spy, Blackmailer, Jailor");
        investList.put(rm.getRoleFromName("Transporter"), "Escort, Transporter, Consort");
        investList.put(rm.getRoleFromName("Veteran"), "Vigilante, Veteran, Mafioso");
        investList.put(rm.getRoleFromName("Vigilante"), "Vigilante, Veteran, Mafioso");
    }

    private void setupSusList(){
        susList.add(rm.getRoleFromName("Mafioso"));
        susList.add(rm.getRoleFromName("SerialKiller"));
        susList.add(rm.getRoleFromName("Framer"));
        //To-Do: As more roles get added, update this list (forger, BMer, WW, etc.)
    }

}
