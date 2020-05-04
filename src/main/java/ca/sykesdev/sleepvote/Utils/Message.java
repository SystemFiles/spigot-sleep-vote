package ca.sykesdev.sleepvote.Utils;

import org.bukkit.ChatColor;

public class Message {
    /**
     * Standard message formatter for this plugin
     * @param msg The message input
     * @return The formatted message
     */
    public static String formatMessage(String msg) {
        return ChatColor.translateAlternateColorCodes('&', "&6[" + " &fSleepVote "
                + "&6]&f " + msg);
    }
}
