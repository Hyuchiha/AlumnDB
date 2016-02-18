package com.byhyuchiha.studentdb.DB.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.byhyuchiha.studentdb.DB.Contracts.AlumnoContract;

/**
 * Created by juangarcilazoortiz on 2/3/16.
 */
public class AlumnoDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Alumnos.db";

    public AlumnoDBHelper(Context ct){
        super(ct, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(AlumnoContract.SQL_CREATE_ALUMNOS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(AlumnoContract.SQL_DELETE_ALUMNOS);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db,newVersion, oldVersion);
    }

}
