package me.nbarudi.salem.roles.Town;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Role;

import java.util.ArrayList;

public class Mayor extends Role {

	public Mayor(String name) {
		super(name);
		this.attack = 0;
		this.defence = 0;
		this.unique = true;
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("You may reveal yourself as the Mayor of the Town.");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("Once you have revealed your self as Mayor your vote counts as 3 votes.");
		atrib.add("You may not be healed once you have revealed your self.");
		atrib.add("Once revealed, you can't whisper or be whispered to.");
		
		
		
		this.attributes = atrib;
		this.abilities = abil;
		// Does not visit
		this.priority = 0;
		this.team = SalemBlockade.tm.getTeamFromName("Town");
	}

}
