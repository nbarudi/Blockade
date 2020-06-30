package me.nbarudi.salem.roles.Mafia;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class Framer extends Role {

	public Framer(String name) {
		super(name);
		this.attack = 0;
		this.defence = 0;
		
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("Choose one person to frame at night.");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("If your target is investigated they will appear to be a member of the Mafia.");
		atrib.add("If there are no kill capable Mafia roles left you will become a Mafioso.");
		atrib.add("You can talk with the other Mafia at night.");
		
		
		this.attributes = atrib;
		this.abilities = abil;
		this.priority = 3;
		this.team = SalemBlockade.tm.getTeamFromName("Mafia");
	}

}
