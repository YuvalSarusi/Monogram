package com.example.monogram;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private EditText usernameEditText;
    private EditText passwordEditText;

    private String username;
    private String password;

    Intent moveToNext;

    private void init(){
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
    }

    private void getNameAndPass(){
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
    }

    private void signIn(){
        moveToNext = new Intent()
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getNameAndPass();
    }


}
