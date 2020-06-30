package me.nbarudi.salem.roles.Town;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.PlayerData;
import me.nbarudi.salem.Role;
import me.nbarudi.util.managers.MessageHandler;
import me.nbarudi.util.managers.game.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Vigilante extends Role{

	public Vigilante(String name) {
		super(name);
		this.attack = 1;
		this.defence = 0;
		
		
		ArrayList<String> abil = new ArrayList<String>();
		abil.add("Choose to take justice into your own hands and shoot someone.");
		
		ArrayList<String> atrib = new ArrayList<String>();
		atrib.add("If you shoot another Town member you will commit suicide over the guilt.");
		atrib.add("You can only shoot your gun 3 times.");
		
		
		this.attributes = atrib;
		this.abilities = abil;
		this.priority = 5;
		this.team = SalemBlockade.tm.getTeamFromName("Town");
	}

	@Override
	public boolean runAbility(PlayerData pd) {

		if(!super.runAbility(pd)) return false;

		Player target = pd.visit1;
		PlayerData t = PlayerManager.getPlayerData(target);

		if(t.isProtected){
			MessageHandler.privateMessage(t, "§aYou were attacked but nursed back to health!");
			for(PlayerData p : t.whoProtected){
				MessageHandler.privateMessage(p, "§4Your target was attacked!");
			}
			return false;
		}
		if(t.getRole().defence >= pd.getRole().defence){
			MessageHandler.privateMessage(target, ChatColor.RED + "You were attacked but your defense is too strong!");
			MessageHandler.privateMessage(Bukkit.getPlayer(pd.getName()), ChatColor.RED + "Your targets defense is too strong to kill!");
			return false;
		}

		if(t.isJailed){
			MessageHandler.privateMessage(target, ChatColor.RED + "You were attacked while in jail!");
			MessageHandler.privateMessage(Bukkit.getPlayer(pd.getName()), ChatColor.RED + "Your target was in jail!");
			return false;
		}

		t.killers.add(ChatColor.GREEN + "Vigilante");
		t.isDead = true;

		return true;
	}
}
