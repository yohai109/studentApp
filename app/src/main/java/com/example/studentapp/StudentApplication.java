package com.example.studentapp;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.studentapp.model.AppDatabase;

public class StudentApplication extends Application {
    private AppViewModelFactory factory;

    @Override
    public void onCreate() {
        super.onCreate();
        factory = new AppViewModelFactory(createDB());
    }

    AppViewModelFactory getFactory() {
        return factory;
    }

    AppDatabase createDB() {
        return Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "students"
        ).build();
    }
}
