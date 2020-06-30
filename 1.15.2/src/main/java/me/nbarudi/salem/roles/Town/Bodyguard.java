package me.nbarudi.salem.roles.Town;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class Bodyguard extends Role{

	public Bodyguard(String name) {
		super(name);
		this.attack = 2;
		this.defence = 0;
		
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("Protect a player from direct attacks at night");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("If your target is attacked or is the victim of a harmful visit, you and the visitor will fight.");
		atrib.add("If you successfully protect someone you can still be Healed");
		atrib.add("You man only wear a bulletproof vest once");
		
		
		this.attributes = atrib;
		this.abilities = abil;
		this.priority = 3;
		this.team = SalemBlockade.tm.getTeamFromName("Town");
	}

}
