package com.example.philnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements isAuthSuccess{
    EditText txtEmail;
    EditText txtpas;
    public TextView txtloginerror;
    ProgressBar progressBar;
    FBAuthentication fba= new FBAuthentication(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializing views
        TextView RegTxt = (TextView) findViewById(R.id.RegTxt);
        Button btnlogin = (Button)findViewById(R.id.btnLogin);
        txtloginerror= (TextView) findViewById(R.id.txtloginerror);
        txtEmail= findViewById(R.id.editTextTextEmailAddress);
        txtpas= findViewById(R.id.editTextTextPassword);
        progressBar =findViewById(R.id.progressBar2);

        //Register Button Function go to RegisterActivity
        RegTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        //Login Button Function
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                login();
            }
        });
    }
    public void login(){
        String email = txtEmail.getText().toString();
        String pass= txtpas.getText().toString();
        //Check if empty or not
        if(email.isEmpty()||pass.isEmpty()){
            txtloginerror.setText("Empty Fields");
        }
        else{
            progressBar.setVisibility(View.GONE);
            fba.Login(email,pass);
        }
    }
    @Override
    public void isSuccess(Boolean isSuccess) {
        if(isSuccess){
            progressBar.setVisibility(View.GONE);
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }
        else{
            txtloginerror.setText("LoginError");
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(fba.isLoggedIn()){
            startActivity(new Intent(this,MainActivity.class));
            this.finish();
        }

    }
}