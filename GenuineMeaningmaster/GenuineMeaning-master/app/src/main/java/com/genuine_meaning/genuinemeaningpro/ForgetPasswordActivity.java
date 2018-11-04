package com.genuine_meaning.genuinemeaningpro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {
    private EditText PasswordEmail;
    private Button btnResetPassword;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try{ this.getSupportActionBar().hide();

        }catch (Exception e){}

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        PasswordEmail = findViewById(R.id.etPasswordEmail);
        btnResetPassword = findViewById(R.id.btnPasswordReset);
        firebaseAuth = FirebaseAuth.getInstance();


        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserEmail=PasswordEmail.getText().toString().trim();

                if(PasswordEmail.equals("")){
                    Toast.makeText(ForgetPasswordActivity.this, "Please enter your registered email ID", Toast.LENGTH_SHORT).show();
                }else {firebaseAuth.sendPasswordResetEmail(UserEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgetPasswordActivity.this, "Password reset email sent!", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(ForgetPasswordActivity.this, loginActivity.class));
                        }else{
                            Toast.makeText(ForgetPasswordActivity.this, "Error in sending password reset email", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                }






            }
        });








    }
}
