package com.example.studentapp.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.studentapp.model.AppDatabase;
import com.example.studentapp.model.FirebaseDao;
import com.example.studentapp.model.Student;
import com.example.studentapp.model.StudentDao;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddStudentViewModel extends ViewModel {
    private final StudentDao dao;
    private final FirebaseDao firebaseDao;
    Executor executor = Executors.newFixedThreadPool(2);

    public AddStudentViewModel(AppDatabase db) {
        this.dao = db.studentDao();
        firebaseDao = FirebaseDao.INSTANCE;
    }

    public void insert(Student student) {
        executor.execute(() -> {
            dao.insertAll(student);
            firebaseDao.insert(student);
        });
    }
}
