package com.Hostel.Accomomate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostels.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b;
        EditText e1,e2;
        e1=(EditText)findViewById(R.id.editTextText2);
        e2=(EditText) findViewById(R.id.editTextTextPassword);
        b= findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e2.getText().toString().equals("msram@26") && e1.getText().toString().equals("msramkarthik26@ptuniv.edu.in")) {
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    changeButtonColor(android.R.color.holo_green_dark);
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                    changeButtonColor(android.R.color.holo_red_dark);
                }
            }
        });
    }

    private void changeButtonColor(int colorRes) {
        Button b = findViewById(R.id.button);

        b.setTextColor(ContextCompat.getColorStateList(this, colorRes));


        // Use a Handler to delay the color change for 1 second (1000 milliseconds)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Reset the text color after the delay
                b.setTextColor(getResources().getColor(android.R.color.black));
            }
        }, 200);
    }
}