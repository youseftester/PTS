package com.example.pts;

import android.app.Application;

import androidx.room.Room;

public class PTSApp extends Application {
    private static PTSApp instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "scanner-db")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static PTSApp getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}