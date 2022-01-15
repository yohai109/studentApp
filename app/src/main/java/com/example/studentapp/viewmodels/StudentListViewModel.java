package com.example.studentapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.RoomDatabase;

import com.example.studentapp.model.AppDatabase;
import com.example.studentapp.model.Student;

import java.util.List;

public class StudentListViewModel extends ViewModel {
    private AppDatabase db;

    public StudentListViewModel(AppDatabase db) {
        this.db = db;
    }

    public LiveData<List<Student>> getAll() {
        return db.studentDao().getAll();
    }
}
