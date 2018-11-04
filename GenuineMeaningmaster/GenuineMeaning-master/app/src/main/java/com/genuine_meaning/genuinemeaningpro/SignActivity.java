package com.genuine_meaning.genuinemeaningpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignActivity extends AppCompatActivity implements View.OnClickListener{

    EditText EmailText, PasswordText,NameText,PhoneText,ConfirmText;
    Button b1;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try{ this.getSupportActionBar().hide();

        }catch (Exception e){}

        Firebase.setAndroidContext(getApplicationContext());
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sign);
        NameText = findViewById(R.id.editTextName);
        EmailText = findViewById(R.id.email);
        PhoneText = findViewById(R.id.editTextNumber);
        PasswordText = findViewById(R.id.pass);
        ConfirmText = findViewById(R.id.editTextConpass);
        progressBar= findViewById(R.id.progress);
        b1= findViewById(R.id.register);
        b1.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    @Override
    public void onClick(View view)
    {
        String name= NameText.getText().toString().trim();
        String email= EmailText.getText().toString().trim();
        String number= PhoneText.getText().toString().trim();
        String pass= PasswordText.getText().toString().trim();
        String Conpass= ConfirmText.getText().toString().trim();


        if(!PasswordText.getText().toString().equals(ConfirmText.getText().toString())){
            ConfirmText.setError("Password Not matched");
            ConfirmText.requestFocus();
            return;
        }

        if(Conpass.isEmpty())
        {
            ConfirmText.setError("Type Again Your Password");
            ConfirmText.requestFocus();
            return;
        }

        if(number.isEmpty())
        {
            PhoneText.setError("Phone Number Cant't Blank");
            PhoneText.requestFocus();
            return;
        }

        if(name.isEmpty())
        {
            NameText.setError("Name Cant't Blank");
            NameText.requestFocus();
            return;
        }


        if(email.isEmpty())
        {
            EmailText.setError("Emal Cant't Blank");
            EmailText.requestFocus();
            return;
        }

        if(pass.isEmpty())
        {

            PasswordText.setError("Pass Cant't Blank");
            PasswordText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            EmailText.setError("Plz Enter Valid Format Email");
            EmailText.requestFocus();

        }

        if(pass.length()<6)
        {
            PasswordText.setError("Plz Enter Minimum length pass Is 6");
            PasswordText.requestFocus();
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("MSG", "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if(task.isSuccessful())
                        {
                            Toast.makeText(SignActivity.this, "User Register Successfull",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignActivity.this,loginActivity.class));
                            finish();
                            progressBar.setVisibility(View.GONE);

                        }
                        if(task.getException() instanceof FirebaseAuthUserCollisionException)
                        {
                            Toast.makeText(SignActivity.this, "User Already Exist",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

        Users users=new Users();
        users.setName(name);
        users.setEmail(email);
        users.setNumber(number);
        users.setPass(pass);


        Firebase firebase=new Firebase(Config.url);
        firebase.child("Visitor").push().setValue(users);






    }
}

