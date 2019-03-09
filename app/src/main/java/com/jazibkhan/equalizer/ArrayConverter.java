package com.jazibkhan.equalizer;

import android.arch.persistence.room.TypeConverter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ArrayConverter {

    @TypeConverter
    public static int[] fromStringToArray(String value) {

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(value);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int[] slider = new int[5];
        for (int i=0; i<5; i++) {
            try {
                if (jsonArray != null) {
                    slider[i]=( jsonArray.getInt(i) );
                }
                else slider[i]=0;
            } catch (JSONException e) {
                slider[i]=0;
            }
        }

        return slider;
    }

    @TypeConverter
    public static String fromArrayToString(int[] value) {
        String array = null;
        if(value != null){
            JSONArray jsonArray = new JSONArray(Arrays.asList(value));
            array = jsonArray.toString();
        }

        return array;
    }
}