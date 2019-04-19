package com.jazibkhan.equalizer;

import android.app.Application;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.LoudnessEnhancer;
import android.media.audiofx.Virtualizer;
import android.os.Build;
import android.util.Log;


//This class returns instances of all the effects and have a lifecycle of the Application

public class EffectInstance extends Application{
    private static volatile Equalizer equalizerInstance;
    private static volatile BassBoost bassBoostInstance;
    private static volatile Virtualizer virtualizerInstance;
    private static volatile LoudnessEnhancer loudnessEnhancerInstance;
    public static int max = Integer.MAX_VALUE;

    private static final String TAG = "EffectInstance";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: EFFECTINSTANCE CREATED");
    }


    static Equalizer getEqualizerInstance(){
        if (equalizerInstance == null) {
            try {
                equalizerInstance = new Equalizer(max,0);
            } catch (RuntimeException e) {
                equalizerInstance = null;
                e.printStackTrace();
            }

        }
        return equalizerInstance;
    }
    static BassBoost getBassBoostInstance(){
        if (bassBoostInstance == null) {
            try {
                bassBoostInstance = new BassBoost(max,0);
            } catch (RuntimeException e) {
                bassBoostInstance = null;
                e.printStackTrace();
            }
        }
        return bassBoostInstance;
    }
    static Virtualizer getVirtualizerInstance(){
        if (virtualizerInstance == null) {
            try {
                virtualizerInstance = new Virtualizer(max,0);
            } catch (RuntimeException e) {
                virtualizerInstance = null;
                e.printStackTrace();
            }
        }
        return virtualizerInstance;
    }
    static LoudnessEnhancer getLoudnessEnhancerInstance(){
        if (loudnessEnhancerInstance == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                try {
                    loudnessEnhancerInstance = new LoudnessEnhancer(0);
                } catch (RuntimeException e) {
                    loudnessEnhancerInstance = null;
                    e.printStackTrace();
                }
            }
        }
        return loudnessEnhancerInstance;
    }
}
