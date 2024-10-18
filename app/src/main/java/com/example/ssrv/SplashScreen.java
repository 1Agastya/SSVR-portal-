package com.example.ssrv;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar loadingSpinner;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Initialize the ProgressBar
        loadingSpinner = findViewById(R.id.loading_spinner);

        // Start a thread to simulate loading
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            loadingSpinner.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Sleep for 50 milliseconds to simulate some work being done
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Once the progress reaches 100, start the MainActivity
                if (progressStatus >= 100) {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Close the SplashScreen activity
                }
            }
        }).start();
    }
}
