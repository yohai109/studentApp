package com.example.studentapp.viewmodels;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.ViewModel;

import com.example.studentapp.model.AppDatabase;
import com.example.studentapp.model.Student;
import com.example.studentapp.model.StudentDao;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddStudentViewModel extends ViewModel {
    private static final String TAG = "AddStudentActivity";
    private final StudentDao dao;
    Executor executor = Executors.newFixedThreadPool(2);
    Handler mainThread = HandlerCompat.createAsync(Looper.getMainLooper());
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public AddStudentViewModel(AppDatabase db) {
        this.dao = db.studentDao();
    }

    public void insert(Student student) {
        executor.execute(() -> {
            dao.insertAll(student);
            db.collection("studentsList").document(student.getId()).set(student).addOnSuccessListener(unused -> {
                Log.i(TAG, "student inserted");
            }).addOnFailureListener(exception -> {
                Log.e(TAG, "Failure in insertStudent");
                exception.printStackTrace();
            });
        });
    }
}
