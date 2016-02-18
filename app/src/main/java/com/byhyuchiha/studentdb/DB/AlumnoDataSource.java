package com.byhyuchiha.studentdb.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.byhyuchiha.studentdb.DB.Contracts.AlumnoContract;
import com.byhyuchiha.studentdb.DB.Helpers.AlumnoDBHelper;
import com.byhyuchiha.studentdb.Model.Alumno;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by juangarcilazoortiz on 2/3/16.
 */
public class AlumnoDataSource {
    private SQLiteDatabase database;
    private AlumnoDBHelper alumnoDBHelper;
    private String[] allColumns = {AlumnoContract.COLUMN_NAME_MATRICULA,
            AlumnoContract.COLUMN_NAME_NOMBRE,
            AlumnoContract.COLUMN_NAME_APELLIDO};

    public AlumnoDataSource(Context context) {
        alumnoDBHelper = new AlumnoDBHelper(context);
    }

    public void open() throws SQLException {
        database = alumnoDBHelper.getWritableDatabase();
    }

    public void close() {
        alumnoDBHelper.close();
    }


    /**
     * Function oriented to insert a new Alumno into DataBase
     *
     * @param stdToInsert the student to be inserted in the db
     * @return row ID of the newly Alumno inserted row, or -1
     */
    public long insertAlumno(Alumno stdToInsert) {
        ContentValues values = new ContentValues();
        values.put(AlumnoContract.COLUMN_NAME_MATRICULA, stdToInsert.getMatricula());
        values.put(AlumnoContract.COLUMN_NAME_NOMBRE, stdToInsert.getFirstName());
        values.put(AlumnoContract.COLUMN_NAME_APELLIDO, stdToInsert.getLastName());
        long newRowId;
        newRowId = database.insert(AlumnoContract.TABLE_NAME, null, values);
        return newRowId;
    }

    public long updateAlumno(Alumno stdToInsert) {
        ContentValues values = new ContentValues();
        values.put(AlumnoContract.COLUMN_NAME_MATRICULA, stdToInsert.getMatricula());
        values.put(AlumnoContract.COLUMN_NAME_NOMBRE, stdToInsert.getFirstName());
        values.put(AlumnoContract.COLUMN_NAME_APELLIDO, stdToInsert.getLastName());
        long newRowId;
        newRowId = database.update(AlumnoContract.TABLE_NAME, values,
                AlumnoContract.COLUMN_NAME_MATRICULA + "=?",
                new String[]{stdToInsert.getMatricula()});

        return newRowId;
    }

    public long deleteAlumno(Alumno stdToDelete) {
        String whereClause = AlumnoContract.COLUMN_NAME_MATRICULA + "=?";
        String[] whereArgs = new String[]{String.valueOf(stdToDelete.getMatricula())};
        return database.delete(AlumnoContract.TABLE_NAME, whereClause, whereArgs);
    }

    /**
     * Function oriented to recovery all Alumno's rows from Database
     *
     * @return List of Alumnos from Database
     */
    public List<Alumno> getAllAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        Alumno alumno;
        Cursor cursor = database.query(AlumnoContract.TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            alumno = cursorToAlumno(cursor);
            alumnos.add(alumno);
            cursor.moveToNext();
        }
        cursor.close();
        return alumnos;
    }

    private Alumno cursorToAlumno(Cursor cursor) {
        return new Alumno(cursor.getString(0), cursor.getString(1), cursor.getString(2));
    }

}
