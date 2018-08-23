package com.jazibkhan.equalizer;

/**
 * Created by Jazib on 2/11/2018.
 */

public class Constants {
    public interface ACTION {
        public static String MAIN_ACTION = "com.jazibkhan.equalizer.action.main";
        public static String STARTFOREGROUND_ACTION = "com.jazibkhan.foregroundservice.action.startforeground";
        public static String STARTFOREGROUND_ACTION_EQ = "com.jazibkhan.foregroundservice.action.starteq";
        public static String STARTFOREGROUND_ACTION_BB = "com.jazibkhan.foregroundservice.action.startbb";
        public static String STARTFOREGROUND_ACTION_VIRTUALIZER = "com.jazibkhan.foregroundservice.action.startvirtualizer";
        public static String STARTFOREGROUND_ACTION_LOUDNESS = "com.jazibkhan.foregroundservice.action.startloudness";
        public static String STOPFOREGROUND_ACTION = "com.jazibkhan.foregroundservice.action.stopforeground";
        public static String STOPFOREGROUND_ACTION_EQ = "com.jazibkhan.foregroundservice.action.stopeq";
        public static String STOPFOREGROUND_ACTION_BB = "com.jazibkhan.foregroundservice.action.stopbb";
        public static String STOPFOREGROUND_ACTION_VIRTUALIZER = "com.jazibkhan.foregroundservice.action.stopvirtualizer";
        public static String STOPFOREGROUND_ACTION_LOUDNESS = "com.jazibkhan.foregroundservice.action.stoploudness";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}