package me.nbarudi.salem.roles.Neutral;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class Jester extends Role {

	public Jester(String name) {
		super(name);
		this.attack = 0;
		this.defence = 0;
		
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("None");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("If you are lynched you will attack one if your guilty or abstaining voters the following night with an Unstoppable attack.");
		
		
		this.attributes = atrib;
		this.abilities = abil;
		this.priority = 1;
		this.team = SalemBlockade.tm.getTeamFromName("NeutralEvil");
	}

}
