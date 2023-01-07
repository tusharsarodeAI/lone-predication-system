package com.example.loaneligibilityprediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.loaneligibilityprediction.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class loginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ProgressBar loginProgress;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        usernameEditText = findViewById(R.id.activity_main_usernameEditText);
        passwordEditText = findViewById(R.id.activity_main_passwordEditText);
        loginButton = findViewById(R.id.activity_main_loginButton);
        loginProgress = findViewById(R.id.loginProgressBar);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameEditText.getText().length() > 0 && passwordEditText.getText().length() > 0) {
                    String email = usernameEditText.getText().toString();
                    String password = passwordEditText.getText().toString();
                    loginButton.setEnabled(true);
                    loginProgress.setVisibility(View.VISIBLE);
                    signInWithEmail(email, password);
//                    String toastMessage = "Username: " + usernameEditText.getText().toString() + ", Password: " + passwordEditText.getText().toString();
//                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();

                } else {
                    String toastMessage = "Username or Password are not populated";
                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                    loginButton.setEnabled(false);
                    loginProgress.setVisibility(View.GONE);
                }

            }
        });
    }

    public void signInWithEmail(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String TAG = "Tushar";
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String id = user.getUid();
                            User userData = new User(
                                    id,
                                    email,
                                    password
                            );
                            db.collection("user").add(userData);
                            updateUI(true);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(loginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            loginButton.setEnabled(false);
                            loginProgress.setVisibility(View.GONE);
                            updateUI(false);
                        }
                    }
                });

    }

    private void updateUI(Boolean o) {
        if (o) {
            Toast.makeText(getApplicationContext(), "Successful Login!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(loginActivity.this,ActivityDucuments.class);
            startActivity(intent);
        } else {
            String toastMessage = "Username or Password are not populated";
            Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
            loginButton.setEnabled(false);
            loginProgress.setVisibility(View.GONE);

        }
    }
}

