package com.genuine_meaning.genuinemeaningpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class splashActivity extends AppCompatActivity {
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try{ this.getSupportActionBar().hide();

        }catch (Exception e){}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        timer= new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(splashActivity.this,loginActivity.class);
                startActivity(intent);
                finish();

            }
        },5000);



    }











}
