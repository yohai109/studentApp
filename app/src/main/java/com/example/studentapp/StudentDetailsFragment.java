package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.TextView;

import com.example.studentapp.model.Model;
import com.example.studentapp.model.Student;
import com.example.studentapp.viewmodels.StudentDetailsViewModel;
import com.example.studentapp.viewmodels.StudentListViewModel;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class StudentDetailsFragment extends Fragment {


    private StudentDetailsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_details, container, false);


        TextView name = view.findViewById(R.id.studentdetails_name_tv);
        TextView id = view.findViewById(R.id.studentdetails_id_tv);
        TextView address = view.findViewById(R.id.studentdetails_addres_tv);
        TextView phone = view.findViewById(R.id.studentdetails_phone_tv);
        CheckBox cb = view.findViewById(R.id.studentdetails_cb);

        String studentId = StudentDetailsFragmentArgs.fromBundle(getArguments()).getStudentId();

        viewModel = new ViewModelProvider(
                this,
                ((StudentApplication) getActivity().getApplication()).getFactory()
        ).get(StudentDetailsViewModel.class);

        viewModel.getStudent(studentId).observe(getViewLifecycleOwner(), student -> {
            if (student != null) {
                name.setText(student.getName());
                id.setText(studentId);
                address.setText(student.getAddress());
                phone.setText(student.getPhone());
                cb.setChecked(student.isFlag());
            }
        });

        Button editBtn = view.findViewById(R.id.studentdetails_save_btn);
        editBtn.setOnClickListener(v -> Navigation.findNavController(v)
                .navigate(
                        StudentDetailsFragmentDirections.actionStudentDetailsFragmentToStudentEditFragment(studentId)
                ));

        Button cancelBtn = view.findViewById(R.id.studentdetails_cancelbtn);
        cancelBtn.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());

        return view;
    }


}