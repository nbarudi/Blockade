package me.nbarudi.salem.roles.Town;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class Escort extends Role{

	public Escort(String name) {
		super(name);
		this.attack = 0;
		this.defence = 0;
		
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("Distract someone each night.");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("Distraction blocks your target from using their role's night abilities.");
		atrib.add("You cannot be role blocked.");
		

		
		this.attributes = atrib;
		this.abilities = abil;
		this.rbimmune = true;
		this.priority = 2;
		this.team = SalemBlockade.tm.getTeamFromName("Town");
	}

}
