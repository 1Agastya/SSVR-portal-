package com.example.ssrv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
Button btn;
EditText name,username,password,phoneNumber,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.et_email);
        name = findViewById(R.id.et_name);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        phoneNumber = findViewById(R.id.et_phone);


        btn = findViewById(R.id.btn_verify_email);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               adduser();

            }
        });
    }

    private void adduser() {
        String textname = name.getText().toString().trim();
        String textemail = email.getText().toString().trim();
        String textusername = username.getText().toString().trim();
        String textpassword = password.getText().toString().trim();
        String textphone = phoneNumber.getText().toString().trim();

        if(textname.isEmpty()){
            Toast.makeText(this, "Enter the Name", Toast.LENGTH_SHORT).show();
        } else  if(textemail.isEmpty()){
            Toast.makeText(this, "Enter the Email", Toast.LENGTH_SHORT).show();
        } else  if(textusername.isEmpty()){
            Toast.makeText(this, "Enter the Username", Toast.LENGTH_SHORT).show();
        }else  if(textpassword.isEmpty()){
            Toast.makeText(this, "Enter the Password", Toast.LENGTH_SHORT).show();
        } else  if(textphone.isEmpty()){
            Toast.makeText(this, "Enter the Phone Number", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.56.1/myproject/ssvr_php_script/register.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Data Successfully inserted")) {
                        Toast.makeText(MainActivity.this, "Data Successfully Inserted", Toast.LENGTH_SHORT).show();
                       /// if the reponce is successfully than .
                        Intent modlogintent = new Intent(MainActivity.this, moodoptionlogin.class);
                        startActivity(modlogintent);
                    } else {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String , String> param = new HashMap<>();
                    param.put("name",textname);
                    param.put("username",textusername);
                    param.put("email",textemail);
                    param.put("password",textpassword);
                    param.put("number",textphone);
                    return param;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(request);

        }
    }
}