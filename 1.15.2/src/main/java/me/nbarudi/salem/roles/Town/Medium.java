package me.nbarudi.salem.roles.Town;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class Medium extends Role{

	public Medium(String name) {
		super(name);
		this.attack = 0;
		this.defence = 0;
		
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("When dead speak to a living person at night.");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("You will speak to the dead anonymously each night you are alive.");
		atrib.add("You may only speak to a living person once.");
		
		
		this.attributes = atrib;
		this.abilities = abil;
		this.priority = 1;
		this.team = SalemBlockade.tm.getTeamFromName("Town");
	}

}
