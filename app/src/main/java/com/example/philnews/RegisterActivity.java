package com.example.philnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements isAuthSuccess {
    EditText txtEmail2;
    EditText txtpas2;
    EditText txtConfirmPass;

    Button btnBack;
    Button btnRegister;

    TextView txtErrorReg;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initializing views
        txtEmail2= findViewById(R.id.txtEmail2);
        txtpas2= findViewById(R.id.txtPass2);
        txtConfirmPass= findViewById(R.id.txtConfirmPass);

        btnBack=findViewById(R.id.btnBack);
        btnRegister=findViewById(R.id.btnRegister);

        txtErrorReg= findViewById(R.id.txtErrorReg);
        progressBar=findViewById(R.id.progressBar);

        //back Button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //back Register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                RegFunction();
            }
        });
    }
    public void RegFunction(){

        String email= txtEmail2.getText().toString();
        String pass= txtpas2.getText().toString();
        String pass2= txtConfirmPass.getText().toString();


        //Check if valid fields
        if(email.isEmpty()||pass.isEmpty()||pass2.isEmpty()){
            progressBar.setVisibility(View.GONE);
            txtErrorReg.setText("Some Fields Are Empty");
        }
        else if(!pass.equals(pass2)){
            progressBar.setVisibility(View.GONE);
            txtErrorReg.setText("Confirm Password and password not the same");
        }
        else if(pass.length()<6){
            progressBar.setVisibility(View.GONE);
            txtErrorReg.setText("Password should be more than 6");
        }
        else{
            FBAuthentication auth = new FBAuthentication(this);
            progressBar.setVisibility(View.GONE);
            auth.RegisterThis(email,pass);
        }

    }

    @Override
    public void isSuccess(Boolean isSuccess) {
        if(isSuccess){
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            txtErrorReg.setText("Something wrong with the server");
        }
    }
}