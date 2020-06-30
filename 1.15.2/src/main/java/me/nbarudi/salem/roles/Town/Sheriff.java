package me.nbarudi.salem.roles.Town;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.PlayerData;
import me.nbarudi.salem.Role;
import me.nbarudi.util.managers.MessageHandler;
import me.nbarudi.util.managers.game.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Sheriff extends Role {

	public Sheriff(String name) {
		super(name);
		this.name = name;
		this.attack = 0;
		this.defence = 0;
		ArrayList<String> abil = new ArrayList<String>();
		ArrayList<String> atrib = new ArrayList<String>();
		
		atrib.add("Interrogate one person each night for suspicious activity.");
		
		abil.add("You will know if your target is suspicious.");
		
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

		Role tRole = tData.getRole();

		if(SalemBlockade.gr.susList.contains(tRole)){
			MessageHandler.privateMessage(Bukkit.getPlayer(pd.getName()), "Your target is suspicious!");
		}else if(tData.framed){
			MessageHandler.privateMessage(Bukkit.getPlayer(pd.getName()), "Your target is suspicious!");
		}else{
			MessageHandler.privateMessage(Bukkit.getPlayer(pd.getName()), "You cannot find evidence of wrongdoing. Your target seems innocent.");
		}

		return true;
	}
}
