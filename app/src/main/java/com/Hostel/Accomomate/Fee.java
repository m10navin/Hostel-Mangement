package com.Hostel.Accomomate;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.hostels.R;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.IOException;
import android.os.Environment;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Fee extends AppCompatActivity {
    private static final String CHANNEL_ID = "payment_channel";
    private EditText nameEditText, registerEditText, phoneEditText, emailEditText, roomEditText, amountEditText;
    private RadioGroup radioGroupYear1;
    private Button gpayButton, downloadButton;
    private RadioButton rb1;
    private DataBaseHelper1 dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee);

        dbHelper = new DataBaseHelper1(this);

        nameEditText = findViewById(R.id.nameEditText);
        registerEditText = findViewById(R.id.registerEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        roomEditText = findViewById(R.id.roomEditText);
        amountEditText = findViewById(R.id.amountEditText);
        radioGroupYear1 = findViewById(R.id.radioGroupYear1);
        gpayButton = findViewById(R.id.gpayButton);
        downloadButton = findViewById(R.id.downloadButton);
        gpayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkAvailable()) {
                    showNoInternetConnectionDialog();
                }
                else{
                insertDataIntoDatabase();
                openGPay();}
            }
        });
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePdf();
            }
        });
    }

    private void insertDataIntoDatabase() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseContract1.FeeEntry.COLUMN_NAME_NAME, nameEditText.getText().toString());
        values.put(DataBaseContract1.FeeEntry.COLUMN_NAME_REGISTER_NUMBER, registerEditText.getText().toString());
        values.put(DataBaseContract1.FeeEntry.COLUMN_NAME_PHONE_NUMBER, phoneEditText.getText().toString());
        values.put(DataBaseContract1.FeeEntry.COLUMN_NAME_EMAIL, emailEditText.getText().toString());
        values.put(DataBaseContract1.FeeEntry.COLUMN_NAME_ROOM_NUMBER, roomEditText.getText().toString());
        int selectedYearId = radioGroupYear1.getCheckedRadioButtonId();
        rb1 = findViewById(selectedYearId);

        String selectedYear = rb1.getText().toString();
        values.put(DataBaseContract1.FeeEntry.COLUMN_NAME_YEAR1, selectedYear);
        values.put(DataBaseContract1.FeeEntry.COLUMN_NAME_AMOUNT, amountEditText.getText().toString());

        db.insert(DataBaseContract1.FeeEntry.TABLE_NAME, null, values);


    }

    private void openGPay() {
        String amount = amountEditText.getText().toString();
        String upiId = "msramkarthik26@oksbi"; // Replace with your UPI ID

        // Construct the UPI link
        String uri = "upi://pay?pa=" + upiId + "&pn=Recipient&mc=123&tid=123&tr=123456789&tn=Payment&am=" + amount + "&cu=INR";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));

        // Verify that the intent will resolve to an activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "GPay not installed", Toast.LENGTH_SHORT).show();
        }
    }

    private void showPaymentNotification() {
        createNotificationChannel();

        // Check if the permission is granted
        if (checkSelfPermission(Manifest.permission.USE_FULL_SCREEN_INTENT) == PackageManager.PERMISSION_GRANTED) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_payment)  // Replace with the correct drawable resource
                    .setContentTitle("Payment Successful")
                    .setContentText("Your payment has been successfully processed.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            int notificationId = 1;  // Use a unique identifier for each notification
            notificationManager.notify(notificationId, builder.build());
        } else {
            Log.e("Notification", "Permission not granted");
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "PaymentChannel";
            String description = "Channel for payment notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, generate the PDF
            createPdf();
        } else {
            Toast.makeText(this, "Permission denied. Cannot generate PDF.", Toast.LENGTH_SHORT).show();
        }
    }


    private void generatePdf() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Request the WRITE_EXTERNAL_STORAGE permission
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            createPdf();
        }
    }

    private void createPdf() {
        String baseFileName = "PaymentDetails";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName = baseFileName + "_" + timeStamp + ".pdf";

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf");
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

        Uri uri = getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);

        try {
            if (uri != null) {
                try (OutputStream outputStream = getContentResolver().openOutputStream(uri);
                     PdfWriter writer = new PdfWriter(outputStream);
                     PdfDocument pdfDocument = new PdfDocument(writer);
                     Document document = new Document(pdfDocument)) {

                    // Add content to the PDF document
                    document.add(new Paragraph("Payment Details"));
                    document.add(new Paragraph("Name: " + nameEditText.getText().toString()));
                    document.add(new Paragraph("Register Number: " + registerEditText.getText().toString()));
                    document.add(new Paragraph("Phone Number: " + phoneEditText.getText().toString()));
                    document.add(new Paragraph("Email: " + emailEditText.getText().toString()));
                    document.add(new Paragraph("Room Number: " + roomEditText.getText().toString()));
                    int selectedYearId = radioGroupYear1.getCheckedRadioButtonId();
                    rb1 = findViewById(selectedYearId);
                    document.add(new Paragraph("Year: " + rb1.getText().toString()));
                    document.add(new Paragraph("Amount: " + amountEditText.getText().toString()));
                }

                Toast.makeText(this, "PDF generated successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error creating file", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error generating PDF", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
    private void showNoInternetConnectionDialog() {
        // Create a custom view for the dialog
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.broadcast, null);

        // Set the message text
        TextView textViewMessage = dialogView.findViewById(R.id.textViewMessage);
        if (textViewMessage != null) {
            textViewMessage.setText("No Internet Connection");
        }

        // Create AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();
        dialog.show();
    }
}