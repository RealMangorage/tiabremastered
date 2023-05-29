package me.mangorage.tiabremastered.common.core;

public class Util {

    /**
     * @param totalTicks ticks
     * @return
     */
    public static String getFormattedTimeString(int totalTicks) {
        String returned = "";

        int ticks = totalTicks % 20;
        int totalSeconds = totalTicks / 20;

        int seconds = totalSeconds % 60;
        int totalminutes = totalSeconds / 60;

        int minutes = totalminutes % 60;
        int totalhours = totalminutes / 60;

        int hours = totalhours % 24;
        int totaldays = totalhours / 24;

        if (ticks > 0)
            returned = ticks + "T";
        if (seconds > 0)
            returned = seconds + "S " + returned;
        if (minutes > 0)
            returned = minutes + "M " + returned;
        if (hours > 0)
            returned = hours + "H " + returned;
        if (totaldays > 0)
            returned = totaldays + "D " + returned;

        return returned.strip();
    }
}
