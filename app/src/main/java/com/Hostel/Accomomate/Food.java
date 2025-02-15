package com.Hostel.Accomomate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.hostels.R;

public class Food extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView cardViewWarden1 = findViewById(R.id.cardViewWarden1);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton imageButtonCall1 = findViewById(R.id.imageButtonCall1);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView cardViewWarden2 = findViewById(R.id.cardViewWarden2);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton imageButtonCall2 = findViewById(R.id.imageButtonCall2);

        // Set onClickListener for the first call button
        imageButtonCall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace "1234567890" with the actual phone number
                String phoneNumber = "1234567890";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });

        // Set onClickListener for the second call button
        imageButtonCall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace "1234567890" with the actual phone number
                String phoneNumber2 = "0987654321";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber2));
                startActivity(intent);
            }
        });
    }
}
