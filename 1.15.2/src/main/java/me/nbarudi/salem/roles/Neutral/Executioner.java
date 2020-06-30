package me.nbarudi.salem.roles.Neutral;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class Executioner extends Role {

	public Executioner(String name) {
		super(name);
		this.attack = 0;
		this.defence = 0;
		
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("None.");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("If your target dies at night you will become a Jester.");
		
		
		this.attributes = atrib;
		this.abilities = abil;
		this.priority = 0;
		this.team = SalemBlockade.tm.getTeamFromName("NeutralEvil");
	}

}
