package me.nbarudi.events;

import me.nbarudi.main.GameStates;
import me.nbarudi.main.SalemBlockade;
import me.nbarudi.util.managers.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.nbarudi.util.managers.game.PlayerManager;

public class JoinLeaveEvent implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(PlayerManager.playerExists(player))
			return;
		PlayerManager.createPlayerData(player);
	}

	@EventHandler
	public void asyncJoin(AsyncPlayerPreLoginEvent event){
		if(SalemBlockade.state != GameStates.WAITING){
			event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, MessageHandler.prefix + "§4Sorry! The game has already started! \n" + ChatColor.DARK_RED + "If this is a mistake contact Bungo at: \n" + ChatColor.YELLOW + "nbarudi#0001");
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if(PlayerManager.playerExists(player))
			PlayerManager.removePlayerData(player);
	}

}
