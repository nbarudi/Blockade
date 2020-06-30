package me.nbarudi.salem.util;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.PlayerData;
import me.nbarudi.salem.Role;
import me.nbarudi.util.Wills;
import me.nbarudi.util.managers.MessageHandler;
import me.nbarudi.util.managers.game.PlayerManager;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class GameStateManager implements Listener {

    public static int dayNumber = 0;
    public static int nightNumber = 0;

    static boolean isDefending = false;
    static boolean onjudgement = false;
    static Player voted = null;

    private static int task;

    public static double i = 0;
    static int neededVotes = 0;

    static int totalGuilties = 0;
    static int totalInnos = 0;

    public static HashMap<Player, Integer> pvotes = new HashMap<Player, Integer>();
    public static HashMap<Player, Player> whoVote = new HashMap<Player, Player>();
    public static HashMap<Player, Boolean> votes = new HashMap<Player, Boolean>();

    public static void firstDay(){
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(SalemBlockade.instance, new Runnable(){
            public void run(){
                if(i >= 1){
                    i = 0;
                    Bukkit.getScheduler().cancelTask(task);
                    nextNight();
                }else{
                    BossBarManager.setBossBar("Day 1. Your Hellos!", i);
                    i = i + 0.01;
                }
            }
        }, 20, 3);
    }

    public static void nextDay(){
        dayNumber++;
        PlayerManager.showPlayers();
        if (!checkForWin())
        {
            task = Bukkit.getScheduler().scheduleSyncRepeatingTask(SalemBlockade.instance, new Runnable() {
                public void run() {
                    if(i >= 1) {
                        i = 0;
                        Bukkit.getScheduler().cancelTask(task);
                        //Trigger Voting
                        //Voting.triggerVoting(task);
                        voting();
                    } else {
                        BossBarManager.setBossBar("Day " + dayNumber + ": Discussion.", i);
                        i = i + 0.01;
                    }
                }
            }, 20, 9);
        } else {
            System.out.println("Game over!");
            endGame();
        }
    }

    public static void voting(){
        ArrayList<PlayerData> alive = new ArrayList<PlayerData>();
        makeVoteBook();
        for(PlayerData pd : SalemBlockade.playerdata){
            if(pd.isDead) continue;
            alive.add(pd);
        }

        neededVotes = (alive.size() / 2) + 1;
        MessageHandler.globalAnnounce(neededVotes + " votes are required to judge a player!");

        whoVote.clear();
        pvotes.clear();

        for(PlayerData p : alive) {
            pvotes.put(Bukkit.getPlayer(p.getName()), 0);
        }

        //Handle Boss Bar
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(SalemBlockade.instance, new Runnable() {
            public void run() {
                if(i >= 1) {
                    i = 0;
                    Bukkit.getScheduler().cancelTask(task);
                    nextNight();
                }else {
                    BossBarManager.setBossBar("Day §b" + dayNumber+ "§r: Voting.", 1 - i);
                    i = (i + 0.01);
                }
            }
        }, 20, 6);

    }

    public static void defence(Player player){
        isDefending = true;
        voted = player;

        MessageHandler.globalAnnounce(PlayerManager.getPlayerData(player).getDisguise() + "§a you are being tried for actions against the town. What is your defense?");
        //Teleport Player to the stand.

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(SalemBlockade.instance, new Runnable() {
            public void run() {
                if(i >= 1) {
                    i = 0;
                    Bukkit.getScheduler().cancelTask(task);
                    isDefending = false;
                    judgement();
                }else {
                    BossBarManager.setBossBar("Day §b" + dayNumber+ "§r: Defense.", i);
                    i = (i + 0.01);
                }
            }
        }, 60, 3);
    }


    public static void judgement(){
        onjudgement = true;
        isDefending = false;
        MessageHandler.globalAnnounce("§2Town may now vote §aInnocent §2(inno in chat) or §4Guilty §2(guilty in chat)");
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(SalemBlockade.instance, new Runnable() {
            public void run() {
                if(i >= 1) {
                    i = 0;
                    Bukkit.getScheduler().cancelTask(task);
                    //giveVoteInfo();
                    isDefending = true;
                    if(totalGuilties > totalInnos) {
                        MessageHandler.globalAnnounce(PlayerManager.getPlayerData(voted).getDisguise() + " §2has been voted §4Guilty§2!");
                        lastWords(voted);
                        onjudgement = false;
                    }else {
                        MessageHandler.globalAnnounce(PlayerManager.getPlayerData(voted).getDisguise() + " §2has been voted §aInnocent§2!");
                        voting();
                        onjudgement = false;
                    }
                }else {
                    BossBarManager.setBossBar("Day §b" + dayNumber+ "§r: Judgement.", 1 - i);
                    i = (i + 0.01);
                }
            }
        }, 100, 4);
    }

    public static void lastWords(Player dead){
        isDefending = true;
        voted = dead;
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(SalemBlockade.instance, new Runnable() {
            public void run() {
                if(i >= 1) {
                    i = 0;
                    Bukkit.getScheduler().cancelTask(task);
                    isDefending = false;
                    voted.setHealth(0);
                    postDeathInfo(voted, "They were hung by the town.");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(SalemBlockade.instance, new Runnable() {
                        public void run() {
                            nextNight();
                        }
                    }, 40);
                }else {
                    BossBarManager.setBossBar("Day §b" + dayNumber+ "§r: Any Last Words?.", i);
                    i = (i + 0.01);
                }
            }
        }, 20, 1);
    }


    public static void nextNight(){
        PlayerManager.hidePlayers();
        SalemBlockade.isNight = true;
        nightNumber++;
        abilityBook();
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(SalemBlockade.instance, new Runnable() {
            public void run() {
                if(i >= 1) {
                    i = 0;
                    Bukkit.getScheduler().cancelTask(task);
                    triggerAbilities();
                    nextDay();
                    SalemBlockade.isNight = false;
                }else {
                    BossBarManager.setBossBar("Night §b "+ nightNumber +"§r.", 1 - i);
                    i = i + 0.01;
                }
            }
        }, 100, 6);

    }

    public static boolean checkForWin(){
        ArrayList<PlayerData> alivePlayers = new ArrayList<PlayerData>();
        for(PlayerData pd : SalemBlockade.playerdata){
            if(!pd.isDead){
                alivePlayers.add(pd);
            }
        }

        for(PlayerData pd : alivePlayers){
            Role r = pd.getRole();

            if(r.hasWon()){
                return true;
            }
        }

        return false;

    }


    public static void votePlayer(Player votee, Player voterr, int votes) {

        ArrayList<PlayerData> alive = new ArrayList<PlayerData>();

        for(PlayerData pd : SalemBlockade.playerdata){
            if(pd.isDead) continue;
            alive.add(pd);
        }

        int tempVotes = 0;
        tempVotes = pvotes.get(votee);
        int newVotes = tempVotes + votes;
        pvotes.remove(votee);
        pvotes.put(votee, newVotes);
        if(whoVote.get(voterr) != null) {
            whoVote.remove(voterr);
            MessageHandler.globalAnnounce(PlayerManager.getPlayerData(voterr).getDisguise() + " §2has instead voted against §r" + PlayerManager.getPlayerData(votee).getDisguise() + "§a(§r" + newVotes + "§a).§r");
        }else {
            MessageHandler.globalAnnounce(PlayerManager.getPlayerData(voterr).getDisguise() + " §2has voted against §r" + PlayerManager.getPlayerData(votee).getDisguise() + "§a(§r" + newVotes + "§a).§r");
        }
        whoVote.put(voterr, votee);

        if(neededVotes <= newVotes) {
            Bukkit.getScheduler().cancelTask(task);
            for(PlayerData p : alive) {
                Bukkit.getPlayer(p.name).getInventory().remove(Bukkit.getPlayer(p.name).getInventory().getItem(0));
            }
            defence(votee);
        }

    }

    public static void makeVoteBook(){
        ArrayList<PlayerData> alive = new ArrayList<PlayerData>();

        for(PlayerData pd : SalemBlockade.playerdata){
            if(pd.isDead) continue;
            alive.add(pd);
        }

        for(PlayerData pd : alive) {
            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta bm = (BookMeta) book.getItemMeta();

            ComponentBuilder msg = new ComponentBuilder("");

            for(PlayerData p : alive){
                Role r = pd.getRole();

                if(pd.getName() == p.getName()) continue;
                if(p.isDead) continue;

                msg.append(p.getDisguise());
                msg.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/vote " + p.getDisguise() + " " + SalemBlockade.Key));
                msg.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Vote up " + p.getDisguise()).create()));
                msg.append("\n");
            }

            BaseComponent[] bc = msg.create();
            bm.spigot().addPage(bc);
            bm.setTitle("Voting");
            bm.setAuthor("Bungo");
            book.setItemMeta(bm);
            Bukkit.getPlayer(pd.getName()).getInventory().setItem(0, book);
        }
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();

        if(isDefending){
            if(player == voted);
            else{
                event.setCancelled(true);
            }
            return;
        }

        String msg = event.getMessage();
        if(!onjudgement) return;
        if(event.getPlayer() == voted) return;

        if(votes.get(event.getPlayer()) != null) {
            if(msg.equalsIgnoreCase("guilty")) {
                votes.put(event.getPlayer(), true);
                MessageHandler.globalAnnounce(PlayerManager.getPlayerData(event.getPlayer()).getDisguise() + " has changed voted!");
                event.setCancelled(true);
            }else if(msg.equalsIgnoreCase("inno")) {
                votes.put(event.getPlayer(), false);
                MessageHandler.globalAnnounce(PlayerManager.getPlayerData(event.getPlayer()).getDisguise() + " has changed voted!");
                event.setCancelled(true);
            }
        }else {
            if(msg.equalsIgnoreCase("guilty")) {
                votes.put(event.getPlayer(), true);
                MessageHandler.globalAnnounce(PlayerManager.getPlayerData(event.getPlayer()).getDisguise() + " has voted!");
                event.setCancelled(true);
            }else if(msg.equalsIgnoreCase("inno")) {
                votes.put(event.getPlayer(), false);
                MessageHandler.globalAnnounce(PlayerManager.getPlayerData(event.getPlayer()).getDisguise() + " has voted!");
                event.setCancelled(true);
            }
        }

    }

    public static void giveVoteInfo() {
        votes.forEach((p, v) ->{
            if(v) {
                if(PlayerManager.getPlayerData(p).getRole() == SalemBlockade.rm.getRoleFromName("Mayor") && SalemBlockade.mayorRevealed) {
                    totalGuilties = totalGuilties + 2;
                }
                totalGuilties++;
                MessageHandler.globalAnnounce(PlayerManager.getPlayerData(p).getDisguise() + " §2voted §4Guilty§2!");
            }else {
                totalInnos++;
                MessageHandler.globalAnnounce(PlayerManager.getPlayerData(p).getDisguise() + " §2voted §eInnocent§2!");
            }
        });

        MessageHandler.globalAnnounce("§2The vote ended with §b" + totalGuilties + " §4Guilties§2, and §b" + totalInnos + "§2 Innocents");

    }

    public static void postDeathInfo(Player p, String reason) {
        Role role = PlayerManager.getPlayerData(p).getRole();
        PlayerManager.getPlayerData(p).isDead = true;
        MessageHandler.globalAnnounce(PlayerManager.getPlayerData(p).getDisguise() + " §2has Died.");
        MessageHandler.globalAnnounce(PlayerManager.getPlayerData(p).getDisguise()+ "§2: " + reason);
        MessageHandler.globalAnnounce(PlayerManager.getPlayerData(p).getDisguise() + " §2was the " + role.team + role.name + "§2!");
        MessageHandler.globalAnnounce("§2Everyone has been given a copy of §r" + PlayerManager.getPlayerData(p).getDisguise() + "§2's will.");
        Wills.spreadWill(p);
    }

    public static void abilityBook(){
        ArrayList<PlayerData> alive = new ArrayList<PlayerData>();

        for(PlayerData pd : SalemBlockade.playerdata){
            if(pd.isDead) continue;
            alive.add(pd);
        }

        for(PlayerData pd : alive){
            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta bm = (BookMeta) book.getItemMeta();

            ComponentBuilder msg = new ComponentBuilder("");
            for(PlayerData p : alive){




                if(pd.getName() == p.getName()) continue;

                msg.append(p.getDisguise());
                msg.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/visit1 " + p.getDisguise() + " " + SalemBlockade.Key));
                msg.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Visit " + p.getDisguise()).create()));
                msg.append("\n");

            }

            BaseComponent[] bc = msg.create();
            bm.spigot().addPage(bc);
            bm.setTitle("Abilities");
            bm.setAuthor("Bungo");
            book.setItemMeta(bm);
            Bukkit.getPlayer(pd.getName()).getInventory().setItem(0, book);

        }


    }

    public static void endGame(){

    }

    public static void triggerAbilities(){
        ArrayList<PlayerData> alive = new ArrayList<PlayerData>();
        for(PlayerData pd : SalemBlockade.playerdata){
            if(pd.isDead) continue;
            alive.add(pd);
        }

        ArrayList<PlayerData> sorted = new ArrayList<PlayerData>();
        while(alive.size() != 0){
            PlayerData important = getSorted(alive);
            sorted.add(important);
            alive.remove(important);
        }

        for(PlayerData pd : sorted){
            pd.getRole().runAbility(pd);
            System.out.println("Running " + pd.getName() + "'s ability! Disguise: " + pd.getDisguise() + " | Role: " + pd.getRole().name + " | Priority: " + pd.getPriority());
        }

    }

    public static PlayerData getSorted(ArrayList<PlayerData> alive){
        PlayerData important = null;

        for(PlayerData pd : alive){
            if(important == null){
                important = pd;
                System.out.println(important.getPriority());
            }else{
                if(important.getPriority() < pd.getPriority()){
                    important = pd;
                    System.out.println(important.getPriority());
                }
            }
        }

        System.out.println(important.getPriority());
        return important;
    }

}
