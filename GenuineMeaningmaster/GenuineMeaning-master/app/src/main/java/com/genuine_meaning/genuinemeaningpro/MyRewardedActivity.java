package com.genuine_meaning.genuinemeaningpro;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;


public class MyRewardedActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private RewardedVideoAd mRewardedVideoAd;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    int score=0;
    Timer timer;
    Button Earnbuttn;
    TextView ViewScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try{ this.getSupportActionBar().hide();
        }catch (Exception ignored){}
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_my_rewarded);
        ViewScore=findViewById(R.id.textviewscore);
         Earnbuttn=findViewById(R.id.btnearn);
         Firebase.setAndroidContext(this);

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

         SharedPreferences myScore=this.getSharedPreferences("MyAwesomeScore",Context.MODE_PRIVATE);
         score=myScore.getInt("score",0);
        ViewScore.setText("score:"+ score);


        Earnbuttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Earnbuttn.setEnabled(false);
                new CountDownTimer(99000, 50) { //Set Timer for 5 seconds
                    public void onTick(long millisUntilFinished) {
                    }



                    @Override
                    public void onFinish() {
                        Earnbuttn.setEnabled(true);
                    }
                }.start();


                score +=1;
                ViewScore.setText("score:"+ score);
                SharedPreferences myScore = getSharedPreferences("MyAwesomeScore", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = myScore.edit();
                editor.putInt("score", score);
                editor.commit();




            }
        });

































        timer= new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(MyRewardedActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },73000);












    }
    private void loadRewardedVideoAd() {
        if(!mRewardedVideoAd.isLoaded())
        mRewardedVideoAd.loadAd("ca-app-pub-3179007353785480/6324679826",
                new AdRequest.Builder().build());
    }


    public void vediorewarded(View view) {
        if(mRewardedVideoAd.isLoaded()){

            mRewardedVideoAd.show();

        }


    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        ViewScore.setText("5");
        score +=5;
        ViewScore.setText("score:"+ score);
        SharedPreferences myScore = getSharedPreferences("MyAwesomeScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myScore.edit();
        editor.putInt("score", score);
        editor.commit();


    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }

    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }

    public void clickclame(View view) {startActivity(new Intent(MyRewardedActivity.this,clamee.class));
    }
}






