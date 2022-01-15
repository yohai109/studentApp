package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.studentapp.viewmodels.EditStudentViewModel;
import com.example.studentapp.viewmodels.StudentDetailsViewModel;

import java.util.List;

public class StudentEditFragment extends Fragment {

    private EditStudentViewModel viewModel;
    private EditText name;
    private EditText id;
    private EditText address;
    private EditText phone;
    private CheckBox cb;

    private Student editedStudent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_student_edit, container, false);


        viewModel = new ViewModelProvider(
                this,
                ((StudentApplication) getActivity().getApplication()).getFactory()
        ).get(EditStudentViewModel.class);

        name = view.findViewById(R.id.studentedit_name);
        id = view.findViewById(R.id.studentedit_id);
        address = view.findViewById(R.id.studentedit_addres);
        phone = view.findViewById(R.id.studentedit_phone);
        cb = view.findViewById(R.id.studentedit_cb);

        @NonNull String index = StudentEditFragmentArgs.fromBundle(getArguments()).getStudentIndex();
        viewModel.getStudent(index).observe(getViewLifecycleOwner(), student -> {
            name.setText(student.getName());
            id.setText(student.getId());
            address.setText(student.getAddress());
            phone.setText(student.getPhone());
            cb.setChecked(student.isFlag());

            editedStudent = student;
        });


        Button saveBtn = view.findViewById(R.id.studentedit_save);
        Button cancelBtn = view.findViewById(R.id.studentedit_cancel);
        Button deleteBtn = view.findViewById(R.id.studentedit_delete);

        cancelBtn.setOnClickListener(v -> Navigation.findNavController(v).navigate(StudentEditFragmentDirections.actionGlobalStudentListRvFragment()));

        saveBtn.setOnClickListener(v -> {
            String oldId = editedStudent.getId();
            //update the student
            editedStudent.setName(name.getText().toString());
            editedStudent.setId(id.getText().toString());
            editedStudent.setAddress(address.getText().toString());
            editedStudent.setPhone(phone.getText().toString());
            editedStudent.setFlag(cb.isChecked());

            viewModel.update(
                    oldId,
                    editedStudent.getId(),
                    editedStudent.getName(),
                    editedStudent.getAddress(),
                    editedStudent.getPhone(),
                    editedStudent.isFlag()
            );
            Navigation.findNavController(v).navigate(StudentEditFragmentDirections.actionGlobalStudentListRvFragment());
        });

        deleteBtn.setOnClickListener(v -> {
            viewModel.delete(editedStudent);
            Navigation.findNavController(v).navigate(StudentEditFragmentDirections.actionGlobalStudentListRvFragment());
        });

        return view;
    }

}