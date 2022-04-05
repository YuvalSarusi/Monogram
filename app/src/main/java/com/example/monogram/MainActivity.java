package com.example.monogram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private EditText usernameEditText;
    private EditText passwordEditText;

    private Button signIn;

    private String username;
    private String password;

    Intent moveToHome;
    Intent moveToSignUp;

    private void init(){
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signIn = findViewById(R.id.signIn);
        moveToHome = new Intent(this, HomePage.class);
        //moveToSignUp connection
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNameAndPass();
                /*-----check user exist in db-----*/
                startActivity(moveToHome);
            }
        });
    }

    private void getNameAndPass(){
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
    }

    public void signIn(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }


}
