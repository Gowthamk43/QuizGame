package com.company.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    EditText mail;
    Button button;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mail = findViewById(R.id.editTextForgotEmail);
        button = findViewById(R.id.buttonContinue);
        progressBar = findViewById(R.id.progressBarForgot);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mail.getText().toString().trim().equals("")){

                    Toast.makeText(ForgotPassword.this,
                            "Please enter the email",Toast.LENGTH_LONG).show();

                }
                else{
                    String userEmail = mail.getText().toString();
                    resetPassword(userEmail);

                }









            }
        });
    }

    public void resetPassword(String userEmail){
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this,
                            "We sent you an email to reset the password",Toast.LENGTH_LONG).show();
                    button.setClickable(false);
                    finish();
                    
                    

                }else {

                    Toast.makeText(ForgotPassword.this,
                            "Check your email and try again",Toast.LENGTH_LONG).show();



                }
            }
        });
    }
}