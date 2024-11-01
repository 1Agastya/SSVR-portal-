package com.example.ssrv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class moodoptionlogin extends AppCompatActivity {

    Button login_btn,sign_upbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moodoptionlogin);
        login_btn = findViewById(R.id.login_btn);
        sign_upbtn = findViewById(R.id.signup_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Loginintent = new Intent(moodoptionlogin.this,LoginActivity.class);
                startActivity(Loginintent);
            }
        });
        sign_upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupintent = new Intent(moodoptionlogin.this,MainActivity.class);
                startActivity(signupintent);
            }
        });
    }
}