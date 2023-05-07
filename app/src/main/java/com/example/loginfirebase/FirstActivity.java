package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirstActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText signInEmail,signInPwd;
    private Button signingButton;
    private TextView loginRedirectionTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        auth =FirebaseAuth.getInstance();
        signInEmail = findViewById(R.id.signUp_email);
        signInPwd = findViewById(R.id.signUp_pwd);
        signingButton = findViewById(R.id.btn_signup);
        loginRedirectionTxt = findViewById(R.id.loginRedirectionText);

        signingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = signInEmail.getText().toString().trim();
                String pwd = signInPwd.getText().toString().trim();

                if(user.isEmpty()){
                    signInEmail.setError("Email cannot be Empty");
                }
                if(pwd.isEmpty()){
                    signInPwd.setError("Password cannot be Empty");
                }
                else {
                    auth.createUserWithEmailAndPassword(user ,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(FirstActivity.this, "Signin Successful", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                Toast.makeText(FirstActivity.this, "Signin Fail"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    loginRedirectionTxt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(FirstActivity.this,LoginActivity.class));
                        }
                    });
                }

            }
        });
    }
}