package me.nbarudi.salem.roles.Town;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class Jailor extends Role {

	public Jailor(String name) {
		super(name);
		this.attack = 3;
		this.defence = 0;
		ArrayList<String> abil = new ArrayList<String>();
		ArrayList<String> atrib = new ArrayList<String>();
		
		atrib.add("You may choose one person during the day to jail for the night.");
		
		abil.add("You may anonymously talk with your prisoner.");
		abil.add("You can choose to attack your prisoner.");
		abil.add("The jailed target can't perform their night ability.");
		abil.add("While jailed the prisoner is given Powerful defense.");
		
		
		this.attributes = atrib;
		this.abilities = abil;


		this.unique = true;
		this.priority = 5;
		this.team = SalemBlockade.tm.getTeamFromName("Town");
		
	}

}
