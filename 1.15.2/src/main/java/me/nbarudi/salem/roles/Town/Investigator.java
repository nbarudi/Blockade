package me.nbarudi.salem.roles.Town;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.PlayerData;
import me.nbarudi.salem.Role;
import me.nbarudi.util.managers.MessageHandler;
import me.nbarudi.util.managers.game.PlayerManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Investigator extends Role {

	public Investigator(String name) {
		super(name);
		this.attack = 0;
		this.defence = 0;
		ArrayList<String> abil = new ArrayList<String>();
		ArrayList<String> atrib = new ArrayList<String>();
		
		atrib.add("None");
		
		abil.add("Investigate one person each night for a clue to their role.");
		
		
		this.attributes = atrib;
		this.abilities = abil;
		this.priority = 4;
		this.team = SalemBlockade.tm.getTeamFromName("Town");
	}


	@Override
	public boolean runAbility(PlayerData pd) {
		if(!super.runAbility(pd)) return false;
		Player t = pd.visit1;
		PlayerData target = PlayerManager.getPlayerData(t);
		if(target.isJailed){
			MessageHandler.privateMessage(pd, "§cYour ability failed because your target was in jail!");
			return false;
		}
		if(pd.disguise == null){
			String rr = SalemBlockade.gr.investList.get(target.getRole());
			MessageHandler.privateMessage(pd, "You target is either: " + rr + "!");
			return true;
		}else{
			String rr = SalemBlockade.gr.investList.get(pd.disguise);
			MessageHandler.privateMessage(pd, "Your target is either: " + rr + "!");
			return false;
		}
	}
}
