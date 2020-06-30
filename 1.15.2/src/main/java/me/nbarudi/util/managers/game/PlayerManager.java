package me.nbarudi.util.managers.game;


import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.PlayerData;
import me.nbarudi.salem.Role;
import me.nbarudi.salem.Team;
import me.nbarudi.util.managers.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class PlayerManager {
	
	public static PlayerData getPlayerData(Player player) {
		PlayerData playerdata = null;
		for(PlayerData pd : SalemBlockade.playerdata)
			if(pd.getName() == player.getName())
				return pd;
		return playerdata;
	}

	public static void createPlayerData(Player player) {
		PlayerData pd = new PlayerData(player.getName(), player.getUniqueId().toString());
		pd.playerPosition = SalemBlockade.lastPlayerNumber + 1;
		SalemBlockade.playerdata.add(pd);
	}
	
	public static void removePlayerData(Player player) {
		PlayerData removal = null;
		for(PlayerData pd : SalemBlockade.playerdata) {
			if(pd.getName() == player.getName()) {
				removal = pd;
			}
		}
		if(removal == null) return;
		SalemBlockade.playerdata.remove(removal);
			
	}
	
	public static boolean playerExists(Player player) {
		for(PlayerData pd : SalemBlockade.playerdata) 
			if(pd.getName() == player.getName())
				return true;
		return false;
	}
	
	public static void setRole(Player p, Role r) {
		PlayerData pd = PlayerManager.getPlayerData(p);
		pd.setRole(r);
		pd.setPriority(r.priority);
	}

	public static Player getPlayerFromName(String name){
		System.out.println("Told to find: " + name);
		for(PlayerData pd : SalemBlockade.playerdata){
			System.out.println("Checking PlayerData: " + pd.getName() + " | Disguise: " + pd.getDisguise());
			if(pd.isDead) continue;
			if(pd.getDisguise().equalsIgnoreCase(name)){
				System.out.println("Disguise Matches Data!");
				return Bukkit.getPlayer(pd.getName());
			}
		}
		return null;
	}
	
	public static void giveRoleInformation(Player p) {
		PlayerData pd = PlayerManager.getPlayerData(p);
		Role r = pd.role;
		MessageHandler.privateMessage(p, "You are the: " + r.team.color + r.name);
		//MessageHandler.privateMessage(p, r.description);
		MessageHandler.privateMessage(p, AQUA + "" + BOLD + "Attack: " + YELLOW + attackConvert(r.attack) + AQUA + " | " + BOLD + "Defence: " + YELLOW + defenceConvert(r.defence));
		MessageHandler.privateMessage(p, GOLD + "" + BOLD + "Attributes:");
		for(String name : r.attributes) {
			MessageHandler.privateMessage(p, "- " + name);
		}
		MessageHandler.privateMessage(p, GOLD + "" + BOLD + "Abilities:");
		for(String name : r.abilities) {
			MessageHandler.privateMessage(p, "- " + name);
		}
		Team team = r.team;
		MessageHandler.privateMessage(p, GOLD + "Winning Conditions:");
		for(Team t : team.winconditions) {
			MessageHandler.privateMessage(p, "- " + t.color + t.name);
		}
	}
	
	public static String attackConvert(int a) {
		String attack = null;
		if(a == 0) attack = "None";
		if(a == 1) attack = "Basic";
		if(a == 2) attack = "Powerful";
		if(a == 3) attack = "Unstoppable";
		
		return attack;
		
	}
	
	public static String defenceConvert(int d) {
		String defense = null;
		if(d == 0) defense = "None";
		if(d == 1) defense = "Basic";
		if(d == 2) defense = "Strong";
		if(d == 3) defense = "Invinicble";
		
		return defense;
	}

	public static void hidePlayers(){

		for(Player player : Bukkit.getOnlinePlayers()){
			PlayerData pd = PlayerManager.getPlayerData(player);
			if(pd.isDead) return;
			for(Player t : Bukkit.getOnlinePlayers()){
				//if(pd.isDead) return;
				if(player == t) continue;
				t.hidePlayer(SalemBlockade.instance, player);
			}
		}

		for(Player player : Bukkit.getOnlinePlayers()){
			PlayerData pd = PlayerManager.getPlayerData(player);
			if(pd.isDead) return;
			if(!pd.getRole().team.nightChat) return;
			for(Player t : Bukkit.getOnlinePlayers()){
				if(player == t) continue;
				PlayerData tdata = PlayerManager.getPlayerData(t);
				if(tdata.getRole().team == pd.getRole().team){
					t.showPlayer(SalemBlockade.instance, player);
				}else{
					continue;
				}
			}

		}

	}

	public static void showPlayers(){
		for(Player p : Bukkit.getOnlinePlayers()) {
			for(Player t : Bukkit.getOnlinePlayers()) {
				if(p == t) continue;
				p.showPlayer(SalemBlockade.instance, t);
			}
		}
	}

}
