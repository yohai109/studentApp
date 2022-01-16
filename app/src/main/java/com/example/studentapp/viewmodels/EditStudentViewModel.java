package com.example.studentapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.studentapp.model.AppDatabase;
import com.example.studentapp.model.FirebaseDao;
import com.example.studentapp.model.Student;
import com.example.studentapp.model.StudentDao;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditStudentViewModel extends ViewModel {
    private final StudentDao dao;
    private final FirebaseDao firebaseDao;
    Executor executor = Executors.newFixedThreadPool(2);

    public EditStudentViewModel(AppDatabase db) {
        this.dao = db.studentDao();
        this.firebaseDao = FirebaseDao.INSTANCE;
    }

    public LiveData<Student> getStudent(String id) {
        return dao.getById(id);
    }

    public void delete(Student student) {
        executor.execute(() -> {
            dao.delete(student);
            firebaseDao.delete(student.getId());
        });
    }

    public void update(String oldId,
                       String newId,
                       String name,
                       String address,
                       String phone,
                       boolean flag) {
        executor.execute(() -> {
            dao.update(
                    oldId,
                    newId,
                    name,
                    address,
                    phone,
                    flag
            );

            if (oldId.equals(newId)) {
                firebaseDao.update(new Student(
                        name,
                        oldId,
                        address,
                        phone,
                        flag
                ));
            } else {
                firebaseDao.delete(oldId);
                firebaseDao.insert(new Student(
                        name,
                        newId,
                        address,
                        phone,
                        flag
                ));
            }
        });
    }
}
