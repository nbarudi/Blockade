package me.nbarudi.salem;

import me.nbarudi.main.SalemBlockade;
import org.bukkit.ChatColor;

import java.util.ArrayList;

public class Team {
	
	public String name;
	public ChatColor color;
	public ArrayList<Team> winconditions;
	public boolean nightChat;
	
	public Team(String name) {
		this.name = name;
	}

	public boolean hasWon(){
		for(PlayerData data : SalemBlockade.playerdata){
			Team team = data.role.team;
			if(this.winconditions.contains(team)){
				continue;
			}else{
				return false;
			}
		}
		return true;
	}

}
