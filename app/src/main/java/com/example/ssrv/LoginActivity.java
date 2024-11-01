package com.example.ssrv;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login;
    TextView forget;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI components
        email = findViewById(R.id.et_login_email);
        password = findViewById(R.id.et_login_password);
        login = findViewById(R.id.btn_login);
        forget = findViewById(R.id.forgettext);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in...");

        // Set login button click listener
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate and initiate login
                if (validateForm()) {
                    loginpath();
                }
            }
        });
    }

    private boolean validateForm() {
        // Check if email is valid and not empty
        String emailInput = email.getText().toString().trim();
        String passwordInput = password.getText().toString().trim();

        if (TextUtils.isEmpty(emailInput)) {
            email.setError("Email is required");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email");
            return false;
        }

        // Check if password is not empty
        if (TextUtils.isEmpty(passwordInput)) {
            password.setError("Password is required");
            return false;
        }

        return true;
    }

    private void loginpath() {
        // Show progress dialog
        progressDialog.show();

        // Execute async task to perform login
        String emailInput = email.getText().toString().trim();
        String passwordInput = password.getText().toString().trim();

        new LoginTask(emailInput, passwordInput).execute();
    }

    // AsyncTask to handle network request (You can replace this with Retrofit/Volley for better performance)
    private class LoginTask extends AsyncTask<Void, Void, String> {
        String email, password;

        public LoginTask(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                // Prepare URL and connection
                URL url = new URL("http://your-server-url/login.php"); // Replace with your server URL
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                // Prepare post data
                HashMap<String, String> postData = new HashMap<>();
                postData.put("email", email);
                postData.put("password", password);
                StringBuilder postDataString = new StringBuilder();
                for (HashMap.Entry<String, String> entry : postData.entrySet()) {
                    postDataString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
                postDataString.deleteCharAt(postDataString.length() - 1);

                // Write post data to the output stream
                OutputStream os = conn.getOutputStream();
                os.write(postDataString.toString().getBytes());
                os.flush();
                os.close();

                // Get response
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Return response
                return response.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Hide progress dialog
            progressDialog.dismiss();

            // Handle login result
            if (result.contains("Login successful")) {
                // Successful login
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                // Redirect to dashboard or next activity (optional)
                // Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                // startActivity(intent);
                // finish();
            } else {
                // Failed login
                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
