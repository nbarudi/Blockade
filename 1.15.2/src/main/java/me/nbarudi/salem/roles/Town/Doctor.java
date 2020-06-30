package me.nbarudi.salem.roles.Town;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.PlayerData;
import me.nbarudi.salem.Role;
import me.nbarudi.util.managers.MessageHandler;
import me.nbarudi.util.managers.game.PlayerManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Doctor extends Role {

	public Doctor(String name) {
		super(name);
		this.attack = 0;
		this.defence = 0;
		
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("Heal one person each night, granting them a powerful defense.");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("You may only Heal yourself once.");
		atrib.add("You will know if your target is attacked.");
		
		
		this.attributes = atrib;
		this.abilities = abil;
		this.priority = 3;
		this.team = SalemBlockade.tm.getTeamFromName("Town");
	}

	@Override
	public boolean runAbility(PlayerData pd) {
		if(!super.runAbility(pd)) return false;

		Player target = pd.visit1;
		PlayerData t = PlayerManager.getPlayerData(target);

		if(t.isJailed){
			MessageHandler.privateMessage(pd, "Your ability failed because your target was in jail!");
			return false;
		}

		t.isProtected = true;
		t.whoProtected.add(pd);

		return true;
	}
}
