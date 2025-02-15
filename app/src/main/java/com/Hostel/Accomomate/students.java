package com.Hostel.Accomomate;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hostels.R;

import java.util.ArrayList;

public class students extends Fragment {
    private ListView listView;
    private DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students, container, false);
        listView = view.findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(getActivity());
        displayData();
        return view;
    }

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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
    }
}
