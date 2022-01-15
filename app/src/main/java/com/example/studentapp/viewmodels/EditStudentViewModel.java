package com.example.studentapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.studentapp.model.AppDatabase;
import com.example.studentapp.model.Student;
import com.example.studentapp.model.StudentDao;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditStudentViewModel extends ViewModel {
    private final StudentDao dao;
    Executor executor = Executors.newFixedThreadPool(2);

    public EditStudentViewModel(AppDatabase db) {
        this.dao = db.studentDao();
    }

    public LiveData<Student> getStudent(String id) {
        return dao.getById(id);
    }

    public void delete(Student student) {
        executor.execute(() -> dao.delete(student));
    }

    public void update(String oldId,
                       String newId,
                       String name,
                       String address,
                       String phone,
                       boolean flag) {
        executor.execute(() -> dao.update(
                oldId,
                newId,
                name,
                address,
                phone,
                flag
        ));
    }
}
