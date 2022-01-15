package com.example.studentapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.RoomDatabase;

import com.example.studentapp.model.AppDatabase;
import com.example.studentapp.model.Student;
import com.example.studentapp.model.StudentDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class StudentListViewModel extends ViewModel {
    private StudentDao dao;
    Executor executor = Executors.newFixedThreadPool(2);

    public StudentListViewModel(AppDatabase db) {
        this.dao = db.studentDao();
    }

    public LiveData<List<Student>> getAll() {
        return dao.getAll();
    }

    public void updateFlag(Student student, Boolean isFlag) {
        executor.execute(() -> {
            student.setFlag(isFlag);
            dao.update(student);
        });
    }
}
