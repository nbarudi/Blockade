package me.nbarudi.salem.roles.Town;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class Spy extends Role {

	public Spy(String name) {
		super(name);
		this.attack = 0;
		this.defence = 0;
		
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("You may bug a player's house to see what happens to them at night");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("You will know who the Mafi and Coven visit each night");
		
		
		this.attributes = atrib;
		this.abilities = abil;
		this.priority = 6;
		this.team = SalemBlockade.tm.getTeamFromName("Town");
	}

}
