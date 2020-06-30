package me.nbarudi.salem;

import org.bukkit.entity.Player;
import org.inventivetalent.nicknamer.api.NickManager;
import org.inventivetalent.nicknamer.api.NickNamerAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerData {
	
	public String name;
	public String uuid;
	public Role role;
	public int priority;
	public String hname;
	public boolean isAdmin = false;
	public boolean isHost = false;
	public Player visit1;
	public Player visit2;
	public boolean isJailed = false;
	public boolean isDead = false;
	public int playerPosition = 0;
	public boolean framed = false;
	public ArrayList<String> killers = new ArrayList<String>();
	public Role disguise = null;
	public boolean isAttacked = false;
	public boolean isProtected = false;
	public ArrayList<PlayerData> whoProtected = new ArrayList<PlayerData>();
	public boolean roleBlocked = false;
	
	public PlayerData(String name, String uuid) {
		this.name = name;
		this.uuid = uuid;
	}
	
	public Role getRole() {
		return role;
	}
	public boolean setRole(Role r) {
		role = r;
		return true;
	}
	
	public boolean getAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}
	
	public boolean getHost() {
		return isHost;
	}
	public void setHost(boolean host) {
		isHost = host;
	}
	
	public String getUUID() {
		return uuid;
	}
	public String getName() {
		return name;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String getDisguise() {
		return hname;
	}
	
	public boolean getJailed() {
		return isJailed;
	}
	
	public void setJailed(boolean jailed) {
		isJailed = jailed;
	}
	
	public List<Player> getVisits(){
		List<Player> visits = new ArrayList<Player>();
		
		if(visit1 == null) {
			return null;
		}
		if (visit2 == null) {
			visits.add(visit1);
			visits.add(visit2);
		}else {
			visits.add(visit1);
		}
		
		return visits;
	}
	
	public void setToVisit(Player target) {
		visit1 = target;
	}
	public void setToVisit(Player target, Player target2) {
		visit1 = target;
		visit2 = target2;
	}
	
	public void setDisguise(String name) {
		NickManager nm = NickNamerAPI.getNickManager();
		UUID u = UUID.fromString(uuid);
		nm.setNick(u, name);
		hname = name;
	}
	
	public void setDisguise(String name, String skinname) {
		NickManager nm = NickNamerAPI.getNickManager();
		UUID u = UUID.fromString(uuid);
		nm.setNick(u, name);
		nm.setSkin(u, skinname);
		hname = name;
	}
	
	
}
