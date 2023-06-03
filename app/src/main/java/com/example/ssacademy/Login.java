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

public class Login extends AppCompatActivity {

    EditText email,password;
    Button btn;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView image1,image2;
        image1 =findViewById(R.id.imageview);
        image2 = findViewById(R.id.qr);
        image1.setImageResource(R.drawable.image1);
        image2.setImageResource(R.drawable.qr);

        TextView textView = findViewById(R.id.register);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
    }
    public void LoginUser() {
        String fixEmail,fixPassword;
        fixEmail = email.getText().toString();
        fixPassword = password.getText().toString();

        if(TextUtils.isEmpty(fixEmail)) {
            Toast.makeText(getApplicationContext(),"Please enter Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(fixPassword)) {
            Toast.makeText(getApplicationContext(),"Please enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(fixEmail,fixPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()) {
                       Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(getApplicationContext(),Home.class);
                       startActivity(intent);
                   }
                   else {
                       Toast.makeText(getApplicationContext(),"Login Failed\nTry Again Later",Toast.LENGTH_SHORT).show();
                   }
                    }
                });
    }
}