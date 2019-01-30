package com.jazibkhan.equalizer;

/**
 * Created by Jazib on 2/11/2018.
 */

public class Constants {
    public interface ACTION {
        String MAIN_ACTION = "com.jazibkhan.equalizer.action.main";
        String STARTFOREGROUND_ACTION = "com.jazibkhan.foregroundservice.action.startforeground";
        String STARTFOREGROUND_ACTION_EQ = "com.jazibkhan.foregroundservice.action.starteq";
        String STARTFOREGROUND_ACTION_BB = "com.jazibkhan.foregroundservice.action.startbb";
        String STARTFOREGROUND_ACTION_VIRTUALIZER = "com.jazibkhan.foregroundservice.action.startvirtualizer";
        String STARTFOREGROUND_ACTION_LOUDNESS = "com.jazibkhan.foregroundservice.action.startloudness";
        String STOPFOREGROUND_ACTION = "com.jazibkhan.foregroundservice.action.stopforeground";
        String STOPFOREGROUND_ACTION_EQ = "com.jazibkhan.foregroundservice.action.stopeq";
        String STOPFOREGROUND_ACTION_BB = "com.jazibkhan.foregroundservice.action.stopbb";
        String STOPFOREGROUND_ACTION_VIRTUALIZER = "com.jazibkhan.foregroundservice.action.stopvirtualizer";
        String STOPFOREGROUND_ACTION_LOUDNESS = "com.jazibkhan.foregroundservice.action.stoploudness";
    }

    public interface NOTIFICATION_ID {
        int FOREGROUND_SERVICE = 101;
    }
}