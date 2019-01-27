package com.jazibkhan.equalizer;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.LoudnessEnhancer;
import android.media.audiofx.Virtualizer;
import android.support.annotation.NonNull;

import java.util.ArrayList;


public class EqualizerViewModel extends AndroidViewModel{
    private PreferenceUtil preferenceUtil;
    private Equalizer equalizer;
    private BassBoost bassBoost;
    private Virtualizer virtualizer;
    private LoudnessEnhancer loudnessEnhancer;
    private MutableLiveData<Integer> virSlider;
    private MutableLiveData<Integer> bBSlider;
    private MutableLiveData<Integer> loudSlider;
    private MutableLiveData<ArrayList<Integer>> slider;
    private MutableLiveData<Integer> spinnerPos;
    private MutableLiveData<Boolean> virSwitch;
    private MutableLiveData<Boolean> bBSwitch;
    private MutableLiveData<Boolean> loudSwitch;
    private MutableLiveData<Boolean> eqSwitch;
    private MutableLiveData<Boolean> isCustomSelected;
    private MutableLiveData<Boolean> darkTheme;

    public EqualizerViewModel(@NonNull Application application) {
        super(application);
        preferenceUtil = PreferenceUtil.getInstance(application);
        equalizer = EffectInstance.getEqualizerInstance();
        bassBoost = EffectInstance.getBassBoostInstanceInstance();
        virtualizer = EffectInstance.getVirtualizerInstance();
        loudnessEnhancer = EffectInstance.getLoudnessEnhancerInstance();
        virSlider = new MutableLiveData<>();
        bBSlider = new MutableLiveData<>();
        loudSlider = new MutableLiveData<>();
        spinnerPos = new MutableLiveData<>();
        virSwitch = new MutableLiveData<>();
        bBSwitch = new MutableLiveData<>();
        loudSwitch = new MutableLiveData<>();
        eqSwitch = new MutableLiveData<>();
        isCustomSelected = new MutableLiveData<>();
        darkTheme = new MutableLiveData<>();
        virSlider.setValue(preferenceUtil.getVirSlider());
        bBSlider.setValue(preferenceUtil.getBBSlider());
        loudSlider.setValue(preferenceUtil.getLoudSlider());
        spinnerPos.setValue(preferenceUtil.getSpinnerPos());
        virSwitch.setValue(preferenceUtil.getVirSwitch());
        bBSwitch.setValue(preferenceUtil.getBBSwitch());
        loudSwitch.setValue(preferenceUtil.getLoudSwitch());
        eqSwitch.setValue(preferenceUtil.getEqSwitch());
        isCustomSelected.setValue(preferenceUtil.getIsCustomSelected());
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

    public MutableLiveData<Integer> getVirSlider() {
        return virSlider;
    }

    public void setVirSlider(int virSlider) {
        this.virSlider.setValue(virSlider);
        preferenceUtil.setVirSlider(virSlider);
    }

    public MutableLiveData<Integer> getBBSlider() {
        return bBSlider;
    }

    public void setBBSlider(int bBSlider) {
        this.bBSlider.setValue(bBSlider);
        preferenceUtil.setBBSlider(bBSlider);
    }

    public MutableLiveData<Integer> getLoudSlider() {
        return loudSlider;
    }

    public void setLoudSlider(int loudSlider) {
        this.loudSlider.setValue(loudSlider);
        preferenceUtil.setLoudSlider(loudSlider);
    }

    public MutableLiveData<ArrayList<Integer>> getSlider() {
        return slider;
    }

    public void setSlider(ArrayList<Integer> slider,int pos) {
        this.slider.setValue(slider);
        preferenceUtil.setEqSlider(slider.get(pos),pos);
    }

    public MutableLiveData<Integer> getSpinnerPos() {
        return spinnerPos;
    }

    public void setSpinnerPos(int spinnerPos) {
        this.spinnerPos.setValue(spinnerPos);
        preferenceUtil.setSpinnerPos(spinnerPos);
    }

    public MutableLiveData<Boolean> getVirSwitch() {
        return virSwitch;
    }

    public void setVirSwitch(boolean virSwitch) {
        this.virSwitch.setValue(virSwitch);
        preferenceUtil.setVirSwitch(virSwitch);
    }

    public MutableLiveData<Boolean> getbBSwitch() {
        return bBSwitch;
    }

    public void setbBSwitch(boolean bBSwitch) {
        this.bBSwitch.setValue(bBSwitch);
        preferenceUtil.setBBSwitch(bBSwitch);
    }

    public MutableLiveData<Boolean> getLoudSwitch() {
        return loudSwitch;
    }

    public void setLoudSwitch(boolean loudSwitch) {
        this.loudSwitch.setValue(loudSwitch);
        preferenceUtil.setLoudSwitch(loudSwitch);
    }

    public MutableLiveData<Boolean> getEqSwitch() {
        return eqSwitch;
    }

    public void setEqSwitch(boolean eqSwitch) {
        this.eqSwitch.setValue(eqSwitch);
        preferenceUtil.setEqSwitch(eqSwitch);
    }

    public MutableLiveData<Boolean> getIsCustomSelected() {
        return isCustomSelected;
    }

    public void setIsCustomSelected(boolean isCustomSelected) {
        this.isCustomSelected.setValue(isCustomSelected);
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
