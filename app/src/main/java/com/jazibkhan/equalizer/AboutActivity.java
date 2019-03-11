package com.jazibkhan.equalizer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String GITHUB = "https://github.com/j4zib/Equalizer";
    private static final String RATE_ON_GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=com.jazibkhan.equalizer";
    public static final String PRIVACY_POLICY = "https://docs.google.com/document/d/1GoypBqSTuSi_h3k18q9xpnKA0itwb9LvV-izrsOzPOM/edit?usp=sharing";
    private static final String TAG = "AboutActivity";
    LinearLayout donate, rateOnPlayStore, eMail, licenses, privacyPolicy, github;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        
        if(sharedPreferences.getBoolean("dark_theme",true)){
            setTheme(R.style.AppTheme_Dark);
            Log.d(TAG, "onCreate: Dark Theme");
        }
        else {
            setTheme(R.style.AppTheme);
            Log.d(TAG, "onCreate: Light Theme");
        }
        
        
        setContentView(R.layout.activity_about);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);



        donate = findViewById(R.id.donate);
        rateOnPlayStore = findViewById(R.id.rate_on_google_play);
        eMail = findViewById(R.id.report_bugs);
        licenses = findViewById(R.id.licenses);
        privacyPolicy = findViewById(R.id.privacy_policy);
        github = findViewById(R.id.fork_on_github);

        donate.setOnClickListener(this);
        rateOnPlayStore.setOnClickListener(this);
        eMail.setOnClickListener(this);
        licenses.setOnClickListener(this);
        privacyPolicy.setOnClickListener(this);
        github.setOnClickListener(this);

        //Remove this once Billing is enabled.
//        donate.setVisibility(View.GONE);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void openUrl(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        if(v==licenses){
            LicensesDialogFragment dialog = LicensesDialogFragment.newInstance();
            dialog.show(getSupportFragmentManager(), "LicensesDialog");
        }
        else if (v == donate){
            Intent myIntent = new Intent(AboutActivity.this, SupportActivity.class);
            AboutActivity.this.startActivity(myIntent);
        }
        else if (v == rateOnPlayStore){
            openUrl(RATE_ON_GOOGLE_PLAY);
        }

        else if(v == eMail){
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:jazib27@hotmail.com"));
            intent.putExtra(Intent.EXTRA_EMAIL, "jazib27@hotmail.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Equalizer");
            startActivity(Intent.createChooser(intent, "E-Mail"));
        }
        else if (v == privacyPolicy){
            openUrl(PRIVACY_POLICY);
        }
        else if (v == github){
            openUrl(GITHUB);
        }
    }
}
