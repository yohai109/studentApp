package com.example.studentapp.model;

import android.util.Log;

import com.example.studentapp.viewmodels.StudentListViewModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.ArrayList;

public class FirebaseDao {

    private static final String COLLECTION_NAME = "studentsList";
    public static final FirebaseDao INSTANCE = new FirebaseDao();

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseDao() {
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(false)
                .build();
        db.setFirestoreSettings(settings);
    }

    public void refresh(StudentListViewModel.OnRefreshFinished callback) {
        db.collection(COLLECTION_NAME)
                .get()
                .addOnSuccessListener(command -> {
                    ArrayList<Student> students = new ArrayList<>();
                    for (DocumentSnapshot doc : command.getDocuments()) {
                        students.add(doc.toObject(Student.class));
                    }
                    callback.onRefreshFinished(students);
                });
    }

    public void insert(Student student) {
        db.collection(COLLECTION_NAME)
                .document(student.getId())
                .set(student)
                .addOnFailureListener(this::failureListener);
    }

    public void update(Student student) {
        db.collection(COLLECTION_NAME)
                .document(student.getId())
                .set(student)
                .addOnFailureListener(this::failureListener);
    }

    public void delete(String id) {
        db.collection(COLLECTION_NAME)
                .document(id)
                .delete()
                .addOnFailureListener(this::failureListener);
    }

    private void failureListener(Exception e) {
        Log.e("TAG", "Failure in insertStudent");
        e.printStackTrace();
    }
}
