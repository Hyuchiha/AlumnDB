package com.byhyuchiha.studentdb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.byhyuchiha.studentdb.DB.AlumnoDataSource;
import com.byhyuchiha.studentdb.Model.Alumno;
import com.byhyuchiha.studentdb.adapter.StudentAdapter;

import java.util.ArrayList;
import java.util.Iterator;

public class StudentDetails extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        lv = (ListView) findViewById(R.id.StudentList);
        createDatabase();
        createListView();


    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        createListView();
    }

    private void createListView(){
        AlumnoDataSource alumnoDS = new AlumnoDataSource(getApplicationContext());
        alumnoDS.open();
        ArrayList<Alumno> alumnos = (ArrayList<Alumno>) alumnoDS.getAllAlumnos();
        StudentAdapter adapter = new StudentAdapter(getApplicationContext(), alumnos);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alumno alumno = (Alumno) lv.getAdapter().getItem(position);

                openNextView(alumno);
            }
        });
    }

    private void openNextView(Alumno alumno){
        Intent intent = new Intent(getApplicationContext(), StudentPersonal.class);
        intent.putExtra("StudentID", alumno);
        startActivity(intent);
    }

    public void addStudent(View view){
        Intent intent = new Intent(getApplicationContext(), StudentPersonal.class);
        startActivity(intent);
    }

    public void logout(View view){
        SharedPreferences.Editor edit = getSharedPreferences("login", Context.MODE_APPEND).edit();
        edit.putBoolean("isLogued", false);
        edit.apply();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void createDatabase(){
        AlumnoDataSource alumnoDS = new AlumnoDataSource(getApplicationContext());
        alumnoDS.open();
        if(alumnoDS.getAllAlumnos().isEmpty()){
            alumnoDS.insertAlumno(new Alumno("08001421", "Kevin", "Pacheco"));
            alumnoDS.insertAlumno(new Alumno("09001421", "Oscar", "Perez"));
        }

        alumnoDS.close();
    }
}