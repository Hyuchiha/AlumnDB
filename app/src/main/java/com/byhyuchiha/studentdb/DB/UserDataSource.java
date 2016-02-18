package com.byhyuchiha.studentdb.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.byhyuchiha.studentdb.DB.Contracts.UserContract;
import com.byhyuchiha.studentdb.DB.Helpers.UserDBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by byhyuchiha on 18/02/16.
 */
public class UserDataSource {
    private SQLiteDatabase database;
    private UserDBHelper alumnoDBHelper;
    private String[] allColumns = {UserContract.COLUMN_NAME_USERNAME,
            UserContract.COLUMN_NAME_PASSWORD};

    public UserDataSource(Context context) {
        alumnoDBHelper = new UserDBHelper(context);
    }

    public void open() throws SQLException {
        database = alumnoDBHelper.getWritableDatabase();
    }

    public void close() {
        alumnoDBHelper.close();
    }

    public long insertUser(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(UserContract.COLUMN_NAME_USERNAME, username);
        values.put(UserContract.COLUMN_NAME_PASSWORD, password);
        long newRowId;
        newRowId = database.insert(UserContract.TABLE_NAME, null, values);
        return newRowId;
    }

    private HashMap<String, String> getAllUsers() {
        HashMap<String, String> users = new HashMap<>();
        Cursor cursor = database.query(UserContract.TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            users.put(cursor.getString(0), cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }

    public boolean hasValidCredentials(String username, String password){
        HashMap<String, String> users = getAllUsers();
        for(String key: users.keySet()){
            if(username.equals(username) && password.equals(users.get(key))){
                return true;
            }
        }
        return false;
    }
}
