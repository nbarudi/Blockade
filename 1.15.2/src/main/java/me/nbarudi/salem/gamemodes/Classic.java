package me.nbarudi.salem.gamemodes;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.Gamemode;
import me.nbarudi.salem.Role;
import me.nbarudi.util.managers.game.RoleManager;

import java.util.ArrayList;

public class Classic extends Gamemode {

	RoleManager rm = SalemBlockade.rm;

	public Classic(String name) {
		super(name);
		ArrayList<Role> roles = new ArrayList<Role>();

		roles.add(rm.getRoleFromName("Sheriff"));
		roles.add(rm.getRoleFromName("Lookout"));
		roles.add(rm.getRoleFromName("Investigator"));
		roles.add(rm.getRoleFromName("Jailor"));
		roles.add(rm.getRoleFromName("Doctor"));
		roles.add(rm.getRoleFromName("Escort"));
		roles.add(rm.getRoleFromName("Medium"));
		roles.add(rm.getRoleFromName("TownKilling"));
		roles.add(rm.getRoleFromName("RandomTown"));
		roles.add(rm.getRoleFromName("GodFather"));
		roles.add(rm.getRoleFromName("Mafioso"));
		roles.add(rm.getRoleFromName("Framer"));
		roles.add(rm.getRoleFromName("SerialKiller"));
		roles.add(rm.getRoleFromName("Executioner"));
		roles.add(rm.getRoleFromName("Jester"));
		this.roles = roles;
		
	}

}
