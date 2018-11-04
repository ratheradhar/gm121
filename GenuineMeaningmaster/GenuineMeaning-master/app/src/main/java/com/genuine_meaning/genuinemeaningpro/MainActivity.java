package com.genuine_meaning.genuinemeaningpro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.google.android.gms.ads.AdRequest.DEVICE_ID_EMULATOR;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public WebView webView;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private Button RButton;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RButton=findViewById(R.id.button7rewarded);
        webView= findViewById(R.id.gmwebview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.genuinemeaning.com/");






        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);





        RButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,MyRewardedActivity.class);
                startActivity(i);

                RButton.setEnabled(false);
                new CountDownTimer(63000, 10) { //Set Timer for 5 seconds
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        RButton.setEnabled(true);
                    }
                }.start();
            }
        });































        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.SUBJECT", "TECH MOBS");
                intent.putExtra("android.intent.extra.TEXT", "TECH MOBS - Application :n" +
                        "# Download it at : https://www.genuinemeaning.com/" + "n" +
                        "# Share And Enjoy ....:)");
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        prepareAd();

        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {

            public void run() {
                Log.i("hello", "world");
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("TAG"," Interstitial not loaded");
                        }

                        prepareAd();


                    }
                });

            }
        }, 80, 80, TimeUnit.SECONDS);





    }












    public void  prepareAd(){

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3179007353785480/4961671232");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }









    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.gmtcondection) {
            this.webView.loadUrl("https://www.genuinemeaning.com/trams-and-conduction/"); }
        if (id == R.id.gmprivacy) {
            this.webView.loadUrl("https://www.genuinemeaning.com/privacy-policy/"); }
        if (id == R.id.gmabout) {
            this.webView.loadUrl("https://www.genuinemeaning.com/about/"); }

        return super.onOptionsItemSelected(item);







    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.gmfacebook)
        {this.webView.loadUrl("https://www.facebook.com/genuinemeaning/");


            // Handle the camera action
        }  else if (id == R.id.gmyoutube) {
            this.webView.loadUrl("https://www.youtube.com/c/GenuineMeaning");

        } else if (id == R.id.gmtwitter) {
            this.webView.loadUrl("https://twitter.com/genuinemeaning0");



        } else if (id == R.id.gminsta) {
            this.webView.loadUrl("https://www.instagram.com/genuinemeaning/");

        } else if (id == R.id.gmwhatsapp) {
            this.webView.loadUrl("https://chat.whatsapp.com/invite/7AN7sTQOIeIGT5eGW34wTe");

        }

        else if (id == R.id.gmaddress) {
            this.webView.loadUrl("https://chat.whatsapp.com/invite/7AN7sTQOIeIGT5eGW34wTe");

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }









}




