package com.Hostel.Accomomate;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hostels.R;

import java.util.ArrayList;

public class StudentListViewActivity extends AppCompatActivity {
    private ListView listView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        dbHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);

        displayData();
    }
//    displaying data

    private void displayData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DataBaseContract.StudentEntry.COLUMN_NAME_NAME,
                DataBaseContract.StudentEntry.COLUMN_NAME_PHONE,
                DataBaseContract.StudentEntry.COLUMN_NAME_FATHER_NAME,
                DataBaseContract.StudentEntry.COLUMN_NAME_FATHER_PHONE,
                DataBaseContract.StudentEntry.COLUMN_NAME_MOTHER_NAME,
                DataBaseContract.StudentEntry.COLUMN_NAME_MOTHER_PHONE,
                DataBaseContract.StudentEntry.COLUMN_NAME_REGISTER_NUMBER,
                DataBaseContract.StudentEntry.COLUMN_NAME_YEAR,
                DataBaseContract.StudentEntry.COLUMN_NAME_DEPARTMENT
        };

        Cursor cursor = db.query(
                DataBaseContract.StudentEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<String> dataList = new ArrayList<>();

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.StudentEntry.COLUMN_NAME_NAME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.StudentEntry.COLUMN_NAME_PHONE));
            String fatherName = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.StudentEntry.COLUMN_NAME_FATHER_NAME));
            String fatherPhone = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.StudentEntry.COLUMN_NAME_FATHER_PHONE));
            String motherName = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.StudentEntry.COLUMN_NAME_MOTHER_NAME));
            String motherPhone = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.StudentEntry.COLUMN_NAME_MOTHER_PHONE));
            String registerNumber = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.StudentEntry.COLUMN_NAME_REGISTER_NUMBER));
            String year = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.StudentEntry.COLUMN_NAME_YEAR));
            String department = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.StudentEntry.COLUMN_NAME_DEPARTMENT));
            String data = "Name: " + name + "\nPhone: " + phone
                    + "\nFather: " + fatherName + " (" + fatherPhone + ")"
                    + "\nMother: " + motherName + " (" + motherPhone + ")"
                    + "\nRegister Number: " + registerNumber
                    + "\nYear: " + year
                    + "\nDepartment: " + department;

            dataList.add(data);
        }

        cursor.close();
        db.close();


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
    }
}
