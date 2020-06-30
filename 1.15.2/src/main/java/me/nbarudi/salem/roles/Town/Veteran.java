package me.nbarudi.salem.roles.Town;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class Veteran extends Role{

	public Veteran(String name) {
		super(name);
		
		this.attack = 2;
		this.defence = 0;
		
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("Decide if you will go on alert.");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("While on alert you gain Basic Defense.");
		atrib.add("While on alert, you will deliver a Powerful attack to anyone who visits you.");
		atrib.add("You can only go on alert 3 times.");
		atrib.add("You cannot be role blocked.");

		
		this.attributes = atrib;
		this.abilities = abil;
		this.unique = true;
		this.rbimmune = true;
		// While on alert
		this.priority = 1;
		this.team = SalemBlockade.tm.getTeamFromName("Town");
		
	}

}
