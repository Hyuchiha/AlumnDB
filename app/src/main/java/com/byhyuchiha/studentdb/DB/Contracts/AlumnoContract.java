package com.byhyuchiha.studentdb.DB.Contracts;

import android.provider.BaseColumns;

/**
 * Created by juangarcilazoortiz on 2/3/16.
 */
public class AlumnoContract implements BaseColumns{
    public static final String TABLE_NAME = "Alumnos";
    public static final String COLUMN_NAME_MATRICULA = "Matricula";
    public static final String COLUMN_NAME_NOMBRE = "Nombre";
    public static final String COLUMN_NAME_APELLIDO = "Apellido";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ALUMNOS =
            "CREATE TABLE " + TABLE_NAME + " (" +_ID + " INTEGER PRIMARY KEY,"+
                    COLUMN_NAME_MATRICULA+TEXT_TYPE+COMMA_SEP+
                    COLUMN_NAME_NOMBRE+TEXT_TYPE+COMMA_SEP+
                    COLUMN_NAME_APELLIDO+TEXT_TYPE +
                    " )";

    public static final String SQL_DELETE_ALUMNOS =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}
