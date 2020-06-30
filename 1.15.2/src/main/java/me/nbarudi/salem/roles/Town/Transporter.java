package me.nbarudi.salem.roles.Town;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class Transporter extends Role {

	public Transporter(String name) {
		super(name);
		this.attack = 0;
		this.defence = 0;
		
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("Choose two people to transport at night.");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("Transporting two people swaps all targets against them.");
		atrib.add("You may transport yourself.");
		atrib.add("Your targets will know they were transported.");
		atrib.add("You may not transport someone with themselves.");
		
		
		this.attributes = atrib;
		this.abilities = abil;
		this.rbimmune = true;
		this.priority = 1;
		this.team = SalemBlockade.tm.getTeamFromName("Town");
		this.numVisits = 2;
	}

}
