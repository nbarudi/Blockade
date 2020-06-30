package me.nbarudi.cmds;

import me.nbarudi.main.SalemBlockade;
import me.nbarudi.salem.PlayerData;
import me.nbarudi.util.managers.MessageHandler;
import me.nbarudi.util.managers.game.PlayerManager;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class VisitCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length < 2 ){
            sender.sendMessage(ChatColor.RED + "Error! Invalid usage! You do not need to run this command, the server runs it for you!");
            return true;
        }

        if(!(sender instanceof Player)){ return true;}

        Player player = (Player) sender;

        if(!args[1].equals(SalemBlockade.Key)){
            sender.sendMessage(ChatColor.RED + "Error! Invalid usage! You do not need to run this command, the server runs it for you!");
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("visit1")){

            PlayerData pd = PlayerManager.getPlayerData(player);

            if(pd.getRole().numVisits == 2){

                Player target = PlayerManager.getPlayerFromName(args[0]);

                if(target == null){
                    player.sendMessage(ChatColor.RED + "Error! Something went wrong! Maybe the player left?");
                    return true;
                }

                pd.setToVisit(target);

                ComponentBuilder msg = new ComponentBuilder("");

                msg.append("Second Target: \n");

                for(PlayerData p : SalemBlockade.playerdata){
                    if(p.getName() == pd.getName()) continue;
                    if(p.isDead) continue;
                    msg.append(p.getDisguise());
                    msg.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/visit2 " + p.getDisguise() + " " + SalemBlockade.Key));
                    msg.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Visit " + p.getDisguise()).create()));
                    msg.append("\n");
                }

                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta bm = (BookMeta) book.getItemMeta();

                BaseComponent[] bc = msg.create();
                bm.spigot().addPage(bc);
                bm.setTitle("Abilities");
                bm.setAuthor("Bungo");
                book.setItemMeta(bm);
                //Bukkit.getPlayer(pd.getName()).getInventory().setItem(0, book);
                player.openBook(book);


                return true;
            }

            Player target = PlayerManager.getPlayerFromName(args[0]);

            if(target == null){
                player.sendMessage(ChatColor.RED + "Error! Something went wrong! Maybe the player left?");
                return true;
            }

            pd.setToVisit(target);
            MessageHandler.privateMessage(player, "You have chosen to visit " + ChatColor.YELLOW + args[0]);



        }else if(cmd.getName().equalsIgnoreCase("visit2")){

            PlayerData pd = PlayerManager.getPlayerData(player);

            Player target = PlayerManager.getPlayerFromName(args[0]);

            if(target == null){
                player.sendMessage(ChatColor.RED + "Error! Something went wrong! Maybe the player left?");
                return true;
            }

            pd.setToVisit(pd.visit1, target);
            MessageHandler.privateMessage(player, "You have chosen to visit " + ChatColor.YELLOW + PlayerManager.getPlayerData(pd.visit1).getDisguise() + ChatColor.AQUA + " into " + ChatColor.YELLOW + args[0]);

        }


        return true;
    }
}
