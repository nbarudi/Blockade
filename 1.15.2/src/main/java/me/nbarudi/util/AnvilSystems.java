package me.nbarudi.util;

import me.nbarudi.AnvilGUI.AnvilGUI;
import me.nbarudi.AnvilGUI.AnvilGUI.Builder;
import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.PlayerData;
import me.nbarudi.util.managers.MessageHandler;
import me.nbarudi.util.managers.game.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class AnvilSystems {
	
	static ItemStack item = new ItemStack(Material.PAPER);
	
	public static void salemName(Player plr) {
		
		Builder ag = new AnvilGUI.Builder();
		ag.onClose(p ->{
			PlayerData pd = PlayerManager.getPlayerData(p);
			if(pd.getDisguise() == null) {
				MessageHandler.privateMessage(p, "You will be given a default name!");
				Random rand = new Random();
				int random = rand.nextInt(SalemBlockade.defaultnames.size());
				String name = SalemBlockade.defaultnames.get(random);
				MessageHandler.privateMessage(p, "Your name: " + name);
				pd.setDisguise(name);
			}
		});
		ag.onComplete((p, name) ->{
			if(name.length() > 16) {
				return AnvilGUI.Response.text("Name too long!");
			}
			if(SalemBlockade.takenNames.contains(name)) {
				return AnvilGUI.Response.text("Name is taken!");
			}
			
			SalemBlockade.takenNames.add(name);
			PlayerData pd = PlayerManager.getPlayerData(p);
			pd.setDisguise(name);
			MessageHandler.privateMessage(p, "Your name has been set to: " + name);
			return AnvilGUI.Response.close();
		});
		ag.item(item);
		ag.title("Set your Name!");
		ag.text("Close for default name!");
		ag.open(plr);
		
	}

}
