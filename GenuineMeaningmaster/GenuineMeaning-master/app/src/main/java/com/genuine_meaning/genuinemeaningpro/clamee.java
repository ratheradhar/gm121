package com.genuine_meaning.genuinemeaningpro;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class clamee extends AppCompatActivity {



    TextView tv0,tv2,tv3,tv4;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try{ this.getSupportActionBar().hide();



        }catch (Exception e){}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clamee);
        tv0=findViewById(R.id.textViewName);
        tv2=findViewById(R.id.textViewpoint);
        tv3=findViewById(R.id.textViewEmail);
        tv4=findViewById(R.id.textViewphonee);



    }
}
