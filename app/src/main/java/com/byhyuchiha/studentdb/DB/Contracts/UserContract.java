package com.byhyuchiha.studentdb.DB.Contracts;

import android.provider.BaseColumns;

/**
 * Created by byhyuchiha on 18/02/16.
 */
public class UserContract implements BaseColumns{
    public static final String TABLE_NAME = "Users";
    public static final String COLUMN_NAME_USERNAME = "Username";
    public static final String COLUMN_NAME_PASSWORD = "Password";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_USERS =
            "CREATE TABLE " + TABLE_NAME + " (" +_ID + " INTEGER PRIMARY KEY,"+
                    COLUMN_NAME_USERNAME+TEXT_TYPE+COMMA_SEP+
                    COLUMN_NAME_PASSWORD+
                    " )";

    public static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}
