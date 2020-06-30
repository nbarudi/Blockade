package me.nbarudi.salem.roles.Neutral;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class SerialKiller extends Role {

	public SerialKiller(String name) {
		super(name);
		this.attack = 1;
		this.defence = 1;
		
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("You may choose to attack a player each night.");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("If you are roleblocked you will attack the role blocker instead of your target.");

		
		this.attributes = atrib;
		this.abilities = abil;
		this.priority = 5;
		this.team = SalemBlockade.tm.getTeamFromName("SerialKiller");
	}

}
