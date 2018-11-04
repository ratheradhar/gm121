package com.genuine_meaning.genuinemeaningpro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class WellcomeActivity extends AppCompatActivity {
    TextView tv;


    Timer timer;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try{ this.getSupportActionBar().hide();

        }catch (Exception e){}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        tv=findViewById(R.id.textView4);


        tv.setText("Hello"+getIntent().getStringExtra("NameText"));

        timer= new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(WellcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },5000);


    }
}
