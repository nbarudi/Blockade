package me.nbarudi.salem.roles.Town;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.PlayerData;
import me.nbarudi.salem.Role;
import me.nbarudi.util.managers.MessageHandler;
import me.nbarudi.util.managers.game.PlayerManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Lookout extends Role {

	public Lookout(String name) {
		super(name);
		this.attack = 0;
		this.defence = 0;
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("Watch one person at night to see who visits them.");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("None");
		

		

		
		this.attributes = atrib;
		this.abilities = abil;
		this.priority = 4;
		this.team = SalemBlockade.tm.getTeamFromName("Town");
		
	}

	@Override
	public boolean runAbility(PlayerData pd) {
		if(!super.runAbility(pd)) return false;

		Player target = pd.visit1;
		PlayerData tData = PlayerManager.getPlayerData(target);

		String tName = tData.getName();
		ArrayList<PlayerData> alive = new ArrayList<PlayerData>();
		for(PlayerData p : SalemBlockade.playerdata){
			if(p.isDead) continue;
			alive.add(p);
		}

		ArrayList<PlayerData> whoVisits = new ArrayList<PlayerData>();

		for(PlayerData p : alive){
			if(p.visit1.getName() == tName){
				if(p.getName() == pd.getName()) continue;
				whoVisits.add(p);
			}
			else if(p.visit2 == null){}
			else if(p.visit2.getName() == tName && p.getRole().name != "Witch"){
				whoVisits.add(p);
			}
		}

		StringBuilder sb = new StringBuilder();

		if(whoVisits.size() == 0){
			MessageHandler.privateMessage(pd, "No one visited your target!");
			return true;
		}

		for(PlayerData p : whoVisits){
			sb.append(p.getDisguise() + ", ");
		}

		MessageHandler.privateMessage(pd, "Your target was visited by " + sb.toString());

		return true;
	}
}
