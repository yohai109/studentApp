package com.example.studentapp.viewmodels;

import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.ViewModel;

import com.example.studentapp.model.AppDatabase;
import com.example.studentapp.model.Student;
import com.example.studentapp.model.StudentDao;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddStudentViewModel extends ViewModel {
    private final StudentDao dao;
    Executor executor = Executors.newFixedThreadPool(2);
    Handler mainThread = HandlerCompat.createAsync(Looper.getMainLooper());

    public AddStudentViewModel(AppDatabase db) {
        this.dao = db.studentDao();
    }

    public void insert(Student student) {
        executor.execute(() -> {
            dao.insertAll(student);
        });
    }
}
