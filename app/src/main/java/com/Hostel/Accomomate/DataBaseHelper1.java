package com.Hostel.Accomomate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper1 extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DataBaseContract1.FeeEntry.TABLE_NAME + " (" +
                    DataBaseContract1.FeeEntry._ID + " INTEGER PRIMARY KEY," +
                    DataBaseContract1.FeeEntry.COLUMN_NAME_NAME + " TEXT," +
                    DataBaseContract1.FeeEntry.COLUMN_NAME_REGISTER_NUMBER + " TEXT," +
                    DataBaseContract1.FeeEntry.COLUMN_NAME_PHONE_NUMBER + " TEXT," +
                    DataBaseContract1.FeeEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    DataBaseContract1.FeeEntry.COLUMN_NAME_ROOM_NUMBER + " TEXT," +
                    DataBaseContract1.FeeEntry.COLUMN_NAME_YEAR1 + " TEXT," +
                    DataBaseContract1.FeeEntry.COLUMN_NAME_AMOUNT + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataBaseContract1.FeeEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeeReader.db";

    public DataBaseHelper1(Context context) {
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
