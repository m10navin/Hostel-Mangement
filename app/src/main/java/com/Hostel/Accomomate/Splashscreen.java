package com.Hostel.Accomomate;
import android.animation.ObjectAnimator;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hostels.R;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        // Get the ImageView and TextView
        ImageView hostelImageView = findViewById(R.id.hostel);
        TextView splashTextView = findViewById(R.id.splashTextView);

        // Create an ObjectAnimator to animate translationY property
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(
                hostelImageView, "translationY", 0f, -200f);
        translationAnimator.setDuration(1000); // Set the duration in milliseconds

        // Create an ObjectAnimator to animate scaleX and scaleY properties
        ObjectAnimator scaleAnimatorX = ObjectAnimator.ofFloat(
                hostelImageView, "scaleX", 1f, 0.5f);
        ObjectAnimator scaleAnimatorY = ObjectAnimator.ofFloat(
                hostelImageView, "scaleY", 1f, 0.5f);
        scaleAnimatorX.setDuration(1000); // Set the duration in milliseconds
        scaleAnimatorY.setDuration(1000); // Set the duration in milliseconds

        // Start both animators
        translationAnimator.start();
        scaleAnimatorX.start();
        scaleAnimatorY.start();

        // After animation ends, show the TextView
        translationAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                splashTextView.setVisibility(View.VISIBLE);
                startActivity(new Intent(Splashscreen.this, MainActivity.class));
                finish(); // Finish the current activity to prevent returning back to it on back press
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
    }
}