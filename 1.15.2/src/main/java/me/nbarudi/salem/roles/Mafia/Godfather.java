package me.nbarudi.salem.roles.Mafia;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class Godfather extends Role {

	public Godfather(String name) {
		super(name);
		this.attack = 1;
		this.defence = 1;
		this.unique = true;
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("You may choose to attack a player each night.");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("If there is a Mafioso he will attack the target instead of you.");
		atrib.add("You will appear to be a Town member to the Sheriff.");
		atrib.add("You can talk with the other Mafia at night.");
		
		this.attributes = atrib;
		this.abilities = abil;
		this.priority = 5;
		this.team = SalemBlockade.tm.getTeamFromName("Mafia");
	}

}
