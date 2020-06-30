package me.nbarudi.salem.roles.Town;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class Retributionist extends Role {

	public Retributionist(String name) {
		super(name);
		this.attack = 0;
		this.defence = 0;
		this.unique = true;
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("You may revive a dead Town member.");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("You may only resurrect one Town member.");
		
		
		this.attributes = atrib;
		this.abilities = abil;
		this.priority = 3;
		this.team = SalemBlockade.tm.getTeamFromName("Town");
	}

}
