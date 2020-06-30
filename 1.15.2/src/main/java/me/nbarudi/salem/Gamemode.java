package me.nbarudi.salem;

import java.util.ArrayList;

public class Gamemode {
	
	public ArrayList<Role> roles;
	public String name;
	
	public Gamemode(String name) {
		this.name = name;
	}
	
	public Gamemode(String name, ArrayList<Role> roles) {
		this.name = name;
		this.roles = roles;
	}
	

}
