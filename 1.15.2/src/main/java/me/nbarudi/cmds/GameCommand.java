package me.nbarudi.cmds;

import me.nbarudi.main.GameStates;
import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.util.AssignRoles;
import me.nbarudi.salem.util.GameStateManager;
import me.nbarudi.util.AnvilSystems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 0) return false;
		
		if(args[0].equalsIgnoreCase("start")) {

			if(SalemBlockade.gameStarted == true || SalemBlockade.state == GameStates.STARTED){
				sender.sendMessage(ChatColor.RED + "Error! Game already started!");
			}
			
			for(Player p : Bukkit.getOnlinePlayers()) {
				AnvilSystems.salemName(p);
			}
			SalemBlockade.instance.getServer().getScheduler().scheduleSyncDelayedTask(SalemBlockade.instance, new Runnable() {
				@Override
				public void run() {
					AssignRoles.assignRoles(SalemBlockade.gamemode);
					for(Player p : Bukkit.getOnlinePlayers()){
						if(p.getOpenInventory() != null){
							//p.sendMessage(ChatColor.DARK_RED + "You took too long to pick a name!");
							p.closeInventory();
						}
					}
					SalemBlockade.gameStarted = true;
					SalemBlockade.state = GameStates.STARTED;
					GameStateManager.firstDay();
				}
			}, 200);
			
		}
		if(args[0].equalsIgnoreCase("end")) {
			
		}
		
		
		return true;
	}
	
	

}
