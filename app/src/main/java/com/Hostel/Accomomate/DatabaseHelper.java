package com.Hostel.Accomomate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "students";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DataBaseContract.StudentEntry.TABLE_NAME + " (" +
                    DataBaseContract.StudentEntry._ID + " INTEGER PRIMARY KEY," +
                    DataBaseContract.StudentEntry.COLUMN_NAME_NAME + " TEXT," +
                    DataBaseContract.StudentEntry.COLUMN_NAME_PHONE + " TEXT," +
                    DataBaseContract.StudentEntry.COLUMN_NAME_FATHER_NAME + " TEXT," +
                    DataBaseContract.StudentEntry.COLUMN_NAME_FATHER_PHONE + " TEXT," +
                    DataBaseContract.StudentEntry.COLUMN_NAME_MOTHER_NAME + " TEXT," +
                    DataBaseContract.StudentEntry.COLUMN_NAME_MOTHER_PHONE + " TEXT," +
                    DataBaseContract.StudentEntry.COLUMN_NAME_REGISTER_NUMBER + " TEXT," +
                    DataBaseContract.StudentEntry.COLUMN_NAME_YEAR + " TEXT," +
                    DataBaseContract.StudentEntry.COLUMN_NAME_DEPARTMENT + " TEXT)";



    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataBaseContract.StudentEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
