package me.nbarudi.cmds;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.util.GameStateManager;
import me.nbarudi.util.managers.game.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return false;
        }

        if(PlayerManager.getPlayerData((Player) sender).isDead) {
            sender.sendMessage("You cannot run this command as a spectator!");
            return false;
        }

        if(args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Please do not use this command. It is all handled for you at night.");
            return false;
        }

        if(!args[1].equals(SalemBlockade.Key)) {
            sender.sendMessage(ChatColor.RED + "Please do not use this command. It is all handled for you at night.");
            return false;
        }


        Player player = (Player) sender;
        int votePower = 1;

        if(PlayerManager.getPlayerData(player).role == SalemBlockade.rm.getRoleFromName("Mayor") && SalemBlockade.mayorRevealed) {
            votePower = 3;
        }

        GameStateManager.votePlayer(Bukkit.getPlayer(args[0]), player, votePower);

        return false;

    }
}
