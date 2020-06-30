package me.nbarudi.salem.roles.Town.Random;

import java.util.ArrayList;
import java.util.List;

import me.nbarudi.salem.Role;
import me.nbarudi.salem.roles.Town.Jailor;
import me.nbarudi.salem.roles.Town.Veteran;
import me.nbarudi.salem.roles.Town.Vigilante;

public class TownKilling extends Role{

	public TownKilling(String name) {
		super(name);
	}
	
	@Override
	public List<Role> getRoles() {
		List<Role> roles = new ArrayList<Role>();
		
		roles.add(new Veteran("Veteran"));
		roles.add(new Vigilante("Vigilante"));
		roles.add(new Jailor("Jailor"));
		//roles.add(new VampireHunter("VampireHunter"));
		
		return roles;
	}

}
