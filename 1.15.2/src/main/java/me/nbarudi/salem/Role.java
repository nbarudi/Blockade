package me.nbarudi.salem;

import me.nbarudi.util.managers.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Role {
	
	public static enum RoleTypes {
		RANDOM,
		PRESET
	}

	
	//Basic Info
	public String name;
	public String description;
	public Team team;
	
	//Purely Cosmetic
	public ArrayList<String> attributes;
	public ArrayList<String> abilities;
	
	//Game Info
	public boolean unique = false;
	public int defence = 0;
	public int attack = 0;
	public boolean rbimmune = false;
	public int numVisits = 1;
	
	//Background Systems
	public int priority;
	public RoleTypes roletypes;
	
	public Role(String name) {
		this.name = name;
	}
	
	public List<Role> getRoles() {
		return null;
	}

	public boolean hasWon(){
		return this.team.hasWon();
	}

	public boolean runAbility(PlayerData pd){
		if(pd.visit1 == null && pd.visit2 == null){
			MessageHandler.privateMessage(Bukkit.getPlayer(pd.getName()), ChatColor.DARK_RED + "You visited no one!");
			return false;
		}
		return true;
	}
	

}

