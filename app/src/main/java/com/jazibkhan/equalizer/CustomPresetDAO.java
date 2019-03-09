package com.jazibkhan.equalizer;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CustomPresetDAO {
    @Query("SELECT * FROM custom_preset ORDER BY preset_name ASC")
    LiveData<List<CustomPreset>> getAllEntry();

    @Query("SELECT preset_name FROM custom_preset ORDER BY preset_name ASC")
    LiveData<List<String>> getPresetName();

    @Insert
    void insertAll(CustomPreset... customPresets);

    @Update
    void update(CustomPreset customPreset);

    @Delete
    void delete(CustomPreset customPreset);

}
