package com.byhyuchiha.studentdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.byhyuchiha.studentdb.DB.AlumnoDataSource;
import com.byhyuchiha.studentdb.Model.Alumno;


public class StudentPersonal extends AppCompatActivity {

    private Alumno currentAlumn;
    private boolean isNew = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_personal);

        currentAlumn = (Alumno) this.getIntent().getSerializableExtra("StudentID");

        if(currentAlumn != null) {
            TextView Name = (TextView) findViewById(R.id.FirstNameET);
            Name.setText(currentAlumn.getFirstName());
            TextView LName = (TextView) findViewById(R.id.LastNameET);
            LName.setText(currentAlumn.getLastName());
            TextView Matricula = (TextView) findViewById(R.id.MatriculaET);
            Matricula.setText(currentAlumn.getMatricula());
        }else{
            Button btn = (Button) findViewById(R.id.deleteButton);
            btn.setEnabled(false);
            isNew = true;
        }
    }

    public void saveData(View view){
        TextView Name = (TextView) findViewById(R.id.FirstNameET);
        String firstName = Name.getText().toString();
        TextView LName = (TextView) findViewById(R.id.LastNameET);
        String lastName = LName.getText().toString();
        TextView Matricula = (TextView) findViewById(R.id.MatriculaET);
        String matricula = Matricula.getText().toString();

        Alumno alumn = new Alumno();
        alumn.setFirstName(firstName);
        alumn.setLastName(lastName);
        alumn.setMatricula(matricula);

        if(isNew){
            insert(alumn);
        }else{
            update(alumn);
        }

        finish();
    }

    private void insert(Alumno alumn){
        AlumnoDataSource data = new AlumnoDataSource(getApplicationContext());
        data.open();
        data.insertAlumno(alumn);
        data.close();
    }

    private void update(Alumno alumn){
        AlumnoDataSource data = new AlumnoDataSource(getApplicationContext());
        data.open();
        data.updateAlumno(alumn);
        data.close();
    }

    public void delete(View view){
        AlumnoDataSource data = new AlumnoDataSource(getApplicationContext());
        data.open();
        data.deleteAlumno(currentAlumn);
        data.close();
        finish();
    }
}
