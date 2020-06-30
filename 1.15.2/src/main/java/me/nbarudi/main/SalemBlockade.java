package me.nbarudi.main;

import me.nbarudi.cmds.GameCommand;
import me.nbarudi.cmds.VisitCommand;
import me.nbarudi.cmds.VoteCommand;
import me.nbarudi.events.ChatEvent;
import me.nbarudi.events.JoinLeaveEvent;
import me.nbarudi.salem.Gamemode;
import me.nbarudi.salem.PlayerData;
import me.nbarudi.salem.gamemodes.Classic;
import me.nbarudi.salem.util.GameStateManager;
import me.nbarudi.salem.util.GlobalResponses;
import me.nbarudi.util.managers.FileManager;
import me.nbarudi.util.managers.game.RoleManager;
import me.nbarudi.util.managers.game.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class SalemBlockade extends JavaPlugin {

	//Globals
	public static Plugin instance;
	public static TeamManager tm;
	public static RoleManager rm;
	public static GlobalResponses gr;
    public static int lastPlayerNumber = 0;

    public FileManager fm = new FileManager(this);
	
	
	//Player Systems
	public static ArrayList<PlayerData> playerdata = new ArrayList<PlayerData>();
	public static ArrayList<String> takenNames = new ArrayList<String>();
	public static ArrayList<String> defaultnames = new ArrayList<String>();

	//Background Systems
	public static Gamemode gamemode;
	public static boolean isNight = false;
	public static boolean gameStarted = false;
	public static Player jailedPlayer;
	public static GameStates state = GameStates.WAITING;
	public static boolean mayorRevealed = false;

	public static String Key;

	public void onEnable() {
	    Key = randomAlphaNumeric(16);
		instance = this;
		register();
		gamemode = new Classic("Classic");
	}
	public void onDisable() {}
	
	
	
	
	private void register() {
			registerCommands();
			registerConfig();
			registerEvents();
			registerManagers();
			registerSalemNames();
	}
	
	private void registerCommands() {

	    this.getCommand("game").setExecutor(new GameCommand());
	    this.getCommand("vote").setExecutor(new VoteCommand());
	    this.getCommand("visit1").setExecutor(new VisitCommand());
	    this.getCommand("visit2").setExecutor(new VisitCommand());
	}
	private void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinLeaveEvent(), this);
		pm.registerEvents(new ChatEvent(), this);
		pm.registerEvents(new GameStateManager(), this);
	}
	private void registerConfig() {}
	private void registerManagers(){
		tm = new TeamManager();
		rm = new RoleManager();
		gr = new GlobalResponses();
	}
	private void registerSalemNames() {
		defaultnames.add("CottonMather");
		defaultnames.add("DeodatLawson");
		defaultnames.add("EdwardBishop");
		defaultnames.add("GilesCorey");
		defaultnames.add("JamesBayley");
		defaultnames.add("JamesRussel");
		defaultnames.add("JohnHathorne");
		defaultnames.add("JohnProctor");
		defaultnames.add("JohnWillard");
		defaultnames.add("JonathanCorwin");
		defaultnames.add("SamuelParris");
		defaultnames.add("SamuelSewall");
		defaultnames.add("ThomasDanforth");
		defaultnames.add("WilliamHobbs");
		defaultnames.add("WilliamPhips");
		defaultnames.add("AbigailHobbs");
		defaultnames.add("AliceYoung");
		defaultnames.add("AnnHibbins");
		defaultnames.add("AnnPutnam");
		defaultnames.add("AnnSears");
		defaultnames.add("BettyParris");
		defaultnames.add("DorothyGood");
		defaultnames.add("LydiaDustin");
		defaultnames.add("MarthaCorey");
		defaultnames.add("MaryEastey");
		defaultnames.add("MaryJohnson");
		defaultnames.add("MaryWarren");
		defaultnames.add("SarahBishop");
		defaultnames.add("SarahGood");
		defaultnames.add("SarahWildes");
	}

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

}
