package com.example.ssacademy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText email,password;
    Button btn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageView image1;
        image1 =findViewById(R.id.imageview);
        image1.setImageResource(R.drawable.image1);

        TextView textview = findViewById(R.id.login);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });
    }
    public void RegisterUser() {
        String fixemail,fixpassword;
        fixemail = email.getText().toString();
        fixpassword = password.getText().toString();

        if(TextUtils.isEmpty(fixemail)) {
            Toast.makeText(getApplicationContext(),"Please enter Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(fixpassword)) {
            Toast.makeText(getApplicationContext(),"Please enter Password",Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(fixemail,fixpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Home.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Registration Failed\nTry Again Later",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}