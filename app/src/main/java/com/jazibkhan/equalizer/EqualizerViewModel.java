package com.jazibkhan.equalizer;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.LoudnessEnhancer;
import android.media.audiofx.Virtualizer;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;


public class EqualizerViewModel extends AndroidViewModel{
    private static final String TAG = "EqualizerViewModel";
    private PreferenceUtil preferenceUtil;
    private Equalizer equalizer;
    private BassBoost bassBoost;
    private Virtualizer virtualizer;
    private LoudnessEnhancer loudnessEnhancer;
    private Integer virSlider;
    private Integer bBSlider;
    private Float loudSlider;
    private int slider[];
    private int spinnerPos;
    private Boolean virSwitch;
    private Boolean bBSwitch;
    private Boolean loudSwitch;
    private Boolean eqSwitch;
    private Boolean isCustomSelected;
    private MutableLiveData<Boolean> darkTheme;

    public EqualizerViewModel(@NonNull Application application) {
        super(application);
        preferenceUtil = PreferenceUtil.getInstance(application);
        equalizer = EffectInstance.getEqualizerInstance();
        bassBoost = EffectInstance.getBassBoostInstanceInstance();
        virtualizer = EffectInstance.getVirtualizerInstance();
        loudnessEnhancer = EffectInstance.getLoudnessEnhancerInstance();
        darkTheme = new MutableLiveData<>();
        slider = new int[5];
        for(int i=0;i<5;i++) {
            slider[i]=(preferenceUtil.getEqSlider(i));
        }
        virSlider=preferenceUtil.getVirSlider();
        bBSlider=(preferenceUtil.getBBSlider());
        loudSlider=(preferenceUtil.getLoudSlider());
        spinnerPos=(preferenceUtil.getSpinnerPos());
        virSwitch=(preferenceUtil.getVirSwitch());
        bBSwitch=(preferenceUtil.getBBSwitch());
        loudSwitch=(preferenceUtil.getLoudSwitch());
        eqSwitch=(preferenceUtil.getEqSwitch());
        isCustomSelected=(preferenceUtil.getIsCustomSelected());
        darkTheme.setValue(preferenceUtil.getDarkTheme());
    }

    public Equalizer getEqualizer() {
        return equalizer;
    }

    public BassBoost getBassBoost() {
        return bassBoost;
    }

    public Virtualizer getVirtualizer() {
        return virtualizer;
    }

    public LoudnessEnhancer getLoudnessEnhancer() {
        return loudnessEnhancer;
    }

    public int getVirSlider() {
        return virSlider;
    }

    public void setVirSlider(int virSlider) {
        this.virSlider=(virSlider);
        preferenceUtil.setVirSlider(virSlider);
    }

    public int getBBSlider() {
        return bBSlider;
    }

    public void setBBSlider(int bBSlider) {
        this.bBSlider=(bBSlider);
        preferenceUtil.setBBSlider(bBSlider);
    }

    public float getLoudSlider() {
        return loudSlider;
    }

    public void setLoudSlider(float loudSlider) {
        this.loudSlider=(loudSlider);
        preferenceUtil.setLoudSlider(loudSlider);
    }

    public int getSlider(int pos) {
        return slider[pos];
    }

    public void setSlider(int slider, int pos) {
        this.slider[pos]=(slider);
        preferenceUtil.setEqSlider(slider,pos);
    }

    public int getSpinnerPos() {
        return spinnerPos;
    }

    public void setSpinnerPos(int spinnerPos) {
        this.spinnerPos=(spinnerPos);
        preferenceUtil.setSpinnerPos(spinnerPos);
    }

    public boolean getVirSwitch() {
        return virSwitch;
    }

    public void setVirSwitch(boolean virSwitch) {
        this.virSwitch=(virSwitch);
        preferenceUtil.setVirSwitch(virSwitch);
    }

    public boolean getbBSwitch() {
        return bBSwitch;
    }

    public void setbBSwitch(boolean bBSwitch) {
        this.bBSwitch=(bBSwitch);
        preferenceUtil.setBBSwitch(bBSwitch);
    }

    public boolean getLoudSwitch() {
        return loudSwitch;
    }

    public void setLoudSwitch(boolean loudSwitch) {
        this.loudSwitch=(loudSwitch);
        preferenceUtil.setLoudSwitch(loudSwitch);
    }

    public boolean getEqSwitch() {
        return eqSwitch;
    }

    public void setEqSwitch(boolean eqSwitch) {
        this.eqSwitch=(eqSwitch);
        preferenceUtil.setEqSwitch(eqSwitch);
    }

    public boolean getIsCustomSelected() {
        return isCustomSelected;
    }

    public void setIsCustomSelected(boolean isCustomSelected) {
        this.isCustomSelected=(isCustomSelected);
        preferenceUtil.setIsCustomSelected(isCustomSelected);
    }

    public MutableLiveData<Boolean> getDarkTheme() {
        return darkTheme;
    }

    public void setDarkTheme(boolean darkTheme) {
        this.darkTheme.setValue(darkTheme);
        preferenceUtil.setDarkTheme(darkTheme);
    }
}
