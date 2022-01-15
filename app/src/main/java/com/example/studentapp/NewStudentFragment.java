package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.studentapp.model.Model;
import com.example.studentapp.model.Student;

public class NewStudentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_student, container, false);

        EditText name = view.findViewById(R.id.newstudent_name_et);
        EditText id = view.findViewById(R.id.newstudent_id_et);
        EditText address = view.findViewById(R.id.newstudent_address_et);
        EditText phone = view.findViewById(R.id.newstudent_phone_et);
        CheckBox cb = view.findViewById(R.id.newstudent_cb);

        Button saveBtn = view.findViewById(R.id.newstudent_save_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student(name.getText().toString(), id.getText().toString(), address.getText().toString(),
                        phone.getText().toString(), cb.isChecked());
                Model.instance.addStudent(student);
                Navigation.findNavController(v).navigateUp();
            }
        });

        Button cancelBtn = view.findViewById(R.id.newstudent_cancel_btn);
        cancelBtn.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());

        return view;
    }



}