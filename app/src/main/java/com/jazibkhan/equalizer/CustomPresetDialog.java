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

import static android.support.constraint.Constraints.TAG;

public class CustomPresetDialog extends DialogFragment {
    int position = 0;
    ArrayList<String> eqPreset;
    EqualizerViewModel equalizerViewModel;
    ArrayAdapter<String> adapter;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        equalizerViewModel = ViewModelProviders.of(getActivity()).get(EqualizerViewModel.class);
        eqPreset = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, eqPreset );
       // eqPreset.addAll(equalizerViewModel.getPresetName().getValue());

        builder.setTitle("Load Preset").setAdapter(adapter, new DialogInterface.OnClickListener() {
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
               // eqPreset.addAll(strings);
                adapter.addAll(strings);
                adapter.notifyDataSetChanged();
            }
        });
    }


}
