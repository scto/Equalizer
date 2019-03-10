package com.jazibkhan.equalizer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "custom_preset")
public class CustomPreset {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "preset_name")
    private String presetName;
    @ColumnInfo(name = "vir_slider")
    private int virSlider;
    @ColumnInfo(name = "bb_slider")
    private int bbSlider;
    @ColumnInfo(name = "loud_slider")
    private float loudSlider;
    @ColumnInfo(name = "slider")
    private int[] slider;
    @ColumnInfo(name = "spinner_pos")
    private int spinnerPos;
    @ColumnInfo(name = "vir_switch")
    private Boolean virSwitch;
    @ColumnInfo(name = "bb_switch")
    private Boolean bbSwitch;
    @ColumnInfo(name = "loud_switch")
    private Boolean loudSwitch;
    @ColumnInfo(name = "eq_switch")
    private Boolean eqSwitch;
    @ColumnInfo(name = "is_custom_selected")
    private Boolean isCustomSelected;

    public CustomPreset(String presetName, int virSlider, int bbSlider, float loudSlider, int[] slider, int spinnerPos, Boolean virSwitch, Boolean bbSwitch, Boolean loudSwitch, Boolean eqSwitch, Boolean isCustomSelected) {
        this.presetName = presetName;
        this.virSlider = virSlider;
        this.bbSlider = bbSlider;
        this.loudSlider = loudSlider;
        this.slider = slider;
        this.spinnerPos = spinnerPos;
        this.virSwitch = virSwitch;
        this.bbSwitch = bbSwitch;
        this.loudSwitch = loudSwitch;
        this.eqSwitch = eqSwitch;
        this.isCustomSelected = isCustomSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

    public int getVirSlider() {
        return virSlider;
    }

    public void setVirSlider(int virSlider) {
        this.virSlider = virSlider;
    }

    public int getBbSlider() {
        return bbSlider;
    }

    public void setBbSlider(int bbSlider) {
        this.bbSlider = bbSlider;
    }

    public float getLoudSlider() {
        return loudSlider;
    }

    public void setLoudSlider(float loudSlider) {
        this.loudSlider = loudSlider;
    }

    public int[] getSlider() {
        return slider;
    }

    public void setSlider(int[] slider) {
        this.slider = slider;
    }

    public int getSpinnerPos() {
        return spinnerPos;
    }

    public void setSpinnerPos(int spinnerPos) {
        this.spinnerPos = spinnerPos;
    }

    public Boolean getVirSwitch() {
        return virSwitch;
    }

    public void setVirSwitch(Boolean virSwitch) {
        this.virSwitch = virSwitch;
    }

    public Boolean getBbSwitch() {
        return bbSwitch;
    }

    public void setBbSwitch(Boolean bbSwitch) {
        this.bbSwitch = bbSwitch;
    }

    public Boolean getLoudSwitch() {
        return loudSwitch;
    }

    public void setLoudSwitch(Boolean loudSwitch) {
        this.loudSwitch = loudSwitch;
    }

    public Boolean getEqSwitch() {
        return eqSwitch;
    }

    public void setEqSwitch(Boolean eqSwitch) {
        this.eqSwitch = eqSwitch;
    }

    public Boolean getCustomSelected() {
        return isCustomSelected;
    }

    public void setCustomSelected(Boolean customSelected) {
        isCustomSelected = customSelected;
    }
}
