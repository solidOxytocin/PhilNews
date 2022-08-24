package com.example.philnews;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FBAuthentication {
    FirebaseAuth auth= FirebaseAuth.getInstance();
    isAuthSuccess isAuthSuccess;

    public FBAuthentication(isAuthSuccess isRegSuccess) {
        this.isAuthSuccess = isRegSuccess;
    }

    public void RegisterThis(String email, String Password) {
        auth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                isSuccess(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                isSuccess(false);
            }
        });

    }

    public void Login(String email, String pass){
        auth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                isSuccess(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                isSuccess(false);
            }
        });
    }
    public void isSuccess(boolean suc){
        isAuthSuccess.isSuccess(suc);

    }
    public boolean isLoggedIn(){
        FirebaseUser user= auth.getCurrentUser();
        if(user!=null){
            return true;
        }
        return false;
    }
}
