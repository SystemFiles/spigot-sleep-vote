package ca.sykesdev.sleepvote.Listeners;

import ca.sykesdev.sleepvote.Sleepvote;
import ca.sykesdev.sleepvote.Utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import java.util.ArrayList;

public class PlayerStartSleepingListener implements Listener {

    private Sleepvote plugin;
    private ArrayList<String> votes;

    public PlayerStartSleepingListener(Sleepvote plugin) {
        this.plugin = plugin;
        this.votes = plugin.getVotes();
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        if (event.getPlayer().getWorld().getName().endsWith("_nether")
            || event.getPlayer().getWorld().getName().endsWith("_the_end")) {
            return;
        }

        // Make sure its actually night time
        if (event.getPlayer().getWorld().getTime() >= 12000) {
            // Check if first vote
            if (votes.size() == 0)
                this.plugin.getServer().broadcastMessage(Message.formatMessage(ChatColor.GOLD
                        + event.getPlayer().getDisplayName() + ChatColor.WHITE + " has just started a sleep vote!"));

            // Update vote count for server
            votes.add(event.getPlayer().getUniqueId().toString());
            this.plugin.getServer().broadcastMessage(Message.formatMessage("Sleep vote: " + ChatColor.GOLD
                    + this.votes.size() + ChatColor.WHITE + "/" + ChatColor.GREEN
                    + this.plugin.getServer().getOnlinePlayers().size() + ChatColor.WHITE + " players voted for daytime!"));

            // If half OR more of the server is sleeping, make it daytime..
            if (votes.size() >= (this.plugin.getServer().getOnlinePlayers().size() / 2)) {
                event.getPlayer().getWorld().setTime(1000);
                this.votes = new ArrayList<>(); // Reset votes after success
                this.plugin.getServer().broadcastMessage(Message.formatMessage("Sleep vote successful!"));
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
        if (votes.contains(event.getPlayer().getUniqueId().toString())) {
            this.plugin.getServer().broadcastMessage(Message.formatMessage(ChatColor.GOLD
                    + event.getPlayer().getDisplayName() + ChatColor.WHITE + " has cancelled their vote."));
            this.plugin.getServer().broadcastMessage(Message.formatMessage("Sleep vote: " + ChatColor.GOLD
                    + this.votes.size() + ChatColor.WHITE + "/" + ChatColor.GREEN
                    + this.plugin.getServer().getOnlinePlayers().size() + ChatColor.WHITE + " players voted for daytime!"));

            // Remove player for voters
            votes.remove(event.getPlayer().getUniqueId().toString());
        }
    }
}
