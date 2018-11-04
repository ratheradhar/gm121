package com.genuine_meaning.genuinemeaningpro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{


    EditText Edittext1, EditText2;
    Button b1;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView ForgetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try{ this.getSupportActionBar().hide();

        }catch (Exception e){}

        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        Edittext1 = findViewById(R.id.email);
        progressBar= findViewById(R.id.progress);
        EditText2 = findViewById(R.id.pass);
        b1= findViewById(R.id.login);
        b1.setOnClickListener(this);
        ForgetPassword= findViewById(R.id.gmforgtpassword);
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(loginActivity.this, MainActivity.class));
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        String email = Edittext1.getText().toString().trim();
        String pass = EditText2.getText().toString().trim();

        if (email.isEmpty()) {
            Edittext1.setError("Emal Cant't Blank");
            Edittext1.requestFocus();
            return;
        }

        if (pass.isEmpty()) {

            EditText2.setError("Pass Cant't Blank");
            EditText2.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Edittext1.setError("Plz Enter Valid Format Email");
            Edittext1.requestFocus();

        }

        if (pass.length() < 6) {
            EditText2.setError("Plz Enter Minimum length pass Is 6");
            EditText2.requestFocus();
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("MSG", "signInWithEmail:onComplete:" + task.isSuccessful());
                        if(task.isSuccessful())
                        {
                            Toast.makeText(loginActivity.this, "User Login Successfull",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(loginActivity.this,WellcomeActivity.class));
                            finish();
                            progressBar.setVisibility(View.GONE);

                        }
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("MSG", "signInWithEmail", task.getException());
                            Toast.makeText(loginActivity.this, "Authentication failed."+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

        ForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this,ForgetPasswordActivity.class));
            }
        });


    }



    public void newUser(View view) {

        startActivity(new Intent(loginActivity.this,SignActivity.class));

    }
}
