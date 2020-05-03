package ca.sykesdev.sleepvote;

import ca.sykesdev.sleepvote.Listeners.PlayerStartSleepingListener;
import ca.sykesdev.sleepvote.Utils.Message;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Sleepvote extends JavaPlugin {

    private ArrayList<String> votes = new ArrayList<>();

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new PlayerStartSleepingListener(this), this);
        this.getServer().broadcastMessage(Message.formatMessage("SleepVote initialized successfully!"));
    }

    @Override
    public void onDisable() {
        this.getServer().broadcastMessage(Message.formatMessage("SleepVote deactivated..."));
    }

    public ArrayList<String> getVotes() {
        return votes;
    }
}
