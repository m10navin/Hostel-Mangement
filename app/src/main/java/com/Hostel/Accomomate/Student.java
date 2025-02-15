package com.Hostel.Accomomate;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hostels.R;

public class Student extends AppCompatActivity {
    private EditText nameEditText, phoneEditText, fatherNameEditText, fatherPhoneEditText,
            motherNameEditText, motherPhoneEditText,registerNumberEditText;
    public RadioButton selectedYearRadioButton;
    private Spinner spinnerDepartment;
     RadioGroup radioGroupYear;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        dbHelper = new DatabaseHelper(this);

        nameEditText = findViewById(R.id.editTextName);
        phoneEditText = findViewById(R.id.editTextPhone);
        fatherNameEditText = findViewById(R.id.editTextFathersName);
        fatherPhoneEditText = findViewById(R.id.editTextFathersPhone);
        motherNameEditText = findViewById(R.id.editTextMothersName);
        motherPhoneEditText = findViewById(R.id.editTextMothersPhone);
        registerNumberEditText=findViewById(R.id.editTextRegisterNumber);
        radioGroupYear = findViewById(R.id.radioGroupYear);


        spinnerDepartment = findViewById(R.id.spinnerDepartment);
        setupSpinner();
        Button addButton = findViewById(R.id.submitButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });


    }
    public void setupSpinner() {
        // The provided code to set up the Spinner
        Spinner spinnerDepartment = findViewById(R.id.spinnerDepartment);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.departments_array,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDepartment.setAdapter(adapter);
    }
    public void insertData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseContract.StudentEntry.COLUMN_NAME_NAME, nameEditText.getText().toString());
        values.put(DataBaseContract.StudentEntry.COLUMN_NAME_PHONE, phoneEditText.getText().toString());
        values.put(DataBaseContract.StudentEntry.COLUMN_NAME_FATHER_NAME, fatherNameEditText.getText().toString());
        values.put(DataBaseContract.StudentEntry.COLUMN_NAME_FATHER_PHONE, fatherPhoneEditText.getText().toString());
        values.put(DataBaseContract.StudentEntry.COLUMN_NAME_MOTHER_NAME, motherNameEditText.getText().toString());
        values.put(DataBaseContract.StudentEntry.COLUMN_NAME_MOTHER_PHONE, motherPhoneEditText.getText().toString());
        values.put(DataBaseContract.StudentEntry.COLUMN_NAME_REGISTER_NUMBER, registerNumberEditText.getText().toString());
        int selectedYearId = radioGroupYear.getCheckedRadioButtonId();
        selectedYearRadioButton = findViewById(selectedYearId);

        String selectedYear = selectedYearRadioButton.getText().toString();
        values.put(DataBaseContract.StudentEntry.COLUMN_NAME_YEAR, selectedYear);
        String selectedDepartment = spinnerDepartment.getSelectedItem().toString();
        values.put(DataBaseContract.StudentEntry.COLUMN_NAME_DEPARTMENT, selectedDepartment);

        long newRowId = db.insert(DataBaseContract.StudentEntry.TABLE_NAME, null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Failed to insert data", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}
