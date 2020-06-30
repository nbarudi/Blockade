package me.nbarudi.salem.util;

import me.nbarudi.salem.Gamemode;
import me.nbarudi.salem.Role;
import me.nbarudi.util.managers.MessageHandler;
import me.nbarudi.util.managers.game.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AssignRoles {
	
	static ArrayList<Player> unassigned = new ArrayList<Player>();
	
	public static void assignRoles(Gamemode gm) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			unassigned.add(p);
		}
		for(Role r : gm.roles) {
			if(unassigned.size() <= 0) {
				MessageHandler.globalAnnounce("Failed to assign roles: " + r.name + "! Not enough players!");
			}else{
				if(r.roletypes == Role.RoleTypes.RANDOM) {
					List<Role> roles = r.getRoles();
					Random rand = new Random();
					int chance = rand.nextInt(roles.size());
					Role role = roles.get(chance);
					int chance2 = rand.nextInt(unassigned.size());
					Player p = unassigned.get(chance2);
					PlayerManager.setRole(p, role);
					unassigned.remove(p);
					PlayerManager.giveRoleInformation(p);
				}else {
					Random rand = new Random();
					int chance = rand.nextInt(unassigned.size());
					Player p = unassigned.get(chance);
					PlayerManager.setRole(p, r);
					unassigned.remove(p);
					PlayerManager.giveRoleInformation(p);
				}
			}
		}
		MessageHandler.globalAnnounce("Roles Assigned!");
	}

}
