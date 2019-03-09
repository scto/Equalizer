package com.jazibkhan.equalizer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;


@Database(entities = {CustomPreset.class}, version = 1)
@TypeConverters({ArrayConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CustomPresetDAO entryDAO();
    private static volatile AppDatabase INSTANCE;

    public static synchronized AppDatabase getDatabase(final Context context) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "custom_preset")
                            .build();
                }
        return INSTANCE;
    }
}
