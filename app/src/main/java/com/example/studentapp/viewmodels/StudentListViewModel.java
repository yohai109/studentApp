package com.example.studentapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.studentapp.model.AppDatabase;
import com.example.studentapp.model.FirebaseDao;
import com.example.studentapp.model.Student;
import com.example.studentapp.model.StudentDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class StudentListViewModel extends ViewModel {
    private final StudentDao dao;
    private final FirebaseDao firebaseDao;
    Executor executor = Executors.newFixedThreadPool(2);

    public StudentListViewModel(AppDatabase db) {
        this.dao = db.studentDao();
        firebaseDao = FirebaseDao.INSTANCE;
    }

    public LiveData<List<Student>> getAll() {
        return dao.getAll();
    }

    public void refrash() {
        firebaseDao.refresh(result -> executor.execute(() -> dao.insertAll(result.toArray(new Student[0]))));
    }

    public void updateFlag(Student student, Boolean isFlag) {
        executor.execute(() -> {
            student.setFlag(isFlag);
            dao.update(student);
            firebaseDao.update(student);
        });
    }

    public interface OnRefreshFinished{
        void onRefreshFinished(List<Student> result);
    }
}
