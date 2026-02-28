package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import beans.Etudiant;

public class StudentAdapter extends ArrayAdapter<Etudiant> {

    public StudentAdapter(Context context, List<Etudiant> students) {
        super(context, 0, students); // 0 car on inflate manuellement
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_student, parent, false);
        }

        Etudiant student = getItem(position);

        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtEmail = convertView.findViewById(R.id.txtEmail);

        txtName.setText(student.getNom() + " " + student.getPrenom());
        txtEmail.setText(student.getVille() + " | " + student.getSexe());

        return convertView;
    }
}