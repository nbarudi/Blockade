package me.nbarudi.events;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.PlayerData;
import me.nbarudi.salem.Role;
import me.nbarudi.salem.Team;
import me.nbarudi.util.managers.MessageHandler;
import me.nbarudi.util.managers.game.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent event){

        if(!SalemBlockade.gameStarted) return;

        Player player = event.getPlayer();
        PlayerData pd = PlayerManager.getPlayerData(player);

        Role role = pd.role;
        Team team = role.team;

        event.setCancelled(true);

        if(SalemBlockade.isNight){
            if(pd.isDead){
                MessageHandler.deadNightMessage(pd, event.getMessage());
            }
           if(!team.nightChat){
               if(role.equals(SalemBlockade.rm.getRoleFromName("Medium"))){
                   MessageHandler.mediumMessage(pd, event.getMessage());
               }
           }else{
               for(PlayerData playerData : SalemBlockade.playerdata){
                   Team newteam = playerData.role.team;
                   if(newteam == team){
                       Player teammate = Bukkit.getPlayer(playerData.name);
                       teammate.sendMessage(MessageHandler.messageFormatting(playerData, event.getMessage()));
                       //player.sendMessage(MessageHandler.messageFormatting(pd, event.getMessage()));
                   }
               }
           }


           if(pd.role == SalemBlockade.rm.getRoleFromName("Jailor")){
               MessageHandler.jailorMessage(pd, event.getMessage());
           }else{
               if(pd.isJailed){
                   for(PlayerData playerData : SalemBlockade.playerdata){
                       if(playerData.role == SalemBlockade.rm.getRoleFromName("Jailor")){
                           Player jailor = Bukkit.getPlayer(pd.name);
                           jailor.sendMessage(MessageHandler.messageFormatting(pd, event.getMessage()));
                       }
                   }
               }
           }

        }else{
            if(pd.isDead){
                MessageHandler.deadMessage(pd, event.getMessage());
            }
            Bukkit.broadcastMessage(MessageHandler.messageFormatting(pd, event.getMessage()));
        }

    }


}
