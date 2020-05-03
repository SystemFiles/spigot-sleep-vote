package ca.sykesdev.sleepvote.Utils;

import org.bukkit.ChatColor;

public class Message {
    public static String formatMessage(String msg) {
        return ChatColor.translateAlternateColorCodes('&', "&6[" + " &fSleepVote "
                + "&6]&f " + msg);
    }
}
