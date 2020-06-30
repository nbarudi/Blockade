package me.nbarudi.salem.roles.Mafia;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class Mafioso extends Role{

	public Mafioso(String name) {
		super(name);
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("You can attack if the Godfather doesn't give you orders.");
		atrib.add("If the Godfather dies you will become the next Godfather.");
		atrib.add("You can talk with the other Mafia at night.");
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("Carry out the Godfather's orders.");
		
		
		
		this.attack = 1;
		this.defence = 0;
		this.abilities = abil;
		this.attributes = atrib;
		this.unique = true;
		this.priority = 5;
		this.team = SalemBlockade.tm.getTeamFromName("Mafia");
	}

}
