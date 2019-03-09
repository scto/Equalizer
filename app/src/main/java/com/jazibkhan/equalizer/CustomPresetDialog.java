package com.jazibkhan.equalizer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomPresetDialog extends DialogFragment {
    int position = 0;
    ArrayList<String> eqPreset;
    EqualizerViewModel equalizerViewModel;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        equalizerViewModel = ViewModelProviders.of(getActivity()).get(EqualizerViewModel.class);
        eqPreset = new ArrayList<>();
//        eqPreset.addAll(equalizerViewModel.getPresetName().getValue());
        eqPreset.add("Hello");
        CharSequence[] sequences = eqPreset.toArray(new CharSequence[eqPreset.size()]);
        builder.setTitle("Add Preset").setItems(sequences, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        return builder.create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        equalizerViewModel = ViewModelProviders.of(getActivity()).get(EqualizerViewModel.class);
        equalizerViewModel.getPresetName().observe(getActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                eqPreset.addAll(strings);
            }
        });
    }


}
