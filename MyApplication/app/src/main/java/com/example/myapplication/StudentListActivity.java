package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Etudiant;

public class StudentListActivity extends AppCompatActivity {

    private ListView listView;
    private List<Etudiant> studentList = new ArrayList<>();
    private StudentAdapter adapter;
    private RequestQueue requestQueue;

    private static final String GET_URL = "http://10.0.2.2/projetMobile/ws/GetStudent.php";
    private static final String DELETE_URL = "http://10.0.2.2/projetMobile/ws/deleteStudent.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        listView = findViewById(R.id.listViewStudents);

        adapter = new StudentAdapter(this, studentList);
        listView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);

        loadStudents();


        listView.setOnItemClickListener((parent, view, position, id) -> {
            Etudiant student = studentList.get(position);
            showOptionsDialog(student);
        });
        FloatingActionButton fab = findViewById(R.id.fabAdd);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(StudentListActivity.this, AddStudent.class);
            startActivity(intent);
        });
    }


    private void loadStudents() {
        StringRequest request = new StringRequest(Request.Method.GET, GET_URL,
                response -> {
                    Type type = new TypeToken<List<Etudiant>>(){}.getType();
                    List<Etudiant> students = new Gson().fromJson(response, type);

                    studentList.clear();
                    studentList.addAll(students);
                    adapter.notifyDataSetChanged();
                },
                error -> Toast.makeText(this, "Erreur chargement", Toast.LENGTH_SHORT).show()
        );

        requestQueue.add(request);
    }


    private void showOptionsDialog(Etudiant student) {

        String[] options = {"Modifier", "Supprimer"};

        new AlertDialog.Builder(this)
                .setTitle("Choisir une action")
                .setItems(options, (dialog, which) -> {

                    if (which == 0) {
                        showEditDialog(student);
                    } else {
                        showDeleteConfirmation(student);
                    }

                }).show();
    }


    private void showEditDialog(Etudiant student) {

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_student, null);

        EditText nom = dialogView.findViewById(R.id.editNom);
        EditText prenom = dialogView.findViewById(R.id.editPrenom);

        nom.setText(student.getNom());
        prenom.setText(student.getPrenom());

        new AlertDialog.Builder(this)
                .setTitle("Modifier étudiant")
                .setView(dialogView)
                .setPositiveButton("Enregistrer", (dialog, which) -> {

                    updateStudent(student.getId(),
                            nom.getText().toString(),
                            prenom.getText().toString());

                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    private void showDeleteConfirmation(Etudiant student) {

        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Supprimer " + student.getNom() + " ?")
                .setPositiveButton("Oui", (dialog, which) -> {
                    deleteStudent(student.getId());
                })
                .setNegativeButton("Non", null)
                .show();
    }


    private void deleteStudent(int id) {

        String url = DELETE_URL + "?id=" + id;

        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    Toast.makeText(this, "Supprimé", Toast.LENGTH_SHORT).show();
                    loadStudents();
                },
                error -> Toast.makeText(this, "Erreur suppression", Toast.LENGTH_SHORT).show()
        );

        requestQueue.add(request);
    }


    private void updateStudent(int id, String nom, String prenom) {

        StringRequest request = new StringRequest(Request.Method.POST,
                "http://10.0.2.2/projetMobile/ws/updateStudent.php",
                response -> {
                    Toast.makeText(this, "Modifié", Toast.LENGTH_SHORT).show();
                    loadStudents();
                },
                error -> Toast.makeText(this, "Erreur modification", Toast.LENGTH_SHORT).show()) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                params.put("nom", nom);
                params.put("prenom", prenom);
                return params;
            }
        };

        requestQueue.add(request);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadStudents();
    }
}