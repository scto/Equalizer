package com.jazibkhan.equalizer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.ColumnInfo;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.jazibkhan.equalizer.EqualizerViewModel;

import java.util.ArrayList;
import java.util.List;

public class CustomPresetSaveDialog extends DialogFragment {
    EqualizerViewModel equalizerViewModel;
    EditText presetNameEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_save_preset, null);
        presetNameEditText = view.findViewById(R.id.preset_name);
        presetNameEditText.requestFocus();
        builder.setView(view);
        builder.setTitle("Save As Preset").setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(presetNameEditText.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(),"Please enter the name of the preset",Toast.LENGTH_SHORT).show();
                }
                else
                savePreset();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        Dialog d = builder.create();
        d.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return d;
    }

    void savePreset() {
        equalizerViewModel = ViewModelProviders.of(getActivity()).get(EqualizerViewModel.class);
        String presetName = presetNameEditText.getText().toString();
        int virSlider = equalizerViewModel.getVirSlider();
        int bbSlider = equalizerViewModel.getBBSlider();
        float loudSlider = equalizerViewModel.getLoudSlider();
        int[] slider = new int[5];
        for (int i = 0; i < 5; i++) {
            slider[i] = equalizerViewModel.getSlider(i);
        }
        int spinnerPos = equalizerViewModel.getSpinnerPos();
        Boolean virSwitch = equalizerViewModel.getVirSwitch();
        Boolean bbSwitch = equalizerViewModel.getbBSwitch();
        Boolean loudSwitch = equalizerViewModel.getLoudSwitch();
        Boolean eqSwitch = equalizerViewModel.getEqSwitch();
        Boolean isCustomSelected = equalizerViewModel.getIsCustomSelected();
        CustomPreset customPreset = new CustomPreset(
                presetName, virSlider, bbSlider, loudSlider, slider, spinnerPos,
                virSwitch, bbSwitch, loudSwitch, eqSwitch, isCustomSelected);
        equalizerViewModel.insert(customPreset);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        equalizerViewModel = ViewModelProviders.of(getActivity()).get(EqualizerViewModel.class);
//
//    }
}
