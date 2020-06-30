package me.nbarudi.util.managers;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class MessageHandler {
	
	public static String prefix = GRAY + "[" + GREEN + "Salem" + GRAY + ":" + DARK_GRAY + "Blockade" + GRAY + "] " + AQUA;
	public static String pmprefix = GRAY + "(PM)" + RESET;
	
	public static void globalAnnounce(String message) {
		Bukkit.broadcastMessage(prefix + message);
	}
	
	public static void privateMessage(Player player, String message) {
		player.sendMessage(pmprefix + prefix + message);
	}
	public static void privateMessage(PlayerData pd, String message){
		Player player = Bukkit.getPlayer(pd.getName());
		player.sendMessage(pmprefix + prefix + message);
	}
	
	public static void privateMessage(CommandSender sender, String message) {
		sender.sendMessage(pmprefix + prefix + message);
	}

	public static String messageFormatting(PlayerData pd, String message){
		return GRAY + "(" + pd.playerPosition + ") " + RESET + pd.hname + ": " + message;
	}

	public static void mediumMessage(PlayerData pd, String message){
		for(PlayerData playerData : SalemBlockade.playerdata){
			if(playerData.isDead){
				Player deadPlayer = Bukkit.getPlayer(playerData.name);
				deadPlayer.sendMessage( BLUE + "Medium: " + RESET + message);
				Bukkit.getPlayer(pd.name).sendMessage(BLUE  + "Medium: " + RESET + message);
			}
		}
	}

	public static void deadMessage(PlayerData pd, String message){
		for(PlayerData playerData : SalemBlockade.playerdata){
			if(playerData.isDead){
				Player deadPlayer = Bukkit.getPlayer(playerData.name);
				deadPlayer.sendMessage(RED + pd.hname + ": " + RESET + message);
			}
		}
	}

	public static void deadNightMessage(PlayerData pd, String message){
		for(PlayerData playerData : SalemBlockade.playerdata){
			if(playerData.isDead){
				Player deadPlayer = Bukkit.getPlayer(playerData.name);
				deadPlayer.sendMessage(RED + pd.hname + ": "  + RESET + message);
			}
			if(playerData.role == SalemBlockade.rm.getRoleFromName("Medium")){
				Player deadPlayer = Bukkit.getPlayer(playerData.name);
				deadPlayer.sendMessage(RED + pd.hname + ": " + RESET + message);
			}
		}
	}

	public static void jailorMessage(PlayerData pd, String message){
		Player jailed = SalemBlockade.jailedPlayer;
		Player jailor = Bukkit.getPlayer(pd.name);
		if(jailed == null) return;
		jailed.sendMessage(YELLOW + "Jailor: " + RESET + message);
		jailor.sendMessage( YELLOW + "Jailor: " + RESET +  message);
	}

}
