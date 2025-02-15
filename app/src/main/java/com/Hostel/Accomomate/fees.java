package com.Hostel.Accomomate;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.hostels.R;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class fees extends Fragment {
    private ListView listView;
    private DataBaseHelper1 dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fees, container, false);
        listView = view.findViewById(R.id.listView);
        dbHelper = new DataBaseHelper1(requireContext());
        displayData();
        return view;
    }

    private void displayData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DataBaseContract1.FeeEntry.COLUMN_NAME_NAME,
                DataBaseContract1.FeeEntry.COLUMN_NAME_REGISTER_NUMBER,
                DataBaseContract1.FeeEntry.COLUMN_NAME_PHONE_NUMBER,
                DataBaseContract1.FeeEntry.COLUMN_NAME_EMAIL,
                DataBaseContract1.FeeEntry.COLUMN_NAME_ROOM_NUMBER,
                DataBaseContract1.FeeEntry.COLUMN_NAME_YEAR1,
                DataBaseContract1.FeeEntry.COLUMN_NAME_AMOUNT
        };

        Cursor cursor = db.query(
                DataBaseContract1.FeeEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<String> dataList = new ArrayList<>();

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract1.FeeEntry.COLUMN_NAME_NAME));
            String registerNumber = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract1.FeeEntry.COLUMN_NAME_REGISTER_NUMBER));
            String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract1.FeeEntry.COLUMN_NAME_PHONE_NUMBER));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract1.FeeEntry.COLUMN_NAME_EMAIL));
            String roomNumber = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract1.FeeEntry.COLUMN_NAME_ROOM_NUMBER));
            String year = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract1.FeeEntry.COLUMN_NAME_YEAR1));
            String amount = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract1.FeeEntry.COLUMN_NAME_AMOUNT));

            String data = "Name: " + name +
                    "\nRegister Number: " + registerNumber +
                    "\nPhone Number: " + phoneNumber +
                    "\nEmail: " + email +
                    "\nRoom Number: " + roomNumber +
                    "\nYear: " + year +
                    "\nAmount: " + amount;

            dataList.add(data);
        }

        cursor.close();
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
    }
}
