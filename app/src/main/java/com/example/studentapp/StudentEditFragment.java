package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.studentapp.model.Model;
import com.example.studentapp.model.Student;

import java.util.List;

public class StudentEditFragment extends Fragment {

    List<Student> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_student_edit, container, false);

        data = Model.instance.getAllStudents();

        int index = StudentEditFragmentArgs.fromBundle(getArguments()).getStudentIndex();
        Student student = data.get(index);

        EditText name = view.findViewById(R.id.studentedit_name);
        EditText id = view.findViewById(R.id.studentedit_id);
        EditText address = view.findViewById(R.id.studentedit_addres);
        EditText phone = view.findViewById(R.id.studentedit_phone);
        CheckBox cb = view.findViewById(R.id.studentedit_cb);
        Button saveBtn = view.findViewById(R.id.studentedit_save);
        Button cancelBtn = view.findViewById(R.id.studentedit_cancel);
        Button deleteBtn = view.findViewById(R.id.studentedit_delete);

        name.setText(student.getName());
        id.setText(student.getId());
        address.setText(student.getAddress());
        phone.setText(student.getPhone());
        cb.setChecked(student.isFlag());

        cancelBtn.setOnClickListener(v -> Navigation.findNavController(v).navigate(StudentEditFragmentDirections.actionGlobalStudentListRvFragment()));


        saveBtn.setOnClickListener(v -> {
            //update the student
            student.setName(name.getText().toString());
            student.setId(id.getText().toString());
            student.setAddress(address.getText().toString());
            student.setPhone(phone.getText().toString());
            student.setFlag(cb.isChecked());

            data.remove(index);
            data.add(index, student);
            Navigation.findNavController(v).navigate(StudentEditFragmentDirections.actionGlobalStudentListRvFragment());
        });

        deleteBtn.setOnClickListener(v -> {
            data.remove(index);
            Navigation.findNavController(v).navigate(StudentEditFragmentDirections.actionGlobalStudentListRvFragment());
        });
        return view;
    }

}